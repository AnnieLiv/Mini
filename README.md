# Sistema de Gestión de Gimnasio

Proyecto final de Programación Orientada a Objetos en **Java**. Aplicación de consola para administrar socios, entrenadores, membresías, rutinas y ejercicios de un gimnasio, con persistencia en **H2**, pruebas con **JUnit 5** y documentación con **JavaDoc**.

## Contenido

- `src/` — código fuente (clases del sistema, menú, conexión a H2 y DAO).
- `Tests/` — 60 pruebas unitarias JUnit 5.
- `lib/` — librerías: H2 y JUnit.
- `docs/` — documentación JavaDoc (abrir `docs/index.html`).
- `documentacion/` — [Manual técnico](documentacion/MANUAL_TECNICO.md) y [Manual de usuario](documentacion/MANUAL_USUARIO.md).
- `Capturas/` — evidencias de funcionamiento.

## Clases

`Persona` (abstracta) → `Socio` y `Entrenador` · `Membresia` · `Rutina` → `Ejercicio` · `Main` (menú).
Persistencia: `ConexionBD`, `SocioDAO`.

## Cómo ejecutarlo

Desde la carpeta del proyecto (PowerShell, con el JDK instalado):

```
javac -encoding UTF-8 -cp "lib/*" -d out src\*.java Tests\*.java
java -cp "out;lib/*" Main
```

Demostración de la base de datos H2:

```
java -cp "out;lib/*" PruebaBD
```

## Pruebas

```
java -jar lib\junit-platform-console-standalone-1.10.2.jar execute -cp out --scan-classpath
```

Resultado esperado: `60 tests successful`.

## Persistencia

Base de datos H2 en modo archivo (`datos/gimnasio.mv.db`) con 7 tablas: `socio`, `entrenador`, `membresia`, `rutina`, `ejercicio`, `entrenador_socio` y `socio_rutina`. Los socios ya se guardan y se leen desde la base de datos (ver `PruebaBD`). Los detalles están en el [Manual técnico](documentacion/MANUAL_TECNICO.md).

## Evidencias

| | |
|---|---|
| Estructura del proyecto | ![Estructura](Capturas/01_estructura_proyecto.png) |
| Registrar socio en el menú | ![Registrar socio](Capturas/02_menu_registrar_socio.png) |
| Membresías | ![Membresía](Capturas/03_membresia.png) |
| Rutina y entrenador | ![Rutina y entrenador](Capturas/04_rutina_y_entrenador.png) |
| Error controlado | ![Error controlado](Capturas/05_error_controlado.png) |
| Pruebas JUnit | ![Pruebas](Capturas/06_pruebas_junit.png) |
| H2: primera ejecución | ![H2 primera ejecución](Capturas/07_h2_primera_ejecucion.png) |
| H2: los datos siguen ahí | ![H2 persistencia](Capturas/08_h2_datos_persisten.png) |
| Carpeta de la base de datos | ![Carpeta datos](Capturas/09_carpeta_datos.png) |
| JavaDoc | ![JavaDoc](Capturas/10_javadoc.png) |

## Autora

Annielee — Desarrollo de Software.