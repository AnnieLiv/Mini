import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final List<Socio> socios = new ArrayList<>();
    private static final List<Entrenador> entrenadores = new ArrayList<>();
    private static final List<Rutina> rutinas = new ArrayList<>();
    private static int siguienteId = 1;

    public Main() { }

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            try {
                ejecutar(opcion);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
        System.out.println("¡Hasta pronto!");
    }

    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE GIMNASIO =====");
        System.out.println(" 1. Registrar socio");
        System.out.println(" 2. Listar socios");
        System.out.println(" 3. Modificar teléfono de un socio");
        System.out.println(" 4. Dar de baja a un socio");
        System.out.println(" 5. Agregar membresía a un socio");
        System.out.println(" 6. Consultar / renovar membresías de un socio");
        System.out.println(" 7. Crear rutina");
        System.out.println(" 8. Registrar entrenador");
        System.out.println(" 9. Asignar un socio a un entrenador");
        System.out.println("10. Asignar rutina a un socio");
        System.out.println("11. Consultar socios de un entrenador");
        System.out.println("12. Consultar rutina de un socio");
        System.out.println(" 0. Salir");
    }

    private static void ejecutar(int opcion) {
        switch (opcion) {
            case 1: registrarSocio(); break;
            case 2: listar(socios, "socios"); break;
            case 3: modificarTelefono(); break;
            case 4: darDeBaja(); break;
            case 5: agregarMembresia(); break;
            case 6: consultarMembresias(); break;
            case 7: crearRutina(); break;
            case 8: registrarEntrenador(); break;
            case 9: asignarSocioAEntrenador(); break;
            case 10: asignarRutina(); break;
            case 11: consultarSociosDeEntrenador(); break;
            case 12: consultarRutinaDeSocio(); break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    }

    private static void registrarSocio() {
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");
        Socio socio = new Socio(siguienteId++, nombre, telefono);
        socio.registrar();
        socios.add(socio);
        System.out.println("Socio registrado: " + socio);
    }

    private static void modificarTelefono() {
        Socio socio = elegir(socios, "socio");
        if (socio == null) return;
        socio.setTelefono(leerTexto("Nuevo teléfono: "));
        System.out.println("Actualizado: " + socio);
    }

    private static void darDeBaja() {
        Socio socio = elegir(socios, "socio");
        if (socio == null) return;
        socio.darDeBaja();
        System.out.println("Dado de baja: " + socio);
    }

    private static void agregarMembresia() {
        Socio socio = elegir(socios, "socio");
        if (socio == null) return;
        System.out.println("Tipo: 1. Mensual  2. Trimestral  3. Anual");
        int tipo = leerEntero("Elige el tipo: ");
        String nombreTipo = tipo == 3 ? "Anual" : tipo == 2 ? "Trimestral" : "Mensual";
        int costo = leerEntero("Costo: ");
        if (costo < 0) throw new IllegalArgumentException("El costo no puede ser negativo.");
        socio.agregarMembresia(new Membresia(nombreTipo, LocalDate.now(), costo));
        System.out.println("Membresía " + nombreTipo + " agregada a " + socio.obtenerNombre() + ".");
    }

    private static void consultarMembresias() {
        Socio socio = elegir(socios, "socio");
        if (socio == null) return;
        if (socio.getMembresias().isEmpty()) {
            System.out.println(socio.obtenerNombre() + " no tiene membresías.");
            return;
        }
        for (int i = 0; i < socio.getMembresias().size(); i++)
            System.out.println((i + 1) + ". " + socio.getMembresias().get(i));
        System.out.println("¿Tiene membresía vigente? " + (socio.tieneMembresiaVigente() ? "Sí" : "No"));
        if (leerTexto("¿Renovar alguna? (s/n): ").equalsIgnoreCase("s")) {
            Membresia membresia = elegir(socio.getMembresias(), "membresía");
            if (membresia == null) return;
            membresia.renovar();
            System.out.println("Renovada: " + membresia);
        }
    }

    private static void crearRutina() {
        Rutina rutina = new Rutina(leerTexto("Nombre de la rutina: "),
                leerTexto("Objetivo: "), leerTexto("Nivel: "));
        int cantidad = 0;
        while (cantidad < 1) {
            cantidad = leerEntero("¿Cuántos ejercicios tiene? (mínimo 1): ");
        }
        int agregados = 0;
        while (agregados < cantidad) {
            System.out.println("Ejercicio " + (agregados + 1) + ":");
            try {
                rutina.agregarEjercicio(leerTexto("  Nombre: "),
                        leerEntero("  Series: "), leerEntero("  Repeticiones: "));
                agregados++;
            } catch (IllegalArgumentException e) {
                System.out.println("  Error: " + e.getMessage() + " Intenta de nuevo.");
            }
        }
        rutinas.add(rutina);
        System.out.println("Rutina creada:\n" + rutina);
    }

    private static void registrarEntrenador() {
        Entrenador entrenador = new Entrenador(siguienteId++, leerTexto("Nombre: "),
                leerTexto("Teléfono: "), leerTexto("Especialidad: "));
        entrenadores.add(entrenador);
        System.out.println("Entrenador registrado: " + entrenador);
    }

    private static void asignarSocioAEntrenador() {
        Entrenador entrenador = elegir(entrenadores, "entrenador");
        if (entrenador == null) return;
        Socio socio = elegir(socios, "socio");
        if (socio == null) return;
        entrenador.agregarSocio(socio);
        System.out.println(socio.obtenerNombre() + " ahora está a cargo de " + entrenador.obtenerNombre() + ".");
    }

    private static void asignarRutina() {
        Entrenador entrenador = elegir(entrenadores, "entrenador");
        if (entrenador == null) return;
        Socio socio = elegir(socios, "socio");
        if (socio == null) return;
        Rutina rutina = elegir(rutinas, "rutina");
        if (rutina == null) return;
        entrenador.asignarRutina(socio, rutina);
        System.out.println("Rutina '" + rutina.getNombre() + "' asignada a " + socio.obtenerNombre() + ".");
    }

    private static void consultarSociosDeEntrenador() {
        Entrenador entrenador = elegir(entrenadores, "entrenador");
        if (entrenador == null) return;
        if (entrenador.consultarSocios().isEmpty())
            System.out.println(entrenador.obtenerNombre() + " no tiene socios a cargo.");
        for (Socio s : entrenador.consultarSocios())
            System.out.println("  - " + s.obtenerNombre() + " (" + s.getEstado() + ")");
    }

    private static void consultarRutinaDeSocio() {
        Socio socio = elegir(socios, "socio");
        if (socio == null) return;
        Rutina rutina = socio.consultarRutina();
        System.out.println(rutina == null ? socio.obtenerNombre() + " no tiene rutina." : rutina.toString());
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número entero.");
            }
        }
    }

    private static <T> T elegir(List<T> lista, String nombre) {
        if (lista.isEmpty()) {
            System.out.println("Todavía no hay " + nombre + "s.");
            return null;
        }
        for (int i = 0; i < lista.size(); i++)
            System.out.println((i + 1) + ". " + lista.get(i));
        int numero = leerEntero("Número de " + nombre + " a elegir: ");
        if (numero < 1 || numero > lista.size()) {
            System.out.println("Número fuera de la lista.");
            return null;
        }
        return lista.get(numero - 1);
    }

    private static <T> void listar(List<T> lista, String nombre) {
        if (lista.isEmpty()) System.out.println("Todavía no hay " + nombre + ".");
        for (T elemento : lista) System.out.println("  " + elemento);
    }
}
