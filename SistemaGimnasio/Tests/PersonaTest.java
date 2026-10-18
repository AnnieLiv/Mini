import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

class PersonaTest {

    private Persona nuevaPersona() {
        return new Persona(7, "Ana Torres", "3001112233") { };
    }

    @Test
    void obtenerNombreDevuelveElNombre() {
        assertEquals("Ana Torres", nuevaPersona().obtenerNombre());
    }

    @Test
    void toStringTieneElFormatoEsperado() {
        assertEquals("[7] Ana Torres (Tel: 3001112233)", nuevaPersona().toString());
    }

    @Test
    void toStringIncluyeElId() {
        assertTrue(nuevaPersona().toString().contains("[7]"));
    }

    @Test
    void toStringIncluyeElNombre() {
        assertTrue(nuevaPersona().toString().contains("Ana Torres"));
    }

    @Test
    void setTelefonoCambiaElTelefono() {
        Persona p = nuevaPersona();
        p.setTelefono("3109998877");
        assertTrue(p.toString().contains("3109998877"));
    }

    @Test
    void setTelefonoYaNoMuestraElNumeroAnterior() {
        Persona p = nuevaPersona();
        p.setTelefono("3109998877");
        assertFalse(p.toString().contains("3001112233"));
    }

    @Test
    void setTelefonoNoCambiaElNombre() {
        Persona p = nuevaPersona();
        p.setTelefono("3109998877");
        assertEquals("Ana Torres", p.obtenerNombre());
    }

    @Test
    void cambiarElTelefonoVariasVecesConservaElUltimo() {
        Persona p = nuevaPersona();
        p.setTelefono("111");
        p.setTelefono("222");
        assertEquals("[7] Ana Torres (Tel: 222)", p.toString());
    }

    @Test
    void personaEsUnaClaseAbstracta() {
        assertTrue(Modifier.isAbstract(Persona.class.getModifiers()));
    }

    @Test
    void socioYEntrenadorSonPersonas() {
        Object socio = new Socio(1, "Luis", "300");
        Object entrenador = new Entrenador(2, "Carlos", "315", "Cardio");
        assertTrue(socio instanceof Persona && entrenador instanceof Persona);
    }
}
