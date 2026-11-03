import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RutinaTest {

    private Rutina rutinaConDosEjercicios() {
        Rutina r = new Rutina("Fuerza", "Ganar fuerza", "Intermedio");
        r.agregarEjercicio("Press", 4, 8);
        r.agregarEjercicio("Remo", 3, 10);
        return r;
    }

    @Test
    void getNombreDevuelveElNombre() {
        assertEquals("Fuerza", new Rutina("Fuerza", "Ganar fuerza", "Intermedio").getNombre());
    }

    @Test
    void rutinaNuevaNoTieneEjercicios() {
        assertEquals(0, new Rutina("Fuerza", "Ganar fuerza", "Intermedio").getEjercicios().size());
    }

    @Test
    void agregarUnEjercicioLoGuarda() {
        Rutina r = new Rutina("Fuerza", "Ganar fuerza", "Intermedio");
        r.agregarEjercicio("Press", 4, 8);
        assertEquals(1, r.getEjercicios().size());
    }

    @Test
    void agregarVariosEjerciciosLosGuardaTodos() {
        Rutina r = rutinaConDosEjercicios();
        r.agregarEjercicio("Sentadilla", 4, 12);
        assertEquals(3, r.getEjercicios().size());
    }

    @Test
    void agregarEjercicioInvalidoLanzaExcepcion() {
        Rutina r = new Rutina("Fuerza", "Ganar fuerza", "Intermedio");
        assertThrows(IllegalArgumentException.class, () -> r.agregarEjercicio("Press", 0, 8));
    }

    @Test
    void quitarUnEjercicioDejaUnoMenos() {
        Rutina r = rutinaConDosEjercicios();
        r.quitarEjercicio("Remo");
        assertEquals(1, r.getEjercicios().size());
    }

    @Test
    void noSePuedeQuitarElUltimoEjercicio() {
        Rutina r = new Rutina("Fuerza", "Ganar fuerza", "Intermedio");
        r.agregarEjercicio("Press", 4, 8);
        assertThrows(IllegalStateException.class, () -> r.quitarEjercicio("Press"));
    }

    @Test
    void quitarEjercicioNoDistingueMayusculas() {
        Rutina r = rutinaConDosEjercicios();
        r.quitarEjercicio("remo");
        assertEquals(1, r.getEjercicios().size());
    }

    @Test
    void quitarUnEjercicioInexistenteNoCambiaLaRutina() {
        Rutina r = rutinaConDosEjercicios();
        r.quitarEjercicio("Burpees");
        assertEquals(2, r.getEjercicios().size());
    }

    @Test
    void toStringMuestraDatosYEjercicios() {
        String texto = rutinaConDosEjercicios().toString();
        assertTrue(texto.startsWith("Rutina 'Fuerza' | Ganar fuerza | Intermedio")
                && texto.contains("Press - 4 series x 8 repeticiones"));
    }
}
