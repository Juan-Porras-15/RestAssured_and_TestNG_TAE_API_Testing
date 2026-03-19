package com.globant.automation.test;

import com.globant.automation.config.Endpoints;
import com.globant.automation.config.TestRunner;
import com.globant.automation.model.PetDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class PetTests extends TestRunner {

    private PetDTO testPet;

    @BeforeMethod
    public void preparePetData() {
        testPet = PetDTO.generateFake();
    }

    @Test(description = "3. Listar mascotas con status 'sold' y validar datos")
    public void findSoldPetsTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("status", "sold");

        Response response = RequestBuilder.getRequestWithQueryParams(Endpoints.PET_FIND_BY_STATUS, queryParams);
        response.then().statusCode(200);

        List<PetDTO> soldPets = response.jsonPath().getList("", PetDTO.class);

        Assert.assertFalse(soldPets.isEmpty(), "La lista de mascotas 'sold' está vacía.");

        SoftAssert softAssert = new SoftAssert();

        for (PetDTO pet : soldPets) {
            softAssert.assertNotNull(pet.getId(), "Mascota encontrada sin ID");
            softAssert.assertNotNull(pet.getName(), "La mascota con ID " + pet.getId() + " no tiene nombre");
        }

        System.out.println("Validación terminada para " + soldPets.size() + " mascotas.");
    }

    @Test(description = "4. Consultar una mascota por ID y validar nombre")
    public void findPetByIdTest() {
        Assert.assertNotNull(testPet, "El objeto testPet no fue inicializado correctamente.");

        Response createResp = RequestBuilder.postRequest(Endpoints.CREATE_PET, testPet);
        Assert.assertEquals(createResp.getStatusCode(), 200, "No se pudo crear la mascota para la consulta");

        Response response = RequestBuilder.getRequestWithParam(Endpoints.PET_BY_ID, "petId", testPet.getId());

        var validate = response.then().statusCode(200);
        validate.body("id", equalTo(testPet.getId()));

        if (testPet.getName() != null) {
            validate.body("name", equalTo(testPet.getName()));
        }

        System.out.println("Consulta exitosa para mascota: " + testPet.getName());
    }

    @AfterMethod
    public void cleanUp() {
        if (testPet != null && testPet.getId() != null) {
            String path = Endpoints.PET_BY_ID;

            RequestBuilder.deleteRequest(path, "petId", testPet.getId());
            System.out.println("Limpieza: Mascota " + testPet.getId() + " eliminada.");
        }
    }
}