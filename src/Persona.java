/*Una persona del gimnasio, de aquí salen los socios y los entrenadores.*/
public abstract class Persona {
    private int idPersona;
    private String nombre;
    private String telefono;

    /*Crea una persona con sus datos.*/
    public Persona(int idPersona, String nombre, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
    }

     /*Dice cómo se llama la persona.*/
    public String obtenerNombre() { return nombre; }

    /**Cambia el teléfono */
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    /*Dice el número que identifica a la perona.*/
    public int getIdPersona() { return idPersona; }

    /*Dice el teléfono de la persona.*/
    public String getTelefono() { return telefono; }

    /*Cambia los datos de la persona en un texto.*/
    @Override
    public String toString() { return "[" + idPersona + "] " + nombre + " (Tel: " + telefono + ")"; }
}
