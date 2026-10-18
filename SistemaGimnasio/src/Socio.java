import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Socio del gimnasio. Hereda de {@link Persona}. Puede tener varias
 * {@link Membresia} y varias {@link Rutina}.
 */
public class Socio extends Persona {
    private LocalDate fechaRegistro;
    private String estado = "Sin registrar";
    private List<Membresia> membresias = new ArrayList<>();
    private List<Rutina> rutinas = new ArrayList<>();

    /**
     * Crea un socio con estado "Sin registrar".
     *
     * @param idPersona identificador del socio
     * @param nombre    nombre completo
     * @param telefono  número de teléfono
     */
    public Socio(int idPersona, String nombre, String telefono) {
        super(idPersona, nombre, telefono);
    }

    /** Registra al socio: guarda la fecha de hoy y lo deja en estado "Activo". */
    public void registrar() {
        fechaRegistro = LocalDate.now();
        estado = "Activo";
    }

    /** Da de baja al socio dejándolo en estado "Inactivo". */
    public void darDeBaja() { estado = "Inactivo"; }

    /**
     * Devuelve la rutina asignada más reciente.
     *
     * @return la última rutina asignada, o {@code null} si no tiene ninguna
     */
    public Rutina consultarRutina() {
        return rutinas.isEmpty() ? null : rutinas.get(rutinas.size() - 1);
    }

    /**
     * Asigna una rutina al socio.
     *
     * @param rutina rutina a asignar
     * @throws IllegalStateException si la rutina no tiene ejercicios
     */
    public void asignarRutina(Rutina rutina) {
        if (rutina.getEjercicios().isEmpty())
            throw new IllegalStateException("No se puede asignar una rutina sin ejercicios.");
        if (!rutinas.contains(rutina)) rutinas.add(rutina);
    }

    /**
     * Agrega una membresía al socio.
     *
     * @param membresia membresía a agregar
     */
    public void agregarMembresia(Membresia membresia) { membresias.add(membresia); }

    /**
     * Devuelve las membresías del socio.
     *
     * @return lista de membresías
     */
    public List<Membresia> getMembresias() { return membresias; }

    /**
     * Indica si el socio tiene al menos una membresía vigente.
     *
     * @return {@code true} si alguna de sus membresías está vigente
     */
    public boolean tieneMembresiaVigente() {
        return membresias.stream().anyMatch(Membresia::estaVigente);
    }

    /**
     * Devuelve el estado del socio.
     *
     * @return "Sin registrar", "Activo" o "Inactivo"
     */
    public String getEstado() { return estado; }

    /**
     * Devuelve una descripción del socio con su estado y fecha de registro.
     *
     * @return texto con los datos del socio
     */
    @Override
    public String toString() {
        return "Socio " + super.toString() + " | " + estado + " | Registro: " + fechaRegistro;
    }
}
