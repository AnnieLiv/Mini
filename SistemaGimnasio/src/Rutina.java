import java.util.ArrayList;
import java.util.List;

// Composición: la rutina crea sus ejercicios y debe tener al menos 1.
public class Rutina {
    private String nombre;
    private String objetivo;
    private String nivel;
    private List<Ejercicio> ejercicios = new ArrayList<>();

    public Rutina(String nombre, String objetivo, String nivel) {
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.nivel = nivel;
    }

    public void agregarEjercicio(String nombre, int series, int repeticiones) {
        ejercicios.add(new Ejercicio(nombre, series, repeticiones));
    }

    public void quitarEjercicio(String nombre) {
        if (ejercicios.size() <= 1)
            throw new IllegalStateException("Una rutina debe tener al menos 1 ejercicio.");
        ejercicios.removeIf(e -> e.getNombre().equalsIgnoreCase(nombre));
    }

    public List<Ejercicio> getEjercicios() { return ejercicios; }

    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        String texto = "Rutina '" + nombre + "' | " + objetivo + " | " + nivel;
        for (Ejercicio e : ejercicios) texto += "\n   - " + e;
        return texto;
    }
}
