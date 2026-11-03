/*Crea los ejercicios de la rutina.
*Guarda las series y repeticiones.*/

public class Ejercicio {
    private String nombre;
    private int series;
    private int repeticiones;
    
    /*Crea un ejercicio.*/
    public Ejercicio(String nombre, int series, int repeticiones) {
        
        this.nombre = nombre;
        modificar(series, repeticiones);
    }
    
    /*Modifica las series y repeticiones */
    public void modificar(int series, int repeticiones) {
        if (series <= 0 || repeticiones <= 0)
            throw new IllegalArgumentException("Las series y repeticiones deben ser mayores que cero.");
        this.series = series;
        this.repeticiones = repeticiones;
    }
    /*Dice el nombre del ejercicio.*/
    public String getNombre() { return nombre; }
    
    /*Muestra el ejercicio en un texto*/
    @Override
    public String toString() { return nombre + " - " + series + " series x " + repeticiones + " repeticiones"; }
}
