public class PruebaBD {
    public static void main(String[] args) throws Exception {
        ConexionBD.crearTablas();
        SocioDAO socioDao = new SocioDAO();

        int numero = socioDao.listarTodos().size() + 1;
        Socio nuevo = new Socio(numero, "Leonard Dilbert " + numero, "3456070" + numero);
        nuevo.registrar();
        socioDao.guardar(nuevo);

        System.out.println("--- Socios guardados en la base de datos ---");
        for (Socio s : socioDao.listarTodos()) {
            System.out.println(s);
        }
    }
}