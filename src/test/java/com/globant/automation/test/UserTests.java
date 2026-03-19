package com.globant.automation.test;

import com.globant.automation.config.Endpoints;
import com.globant.automation.config.TestRunner;
import com.globant.automation.model.UserDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class UserTests extends TestRunner {

    private UserDTO testUser;

    @BeforeMethod
    public void prepareDataInMemory() {
        testUser = UserDTO.getFakeUser();
    }

    @Test(description = "1. Crear un usuario nuevo y validar respuesta")
    public void createUserTest() {
        Response response = RequestBuilder.postRequest(Endpoints.CREATE_USER, testUser);

        response.then()
                .statusCode(200)
                .body("message", equalTo(testUser.getId().toString()));

        System.out.println("PASO: Usuario creado exitosamente.");
    }

    @Test(description = "2. Realizar login con usuario")
    public void loginUserTest() {
        Response createResp = RequestBuilder.postRequest(Endpoints.CREATE_USER, testUser);
        Assert.assertEquals(createResp.getStatusCode(), 200, "FALLO PRE-CONDICIÓN: No se pudo crear el usuario para el login.");

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", testUser.getUsername());
        credentials.put("password", testUser.getPassword());

        Response loginResp = RequestBuilder.getRequestWithQueryParams(Endpoints.USER_LOGIN, credentials);

        loginResp.then()
                .statusCode(200)
                .body("message", containsString("logged in user session"));
    }

    @Test(description = "6. Realizar logout de usuario")
    public void logoutUserTest() {
        Response createResp = RequestBuilder.postRequest(Endpoints.CREATE_USER, testUser);
        Assert.assertEquals(createResp.getStatusCode(), 200, "FALLO PRE-CONDICIÓN: No se pudo crear el usuario para el logout.");

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", testUser.getUsername());
        credentials.put("password", testUser.getPassword());

        Response loginResp = RequestBuilder.getRequestWithQueryParams(Endpoints.USER_LOGIN, credentials);
        Assert.assertEquals(loginResp.getStatusCode(), 200, "FALLO PRE-CONDICIÓN: No se pudo iniciar sesión para el logout.");

        Response logoutResp = RequestBuilder.getRequest(Endpoints.USER_LOGOUT);

        logoutResp.then()
                .statusCode(200)
                .body("message", is("ok"));

        System.out.println("Logout verificado con éxito.");
    }

    @AfterMethod
    public void cleanUpApi() {
        String deletePath = Endpoints.CREATE_USER + "/" + testUser.getUsername();
        RequestBuilder.deleteRequest(deletePath, null, null);
    }
}