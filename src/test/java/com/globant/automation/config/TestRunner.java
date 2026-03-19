package com.globant.automation.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class TestRunner {

    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    @Getter
    private static String baseurl;

    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment() {
        loadProperties();

        baseurl = PROPERTIES.getProperty("base.url");

        if (baseurl == null) {
            log.error("¡ERROR CRÍTICO! No se pudieron encontrar las llaves en el config.properties");
            throw new RuntimeException("Configuración incompleta en config.properties");
        }

        log.info("Entorno configurado correctamente. BaseURL: {}", baseurl);
    }

    private void loadProperties() {
        try (FileInputStream inputStream = new FileInputStream(PROPERTIES_FILE)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            log.error("No se pudo cargar el archivo de propiedades: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}