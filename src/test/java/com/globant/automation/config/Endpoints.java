package com.globant.automation.config;

/**
 * Esta clase centraliza todas las rutas (paths) de la API de Petstore.
 * Mantiene el código limpio y facilita el mantenimiento.
 */
public class Endpoints {

    public static final String CREATE_USER = "/user";
    public static final String USER_LOGIN = "/user/login";
    public static final String PET_FIND_BY_STATUS = "/pet/findByStatus";
    public static final String PET_BY_ID = "/pet/{petId}";
    public static final String STORE_ORDER = "/store/order";
    public static final String USER_LOGOUT = "/user/logout";
    public static final String CREATE_PET = "/pet";
}