import java.util.ArrayList;
import java.util.List;

// Agregación: atiende a 0 o más socios, que existen sin él.
public class Entrenador extends Persona {
    private String especialidad;
    private List<Socio> socios = new ArrayList<>();

    public Entrenador(int idPersona, String nombre, String telefono, String especialidad) {
        super(idPersona, nombre, telefono);
        this.especialidad = especialidad;
    }

    public void agregarSocio(Socio socio) {
        if (!socios.contains(socio)) socios.add(socio);
    }

    public void asignarRutina(Socio socio, Rutina rutina) {
        if (!socios.contains(socio))
            throw new IllegalArgumentException(socio.obtenerNombre() + " no está a cargo de " + obtenerNombre());
        socio.asignarRutina(rutina);
    }

    public List<Socio> consultarSocios() { return socios; }

    @Override
    public String toString() { return "Entrenador " + super.toString() + " | " + especialidad; }
}
