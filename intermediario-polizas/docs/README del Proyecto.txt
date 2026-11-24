# Intermediario de Pólizas

Aplicación intermediaria desarrollada con **Java 25** y **Spring Boot 4.1.1** que conecta a los usuarios finales con el **Sistema Dueño** de pólizas mediante una interfaz web y endpoints REST.

El sistema **no utiliza base de datos local: todas las operaciones de consulta, registro, actualización y baja se ejecutan y confirman directamente contra la API remota del Sistema Dueño.

---

## Guía de instalación y ejecución por sistema operativo

Para compilar y ejecutar el proyecto necesitas tener instalados **JDK 25** y **Eclipse IDE** (el cual ya integra soporte nativo para Maven y Git, por lo que no requieres herramientas externas adicionales). Sigue los pasos correspondientes a tu sistema operativo:

### 1. Instalación y ejecución en Windows

#### Paso A: Instalar Java 25 (JDK 25)
1. Descarga el instalador **JDK 25** (`.msi` para arquitectura `x64`) desde [Eclipse Adoptium (Temurin 25)](https://adoptium.net/) o [Oracle JDK](https://www.oracle.com/java/technologies/downloads/).
   * *Alternativa desde PowerShell:*
     ```powershell
     winget install EclipseAdoptium.Temurin.25.JDK
     ```
2. Durante la instalación con `.msi`, activa la casilla **Set JAVA_HOME variable**. Si necesitas configurarlo manualmente:
   * Abre **Editar las variables de entorno del sistema** → **Variables de entorno**.
   * En *Variables del sistema*, haz clic en **Nueva...**, crea `JAVA_HOME` con la ruta de tu JDK (ejemplo: `C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot`).
   * Selecciona `Path` → **Editar** → **Nuevo** y agrega `%JAVA_HOME%\bin`.
3. Abre una terminal (**CMD** o **PowerShell**) y verifica la versión:
   ```cmd
   java -versión


1. Instalar Eclipse IDE(Windows) 
Descarga el instalador de Eclipse IDE para Windows (x86_64) desde Eclipse Downloads.

Ejecuta el archivo eclipse-inst-jre-win64.exe y selecciona el paquete Eclipse IDE for Enterprise Java and Web Developers.

Verifica que en el campo Java 21+ VM apunte a tu carpeta de JDK 25, haz clic en Install y al finalizar presiona Launch.

Paso C: Importar y ejecutar el proyecto en Eclipse
Clonar el repositorio: En Eclipse ve a File → Import... → Git → Projects from Git → Clone URI, pega https://github.com/DiegoIC9530/ArquitecturaDeSoftware.git y descarga las ramas. En la ventana final (Select a wizard to use for importing projects), haz clic en Cancel.

Importar como proyecto Maven: Ve a File → Import... → Maven → Existing Maven Projects → Next >. Haz clic en Browse..., entra a la carpeta clonada y selecciona la subcarpeta intermediario-polizas (donde está el pom.xml) → Finish.

Sincronizar dependencias: Haz clic derecho sobre el proyecto en el Project Explorer → Maven → Update Project... (Alt + F5) → OK.

Ejecutar: Abre src/main/java/mx/edu/uacm/is/slt/as/intermediario_polizas/IntermediarioPolizasApplication.java, haz clic derecho → Run As → Java Application.

Abre tu navegador en http://localhost:8080.

2.Instalación y ejecución en Linux (Ubuntu / Debian)
Paso A: Instalar Java 25 (JDK 25)
Descarga el paquete .deb de JDK 25 (x64 o aarch64 según tu procesador) desde Eclipse Adoptium (Temurin 25) o Oracle JDK.

Abre una terminal en la carpeta de descargas e instala el paquete:

Bash
sudo apt update
sudo apt install ./temurin-25-jdk_*.deb
(Si descargaste el paquete de Oracle, utiliza sudo dpkg -i jdk-25*_linux-x64_bin.deb).

Configura JAVA_HOME y PATH en ~/.bashrc:

Bash
echo 'export JAVA_HOME=/usr/lib/jvm/temurin-25-jdk-amd64' >> ~/.bashrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bashrc
source ~/.bashrc
Verifica que Java 25 esté activo:

Bash
java -version
Paso B: Instalar Eclipse IDE
Descarga el instalador para Linux (eclipse-inst-jre-linux64.tar.gz) desde Eclipse Downloads.

Descomprime el archivo y ejecuta el instalador desde la terminal:

Bash
tar -xzf eclipse-inst-jre-linux64.tar.gz
cd eclipse-installer
./eclipse-inst
Selecciona Eclipse IDE for Enterprise Java and Web Developers, verifica que apunte a la ruta de JDK 25, haz clic en Install y luego en Launch.

Paso C: Importar y ejecutar el proyecto en Eclipse
Clonar el repositorio: En Eclipse ve a File → Import... → Git → Projects from Git → Clone URI, ingresa https://github.com/DiegoIC9530/ArquitecturaDeSoftware.git y completa la descarga. Al aparecer el asistente final de importación, haz clic en Cancel.

Importar como proyecto Maven: Ve a File → Import... → Maven → Existing Maven Projects, selecciona la subcarpeta intermediario-polizas que contiene el pom.xml y haz clic en Finish.

Sincronizar dependencias: Haz clic derecho sobre el proyecto → Maven → Update Project... (Alt + F5) → OK.

Ejecutar: Haz clic derecho sobre IntermediarioPolizasApplication.java → Run As → Java Application. Que se encuentra en la siguiente ruta :src/main/java/mx/edu/uacm/is/slt/as/intermediario_polizas/IntermediarioPolizasApplication.java

Abre tu navegador en http://localhost:8080.

3. Instalación y ejecución en macOS (Apple Silicon e Intel)
Paso A: Instalar Java 25 (JDK 25)
Descarga el instalador .pkg de JDK 25 desde Eclipse Adoptium (Temurin 25) o Oracle JDK según la arquitectura de tu Mac:

aarch64 para Mac con chip Apple Silicon (M1, M2, M3, M4).

x64 para Mac con procesador Intel.

Alternativa mediante Homebrew:

Bash
brew install --cask temurin@25
Configura JAVA_HOME en ~/.zshrc:

Bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 25)' >> ~/.zshrc
source ~/.zshrc
Verifica la instalación en la Terminal:

Bash
java -version
Paso B: Instalar Eclipse IDE
Descarga el instalador .dmg de Eclipse para macOS (AArch64 para Apple Silicon o x86_64 para Intel) desde Eclipse Downloads.

Alternativa mediante Homebrew:

Bash
brew install --cask eclipse-jee
Si usaste el archivo .dmg, ábrelo, ejecuta Eclipse Installer, elige Eclipse IDE for Enterprise Java and Web Developers, confirma que detecte JDK 25 y haz clic en Install.

Paso C: Importar y ejecutar el proyecto en Eclipse
Clonar el repositorio: Abre Eclipse, ve a File → Import... → Git → Projects from Git → Clone URI, clona https://github.com/DiegoIC9530/ArquitecturaDeSoftware.git y haz clic en Cancel cuando aparezca el asistente de importación genérico.

Importar como proyecto Maven: Ve a File → Import... → Maven → Existing Maven Projects, selecciona la subcarpeta intermediario-polizas (donde vive el pom.xml) y presiona Finish.

Sincronizar dependencias: Haz clic derecho sobre el proyecto → Maven → Update Project... → OK.

Ejecutar: Haz clic derecho sobre IntermediarioPolizasApplication.java → Run As → Java Application.Que se encuentra en la siguiente:
 ruta :src/main/java/mx/edu/uacm/is/slt/as/intermediario_polizas/IntermediarioPolizasApplication.java

Abre Safari o tu navegador preferido en http://localhost:8080.