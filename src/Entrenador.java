import java.util.ArrayList;
import java.util.List;

public class Entrenador extends Persona {
    private String especialidad;
    private List<Socio> socios = new ArrayList<>();

    /*Crea un entrenador.*/
    public Entrenador(int idPersona, String nombre, String telefono, String especialidad) {
        super(idPersona, nombre, telefono);
        this.especialidad = especialidad;
    }
    
    /*Asigna un socio al entrenador*/
    public void agregarSocio(Socio socio) {
        if (!socios.contains(socio)) socios.add(socio);
    }
    
    /*Le da una rutina al socio.*/
    public void asignarRutina(Socio socio, Rutina rutina) {
        if (!socios.contains(socio))
            throw new IllegalArgumentException(socio.obtenerNombre() + " no está a cargo de " + obtenerNombre());
        socio.asignarRutina(rutina);
    }
    
    /*Muestra una lista de los socios que tiene a cargo el entrenador.*/
    public List<Socio> consultarSocios() { return socios; }

    @Override
    public String toString() { return "Entrenador " + super.toString() + " | " + especialidad; }
}
