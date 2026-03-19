# 🐾 PerfDog Petstore API Automation Framework

Este proyecto es una solución de automatización de pruebas para la API de **Petstore (PerfDog)**, desarrollada como parte del proceso de validación de funcionalidades backend. El framework está construido bajo una arquitectura de **Modelos de Objetos de Datos (DTOs)** para garantizar un código limpio, mantenible e independiente.

## 📋 Requerimientos del Enunciado Cubiertos
1. **Creación de usuario:** Flujo completo de registro de nuevos usuarios.
2. **Login de usuario:** Validación de inicio de sesión con credenciales dinámicas.
3. **Listado de mascotas:** Consulta de mascotas con estado "available" (disponible).
4. **Consulta por ID:** Obtención de datos específicos de una mascota existente.
5. **Creación de Orden:** Flujo de compra de una mascota (Store Order).
6. **Logout de usuario:** Cierre de sesión y validación de respuesta de la API.

## 🛠️ Stack Tecnológico
* **Java 17:** Lenguaje de programación base.
* **Maven:** Gestor de dependencias y ciclo de vida del proyecto.
* **Rest Assured:** Librería principal para la ejecución de pruebas de API REST.
* **TestNG:** Test Runner para la gestión de ejecuciones, asserts y reportabilidad.
* **Lombok:** Para reducir el código repetitivo (Boilerplate) en los modelos.
* **Jackson:** Para la serialización (Java a JSON) y deserialización (JSON a Java).

## 📂 Estructura del Proyecto
```text
src/test/java/com/globant/automation/
│
├── config/         # Configuración base, Endpoints y TestRunner.
├── model/          # DTOs (Data Transfer Objects) que representan el esquema de la API.
├── request/        # RequestBuilder: Métodos genéricos y reutilizables para peticiones HTTP.
└── test/           # Clases de prueba divididas por dominio (User, Pet, Order).
```
## ⚙️ Configuración y Ejecución
1. **Requisitos previos:**
   Asegúrese de tener configurado el JDK 17 y Maven en su variable de entorno JAVA_HOME.

2. **Ejecución total (Suite):**
   Para ejecutar todas las pruebas y asegurar la independencia de cada una, utilice el archivo testng.xml:

**Desde terminal:**
```text
mvn clean test
```

**Desde IntelliJ:**

1. Clic derecho sobre el archivo testng.xml. 
2. Seleccionar la opción Run '.../testng.xml'.

## 📊 Reportes de Prueba
El framework genera reportes automáticos al finalizar cada ejecución. Puede consultarlos en:

* target/surefire-reports/index.html (Generado por Maven). 
* test-output/index.html (Generado por TestNG).