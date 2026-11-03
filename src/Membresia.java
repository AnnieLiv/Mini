import java.time.LocalDate;

public class Membresia {
    private String tipo; // Mensual, Trimestral o Anual
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int costo;
    
    /*Crea la membresía*/
    public Membresia(String tipo, LocalDate fechaInicio, int costo) {
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.costo = costo;
        this.fechaFin = fechaInicio.plusMonths(meses());
    }
    /*Dice cuántos meses dura esta membresía según su tipo.*/
    private int meses() {
        return tipo.equalsIgnoreCase("Anual") ? 12 : tipo.equalsIgnoreCase("Trimestral") ? 3 : 1;
    }

    /*Renueva la membresía*/
    public void renovar() {
        boolean vigente = estaVigente();
        LocalDate base = vigente ? fechaFin : LocalDate.now();
        if (!vigente) fechaInicio = base;
        fechaFin = base.plusMonths(meses());
    }
    
    /*Dice si la membresía todavía sirve*/
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
