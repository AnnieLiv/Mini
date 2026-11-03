import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*Es el socio del gimnasio.*/
public class Socio extends Persona {
    private LocalDate fechaRegistro;
    private String estado = "Sin registrar";
    private List<Membresia> membresias = new ArrayList<>();
    private List<Rutina> rutinas = new ArrayList<>();

    /*Crea un socio con estado "Sin registrar".*/
    public Socio(int idPersona, String nombre, String telefono) {
        super(idPersona, nombre, telefono);
    }
    
    /*Crea un socio con todos sus datos.*/
    public Socio(int idPersona, String nombre, String telefono, LocalDate fechaRegistro, String estado) {
        super(idPersona, nombre, telefono);
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    /*Registra al socio, guarda la fecha de hoy y lo deja en estado "Activo".*/
    public void registrar() {
        fechaRegistro = LocalDate.now();
        estado = "Activo";
    }

    /*Da de baja al socio.*/
    public void darDeBaja() { estado = "Inactivo"; }

    /*Devuelve la rutina asignada más reciente.*/
    public Rutina consultarRutina() {
        return rutinas.isEmpty() ? null : rutinas.get(rutinas.size() - 1);
    }

    /*signa una rutina al socio.*/
    public void asignarRutina(Rutina rutina) {
        if (rutina.getEjercicios().isEmpty())
            throw new IllegalStateException("No se puede asignar una rutina sin ejercicios.");
        if (!rutinas.contains(rutina)) rutinas.add(rutina);
    }

    /*Agrega una membresía al socio.*/
    public void agregarMembresia(Membresia membresia) { membresias.add(membresia); }

    /*Muestrasi el socio tiene al menos una membresía vigente.*/
    public boolean tieneMembresiaVigente() {
        return membresias.stream().anyMatch(Membresia::estaVigente);
    }

    /*Devuelve el estado del socio.*/
    public String getEstado() { return estado; }

    /*Dice el día en que se registró el socio.*/
    public LocalDate getFechaRegistro() { return fechaRegistro; }

    /*Da la lista de membresías al socio*/
    public List<Membresia> getMembresias() { return membresias; }

    /*Devuelve una descripción del socio con su estado.*/
    @Override
    public String toString() {
        return "Socio " + super.toString() + " | " + estado + " | Registro: " + fechaRegistro;
    }
}
