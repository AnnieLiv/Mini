import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SocioTest {

    private Socio nuevoSocio() {
        return new Socio(1, "Ana Torres", "3001112233");
    }

    private Rutina rutinaConEjercicio(String nombre) {
        Rutina r = new Rutina(nombre, "objetivo", "nivel");
        r.agregarEjercicio("Ejercicio", 3, 10);
        return r;
    }

    @Test
    void socioNuevoEstaSinRegistrar() {
        assertEquals("Sin registrar", nuevoSocio().getEstado());
    }

    @Test
    void registrarLoDejaActivo() {
        Socio s = nuevoSocio();
        s.registrar();
        assertEquals("Activo", s.getEstado());
    }

    @Test
    void darDeBajaLoDejaInactivo() {
        Socio s = nuevoSocio();
        s.registrar();
        s.darDeBaja();
        assertEquals("Inactivo", s.getEstado());
    }

    @Test
    void registrarGuardaLaFechaDeHoy() {
        Socio s = nuevoSocio();
        s.registrar();
        assertTrue(s.toString().contains(LocalDate.now().toString()));
    }

    @Test
    void socioNuevoNoTieneRutina() {
        assertNull(nuevoSocio().consultarRutina());
    }

    @Test
    void consultarRutinaDevuelveLaUltimaAsignada() {
        Socio s = nuevoSocio();
        s.asignarRutina(rutinaConEjercicio("A"));
        s.asignarRutina(rutinaConEjercicio("B"));
        assertEquals("B", s.consultarRutina().getNombre());
    }

    @Test
    void noSeAsignaUnaRutinaSinEjercicios() {
        Socio s = nuevoSocio();
        assertThrows(IllegalStateException.class,
                () -> s.asignarRutina(new Rutina("Vacía", "objetivo", "nivel")));
    }

    @Test
    void asignarLaMismaRutinaDosVecesNoLaDuplica() {
        Socio s = nuevoSocio();
        Rutina a = rutinaConEjercicio("A");
        s.asignarRutina(a);
        s.asignarRutina(rutinaConEjercicio("B"));
        s.asignarRutina(a);
        assertEquals("B", s.consultarRutina().getNombre());
    }

    @Test
    void sinMembresiaVigenteDevuelveFalse() {
        Socio s = nuevoSocio();
        assertFalse(s.tieneMembresiaVigente());
        s.agregarMembresia(new Membresia("Mensual", LocalDate.now().minusMonths(3), 90000));
        assertFalse(s.tieneMembresiaVigente());
    }

    @Test
    void conUnaMembresiaVigenteDevuelveTrue() {
        Socio s = nuevoSocio();
        s.agregarMembresia(new Membresia("Mensual", LocalDate.now().minusMonths(3), 90000));
        s.agregarMembresia(new Membresia("Anual", LocalDate.now(), 800000));
        assertTrue(s.tieneMembresiaVigente());
    }
}
