import java.time.LocalDate;

public class Membresia {
    private String tipo; // Mensual, Trimestral o Anual
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int costo;

    public Membresia(String tipo, LocalDate fechaInicio, int costo) {
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.costo = costo;
        this.fechaFin = fechaInicio.plusMonths(meses());
    }

    private int meses() {
        return tipo.equalsIgnoreCase("Anual") ? 12 : tipo.equalsIgnoreCase("Trimestral") ? 3 : 1;
    }

    // Si sigue vigente suma el periodo al final actual; si venció, empieza hoy.
    public void renovar() {
        boolean vigente = estaVigente();
        LocalDate base = vigente ? fechaFin : LocalDate.now();
        if (!vigente) fechaInicio = base;
        fechaFin = base.plusMonths(meses());
    }

    public boolean estaVigente() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
    }

    @Override
    public String toString() {
        return tipo + " | " + fechaInicio + " -> " + fechaFin + " | $" + costo
                + " | " + (estaVigente() ? "VIGENTE" : "VENCIDA");
    }
}
