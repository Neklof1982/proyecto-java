package back;

import java.sql.*;

public class UsuarioDAO {

    public static Usuario obtenerUsuario(String nombre, String contraseña) {
        Connection con;
        Statement sentencia;
        ResultSet rs;
        String sql;
        String url = "jdbc:mysql://localhost/Projecto";
        Usuario usuario = new Usuario("a", "pepe");
        int contador = 0;

        try {
            con = DriverManager.getConnection(url, "mysql", "mysql");
            sentencia = con.createStatement();
            sql = "select count(*) from usuario where nombre = " + nombre + " && contraseña = " + contraseña;
            rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                contador += rs.getInt(1);
            }
            if (contador == 0) {
                return null;
            }
            sql = "select * from notas where nombre = " + nombre;
            rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                usuario.getListaNotas().add(new Nota(rs.getInt(1), rs.getString(2), rs.getString(4)));
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    // public static boolean crearUsuario(String nombre, String contraseña) {

    // }
}
