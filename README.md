# Microservicio users
Microservicio encargado de registrar usuarios y hacer el respectivo login del mismo a través del token generado.

### Pre requisitos

- Java versión 11.
- Spring version 2.5.14.
- Gradle 7.4
- H2 Databse

### Generalidades

- Desarrollo realizado a partir de buenas prácticas de desarrollo aplicando clean code y principios SOLID.
- La cobertura global del código está en 80%.
- Gestión de errores se maneja a través @RestControllerAdvice.
- Para el endpoint "/login" el token se debe enviar en el header "Authorization".

### Construcción y Ejecución del proyecto

- Partiendo de que nos encontramos en una consola batch, nos dirigimos a la raíz del proyecto y ejecutamos la siguiente línea:
  ```
  ./gradlew build
  ```  
- Una vez construido para la ejecución del proyecto podemos ejecutar el siguiente comando en la raiz.
    ```
  java -jar build/libs/users-0.0.1-SNAPSHOT.jar
  ```  
