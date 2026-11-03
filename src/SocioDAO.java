import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*Guarda y lee socios en la base de datos.*/
public class SocioDAO {

    /*Guarda un socio en la base de datos.
    *Si ya existía un socio con ese número, le actualiza los datos.*/
    public void guardar(Socio socio) throws SQLException {
        String sql = "MERGE INTO socio (id, nombre, telefono, fecha_registro, estado) "
                + "KEY (id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, socio.getIdPersona());
            sentencia.setString(2, socio.obtenerNombre());
            sentencia.setString(3, socio.getTelefono());
            sentencia.setObject(4, socio.getFechaRegistro());
            sentencia.setString(5, socio.getEstado());
            sentencia.executeUpdate();
        }
    }

    /*Lee todos los socios guardados en la base.*/
    public List<Socio> listarTodos() throws SQLException {
        List<Socio> socios = new ArrayList<>();
        String sql = "SELECT id, nombre, telefono, fecha_registro, estado FROM socio ORDER BY id";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                socios.add(new Socio(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("telefono"),
                        resultado.getObject("fecha_registro", LocalDate.class),
                        resultado.getString("estado")));
            }
        }
        return socios;
    }
}
