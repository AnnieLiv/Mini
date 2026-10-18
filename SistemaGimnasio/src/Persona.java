public abstract class Persona {
    private int idPersona;
    private String nombre;
    private String telefono;

    public Persona(int idPersona, String nombre, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String obtenerNombre() { return nombre; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() { return "[" + idPersona + "] " + nombre + " (Tel: " + telefono + ")"; }
}
