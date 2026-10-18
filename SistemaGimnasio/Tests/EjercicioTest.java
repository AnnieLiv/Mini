import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EjercicioTest {

    @Test
    void creaEjercicioValido() {
        Ejercicio e = new Ejercicio("Sentadilla", 4, 12);
        assertEquals("Sentadilla - 4 series x 12 repeticiones", e.toString());
    }

    @Test
    void getNombreDevuelveElNombre() {
        assertEquals("Press de banca", new Ejercicio("Press de banca", 3, 10).getNombre());
    }

    @Test
    void rechazaSeriesIgualesACero() {
        assertThrows(IllegalArgumentException.class, () -> new Ejercicio("Plancha", 0, 10));
    }

    @Test
    void rechazaRepeticionesIgualesACero() {
        assertThrows(IllegalArgumentException.class, () -> new Ejercicio("Plancha", 3, 0));
    }

    @Test
    void rechazaSeriesNegativas() {
        assertThrows(IllegalArgumentException.class, () -> new Ejercicio("Remo", -1, 10));
    }

    @Test
    void rechazaRepeticionesNegativas() {
        assertThrows(IllegalArgumentException.class, () -> new Ejercicio("Remo", 3, -5));
    }

    @Test
    void modificarActualizaSeriesYRepeticiones() {
        Ejercicio e = new Ejercicio("Press", 3, 10);
        e.modificar(5, 8);
        assertEquals("Press - 5 series x 8 repeticiones", e.toString());
    }

    @Test
    void modificarConValoresInvalidosLanzaExcepcion() {
        Ejercicio e = new Ejercicio("Press", 3, 10);
        assertThrows(IllegalArgumentException.class, () -> e.modificar(0, 8));
    }

    @Test
    void modificarInvalidoNoCambiaLosValoresAnteriores() {
        Ejercicio e = new Ejercicio("Press", 3, 10);
        try {
            e.modificar(-2, 8);
        } catch (IllegalArgumentException ex) {
            // se espera el error; lo importante es que los datos no cambien
        }
        assertEquals("Press - 3 series x 10 repeticiones", e.toString());
    }

    @Test
    void aceptaElValorMinimoDeUnaSerieYUnaRepeticion() {
        Ejercicio e = new Ejercicio("Dominada", 1, 1);
        assertEquals("Dominada - 1 series x 1 repeticiones", e.toString());
    }
}
