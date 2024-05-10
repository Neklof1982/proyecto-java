package back;

import java.sql.*;
import java.util.Iterator;

public class UsuarioDAO {

    public static Usuario obtenerUsuario(String nombre, String contraseña) {
        Connection con;
        PreparedStatement statement;
        ResultSet rs;
        String url = "jdbc:mysql://localhost/Proyecto";
        Usuario usuario = new Usuario(contraseña, nombre);
        int contador = 0;

        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            String consulta = "SELECT COUNT(*) , apellidos FROM usuario WHERE id_nombre = ? AND contrasenia = ?";
            statement = con.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, contraseña);
            rs = statement.executeQuery();
            while (rs.next()) {
                contador += rs.getInt(1);
                usuario.setApellido(rs.getString(2));
            }
            if (contador == 0) {
                return null;
            }
            consulta = "select * from notas where id_nombreFK = ?";
            statement = con.prepareStatement(consulta);
            statement.setString(1, nombre);
            rs = statement.executeQuery();
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

    public static boolean crearUsuario(String nombre,String apellido, String contraseña) {
        Connection con;
        PreparedStatement statement;
        String consulta;
        String url = "jdbc:mysql://localhost/Proyecto";

        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            consulta = "insert into usuario(id_nombre, apellidos, contrasenia) values (?,?,?)";
            statement = con.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, contraseña);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static boolean borrarUsuario(String nombre, String contraseña){
        Connection con;
        PreparedStatement statement;
        String consulta;
        String url = "jdbc:mysql://localhost/Proyecto";

        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            consulta = "delete from usuario where id_nombre = ? and contrasenia = ?";
            statement = con.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, contraseña);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static boolean actualizarNotas(Usuario usu){
        Connection con;
        PreparedStatement statement;
        String consulta;
        String url = "jdbc:mysql://localhost/Proyecto";

        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            consulta = "delete from notas where id_nombreFK = ?";
            statement = con.prepareStatement(consulta);
            statement.setString(1, usu.getNombre());
            statement.executeUpdate();
            Iterator<Nota> it = usu.getListaNotas().iterator();
            while (it.hasNext()){
                Nota nota = it.next();
                consulta = "insert into notas(titulo,contenido,id_nombreFK) values (?,?,?)";
                statement = con.prepareStatement(consulta);
                statement.setString(1, nota.getTitulo());
                statement.setString(2, nota.getContenido());
                statement.setString(3, usu.getNombre());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
