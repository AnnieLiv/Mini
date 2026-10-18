import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class MembresiaTest {

    @Test
    void mensualCreadaHoyEstaVigente() {
        assertTrue(new Membresia("Mensual", LocalDate.now(), 90000).estaVigente());
    }

    @Test
    void mensualDeHaceTresMesesEstaVencida() {
        assertFalse(new Membresia("Mensual", LocalDate.now().minusMonths(3), 90000).estaVigente());
    }

    @Test
    void trimestralDeHaceDosMesesSigueVigente() {
        assertTrue(new Membresia("Trimestral", LocalDate.now().minusMonths(2), 240000).estaVigente());
    }

    @Test
    void trimestralDeHaceCuatroMesesEstaVencida() {
        assertFalse(new Membresia("Trimestral", LocalDate.now().minusMonths(4), 240000).estaVigente());
    }

    @Test
    void anualDeHaceOnceMesesSigueVigente() {
        assertTrue(new Membresia("Anual", LocalDate.now().minusMonths(11), 800000).estaVigente());
    }

    @Test
    void anualDeHaceTreceMesesEstaVencida() {
        assertFalse(new Membresia("Anual", LocalDate.now().minusMonths(13), 800000).estaVigente());
    }

    @Test
    void renovarUnaMembresiaVencidaLaDejaVigente() {
        Membresia m = new Membresia("Mensual", LocalDate.now().minusMonths(3), 90000);
        m.renovar();
        assertTrue(m.estaVigente());
    }

    @Test
    void renovarUnaMembresiaVigenteSumaElPeriodoAlFinalActual() {
        Membresia m = new Membresia("Mensual", LocalDate.now(), 90000);
        m.renovar();
        LocalDate esperado = LocalDate.now().plusMonths(1).plusMonths(1);
        assertTrue(m.toString().contains("-> " + esperado));
    }

    @Test
    void membresiaConFechaDeInicioFuturaNoEstaVigente() {
        assertFalse(new Membresia("Mensual", LocalDate.now().plusDays(10), 90000).estaVigente());
    }

    @Test
    void toStringMuestraTipoCostoYEstado() {
        String texto = new Membresia("Mensual", LocalDate.now(), 90000).toString();
        assertTrue(texto.contains("Mensual") && texto.contains("$90000") && texto.contains("VIGENTE"));
    }
}
