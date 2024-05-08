package back;

import java.sql.*;

public class UsuarioDAO {

    public static Usuario obtenerUsuario(String nombre, String contraseña) {
        Connection con;
        Statement sentencia;
        ResultSet rs;
        String sql;
        String url = "jdbc:mysql://localhost/Proyecto";
        Usuario usuario = new Usuario(nombre, contraseña);
        int contador = 0;

        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sentencia = con.createStatement();
            String consulta = "SELECT COUNT(*) FROM usuario WHERE id_nombre = ? AND contraseña = ?";
            PreparedStatement statement = con.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, contraseña);
            rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
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
