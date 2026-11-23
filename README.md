# Intermediario de Pólizas

Intermediario Spring Boot (Java 25) entre el **Sistema Dueño** de pólizas y sus usuarios. No guarda datos por su cuenta: consulta y modifica la información en el Sistema Dueño.

## Cambios respecto a la versión original

**Eliminados**

* `repositorios/`: base de datos local (JPA y PostgreSQL).
* `ManejadorExcepcionesGlobal.java`: convertía errores de programación en 404.
* `SistemaDuenoNoDisponible.java`: código sin uso.
* `vistas/menuConsola.java`: archivo vacío.
* `.checkstyle` y `target/`: archivos de IDE y compilados.

**Modificados**

* `pom.xml`: sin dependencias de JPA ni PostgreSQL.
* `application.properties`: solo nombre y puerto. Se quitó la contraseña en texto plano.
* `modelos/`: sin anotaciones de base de datos. `Beneficiario` ya no tiene campo `id`.
* `servicios/`: sin repositorios ni métodos locales. Cada alta, modificación y baja se confirma consultando el registro.
* `adaptadores/SistemaDuenoAdapter.java`: respuestas vacías se reportan como "no encontrado", y se agregan funciones para confirmar escrituras.
* `controladores/ManejadorErrores.java`: nuevas respuestas 404 (no encontrado) y 502 (no confirmado).
* `controladores/`: se quitaron los `try/catch` que devolvían 503 para cualquier error.
* `static/index.html`: formularios de alta, modificación y baja; mensajes de resultado; estilo plano.

**Nuevos**

* `excepciones/RecursoNoEncontradoException.java`
* `excepciones/OperacionNoConfirmadaException.java`
* Consulta de beneficiario con segundo apellido (ruta de cinco segmentos).

**Sin cambios**

`IntermediarioPolizasApplication.java`, la prueba de arranque, `mvnw`, `.mvn/` y `.gitignore`.

## Cómo ejecutarla

1. Abre la carpeta `intermediario-polizas` (la que contiene `pom.xml`).
2. Espera a que Maven descargue las dependencias.
3. Ejecuta `IntermediarioPolizasApplication` o `.\\mvnw.cmd spring-boot:run`.
4. Abre http://localhost:8080.

No requiere PostgreSQL.

## Orden de registro

1. **Cliente** (por CURP).
2. **Póliza**, que requiere un cliente registrado.
3. **Beneficiario**, que requiere una póliza registrada.

