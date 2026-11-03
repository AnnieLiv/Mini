import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    
    /*Donde se guarda la base de datos.*/
    private static final String URL = "jdbc:h2:./datos/gimnasio";
    private static final String USUARIO = "sa";
    private static final String CLAVE = "";
    
    /*Se conecta con la base de datos.*/
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
    
    /*Crea las tablas del gomnasio*/
    public static void crearTablas() throws SQLException {
        try (Connection conexion = obtenerConexion();
             Statement sentencia = conexion.createStatement()) {

            sentencia.execute("CREATE TABLE IF NOT EXISTS socio ("
                    + "id INT PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "telefono VARCHAR(30), "
                    + "fecha_registro DATE, "
                    + "estado VARCHAR(20) NOT NULL)");

            sentencia.execute("CREATE TABLE IF NOT EXISTS entrenador ("
                    + "id INT PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "telefono VARCHAR(30), "
                    + "especialidad VARCHAR(100))");

            sentencia.execute("CREATE TABLE IF NOT EXISTS membresia ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "socio_id INT NOT NULL, "
                    + "tipo VARCHAR(20) NOT NULL, "
                    + "fecha_inicio DATE NOT NULL, "
                    + "fecha_fin DATE NOT NULL, "
                    + "costo INT NOT NULL, "
                    + "FOREIGN KEY (socio_id) REFERENCES socio(id))");

            sentencia.execute("CREATE TABLE IF NOT EXISTS rutina ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "objetivo VARCHAR(100), "
                    + "nivel VARCHAR(50))");

            sentencia.execute("CREATE TABLE IF NOT EXISTS ejercicio ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "rutina_id INT NOT NULL, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "series INT NOT NULL, "
                    + "repeticiones INT NOT NULL, "
                    + "FOREIGN KEY (rutina_id) REFERENCES rutina(id))");

            sentencia.execute("CREATE TABLE IF NOT EXISTS entrenador_socio ("
                    + "entrenador_id INT NOT NULL, "
                    + "socio_id INT NOT NULL, "
                    + "PRIMARY KEY (entrenador_id, socio_id), "
                    + "FOREIGN KEY (entrenador_id) REFERENCES entrenador(id), "
                    + "FOREIGN KEY (socio_id) REFERENCES socio(id))");

            sentencia.execute("CREATE TABLE IF NOT EXISTS socio_rutina ("
                    + "socio_id INT NOT NULL, "
                    + "rutina_id INT NOT NULL, "
                    + "PRIMARY KEY (socio_id, rutina_id), "
                    + "FOREIGN KEY (socio_id) REFERENCES socio(id), "
                    + "FOREIGN KEY (rutina_id) REFERENCES rutina(id))");
        }
    }
}

