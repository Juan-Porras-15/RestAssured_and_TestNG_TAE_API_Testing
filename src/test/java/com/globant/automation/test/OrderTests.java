package com.globant.automation.test;

import com.globant.automation.config.Endpoints;
import com.globant.automation.config.TestRunner;
import com.globant.automation.model.OrderDTO;
import com.globant.automation.model.PetDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

/**
 * Pruebas para la funcionalidad de Órdenes (Store)
 * Cumple con el requerimiento de creación de orden de compra.
 */
public class OrderTests extends TestRunner {

    private PetDTO testPet;
    private OrderDTO testOrder;

    @BeforeMethod
    public void setup() {
        testPet = PetDTO.generateFake();
        RequestBuilder.postRequest(Endpoints.CREATE_PET, testPet);

        testOrder = OrderDTO.generateFake(testPet.getId());
    }

    @Test(description = "5. Crear una orden de compra para una mascota")
    public void placeOrderTest() {
        Response response = RequestBuilder.postRequest(Endpoints.STORE_ORDER, testOrder);

        response.then()
                .statusCode(200)
                .body("petId", equalTo(testPet.getId()))
                .body("status", is("placed"));

        Assert.assertNotNull(response.jsonPath().get("id"), "El ID de la orden no debería ser nulo");
    }

    @AfterMethod
    public void cleanup() {
        RequestBuilder.deleteRequest(Endpoints.PET_BY_ID, "petId", testPet.getId());
    }
}