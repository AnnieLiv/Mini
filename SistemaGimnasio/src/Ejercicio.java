public class Ejercicio {
    private String nombre;
    private int series;
    private int repeticiones;

    public Ejercicio(String nombre, int series, int repeticiones) {
        this.nombre = nombre;
        modificar(series, repeticiones);
    }

    public void modificar(int series, int repeticiones) {
        if (series <= 0 || repeticiones <= 0)
            throw new IllegalArgumentException("Las series y repeticiones deben ser mayores que cero.");
        this.series = series;
        this.repeticiones = repeticiones;
    }

    public String getNombre() { return nombre; }

    @Override
    public String toString() { return nombre + " - " + series + " series x " + repeticiones + " repeticiones"; }
}
