import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EntrenadorTest {

    private Entrenador nuevoEntrenador() {
        return new Entrenador(10, "Carlos", "3151230000", "Musculación");
    }

    private Socio nuevoSocio(int id, String nombre) {
        return new Socio(id, nombre, "3000000000");
    }

    private Rutina rutinaConEjercicio() {
        Rutina r = new Rutina("Hipertrofia", "Ganar masa", "Intermedio");
        r.agregarEjercicio("Sentadilla", 4, 12);
        return r;
    }

    @Test
    void entrenadorNuevoNoTieneSocios() {
        assertEquals(0, nuevoEntrenador().consultarSocios().size());
    }

    @Test
    void agregarUnSocioLoGuarda() {
        Entrenador e = nuevoEntrenador();
        e.agregarSocio(nuevoSocio(1, "Ana"));
        assertEquals(1, e.consultarSocios().size());
    }

    @Test
    void agregarDosSociosDistintosLosGuardaAmbos() {
        Entrenador e = nuevoEntrenador();
        e.agregarSocio(nuevoSocio(1, "Ana"));
        e.agregarSocio(nuevoSocio(2, "Luis"));
        assertEquals(2, e.consultarSocios().size());
    }

    @Test
    void noDuplicaAlMismoSocio() {
        Entrenador e = nuevoEntrenador();
        Socio ana = nuevoSocio(1, "Ana");
        e.agregarSocio(ana);
        e.agregarSocio(ana);
        assertEquals(1, e.consultarSocios().size());
    }

    @Test
    void consultarSociosDevuelveElSocioAgregado() {
        Entrenador e = nuevoEntrenador();
        Socio ana = nuevoSocio(1, "Ana");
        e.agregarSocio(ana);
        assertSame(ana, e.consultarSocios().get(0));
    }

    @Test
    void asignaRutinaAUnSocioACargo() {
        Entrenador e = nuevoEntrenador();
        Socio ana = nuevoSocio(1, "Ana");
        e.agregarSocio(ana);
        e.asignarRutina(ana, rutinaConEjercicio());
        assertEquals("Hipertrofia", ana.consultarRutina().getNombre());
    }

    @Test
    void noAsignaRutinaAUnSocioQueNoTieneACargo() {
        Entrenador e = nuevoEntrenador();
        Socio ana = nuevoSocio(1, "Ana");
        assertThrows(IllegalArgumentException.class, () -> e.asignarRutina(ana, rutinaConEjercicio()));
    }

    @Test
    void elMensajeDeErrorNombraAlSocioYAlEntrenador() {
        Entrenador e = nuevoEntrenador();
        Socio ana = nuevoSocio(1, "Ana");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> e.asignarRutina(ana, rutinaConEjercicio()));
        assertEquals("Ana no está a cargo de Carlos", ex.getMessage());
    }

    @Test
    void noAsignaUnaRutinaSinEjercicios() {
        Entrenador e = nuevoEntrenador();
        Socio ana = nuevoSocio(1, "Ana");
        e.agregarSocio(ana);
        assertThrows(IllegalStateException.class,
                () -> e.asignarRutina(ana, new Rutina("Vacía", "objetivo", "nivel")));
    }

    @Test
    void toStringIncluyeLaEspecialidad() {
        assertTrue(nuevoEntrenador().toString().contains("Musculación"));
    }
}
