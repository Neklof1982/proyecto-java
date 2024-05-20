package back;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.sql.*;
import java.util.Iterator;

public class UsuarioDAO {

    public static boolean comprobarConexion() {
        Connection con;
        PreparedStatement statement;
        ResultSet rs;
        String url = "jdbc:mysql://localhost/Proyecto";
        boolean result = false;

        try {
            con = getConnection(url);
            String consulta = "select conexion() as con";
            statement = con.prepareStatement(consulta);
            rs = statement.executeQuery();

            if (rs.next()) {
                result = rs.getInt("con") == 1 ? true : false;
            }

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Usuario obtenerUsuario() {
        Connection con;
        PreparedStatement statement;
        ResultSet rs;
        String url = "jdbc:mysql://localhost/Proyecto";
        // Usuario usuario = new Usuario(contraseña, nombre);
        Usuario usuario = null;
        int contador = 0;

        usuario = getUsuario();

        try {
            con = getConnection(url);
            String consulta = "SELECT COUNT(*) , apellidos FROM usuario WHERE id_nombre = ? AND contrasenia = ?";
            statement = con.prepareStatement(consulta);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getContraseña());
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
            statement.setString(1, usuario.getNombre());
            rs = statement.executeQuery();
            while (rs.next()) {
                usuario.getListaNotas().add(new Nota(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getInt(5)));
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

    public static boolean crearUsuario() {
        Connection con;
        PreparedStatement statement;
        String consulta;
        String url = "jdbc:mysql://localhost/Proyecto";

        Usuario usuario = null;

        usuario = getUsuario();

        try {
            con = getConnection(url);
            consulta = "insert into usuario(id_nombre, apellidos, contrasenia) values (?,?,?)";
            statement = con.prepareStatement(consulta);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getContraseña());
            statement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public static boolean borrarUsuario() {
        Connection con;
        PreparedStatement statement;
        String consulta;
        String url = "jdbc:mysql://localhost/Proyecto";
        Usuario usuario = null;

        usuario = getUsuario();

        try {
            con = getConnection(url);
            consulta = "delete from usuario where id_nombre = ? and contrasenia = ?";
            statement = con.prepareStatement(consulta);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getContraseña());
            statement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public static boolean actualizarNotas() {
        Connection con;
        PreparedStatement statement;
        String consulta;
        String url = "jdbc:mysql://localhost/Proyecto";

        Usuario usu = null;

        usu = getUsuario();

        try {
            con = getConnection(url);
            consulta = "delete from notas where id_nombreFK = ?";
            statement = con.prepareStatement(consulta);
            statement.setString(1, usu.getNombre());
            statement.executeUpdate();
            Iterator<Nota> it = usu.getListaNotas().iterator();
            while (it.hasNext()) {
                Nota nota = it.next();
                consulta = "insert into notas(titulo,fecha_creacion,contenido,tamanioTexto,id_nombreFK) values (?,?,?,?,?)";
                statement = con.prepareStatement(consulta);
                statement.setString(1, nota.getTitulo());
                statement.setString(2, nota.getFechaCreacion());
                statement.setString(3, nota.getContenido());
                statement.setInt(4, nota.getTamanoTexto());
                statement.setString(5, usu.getNombre());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private static Connection getConnection(String url) throws SQLException {

        String usuario = null;
        String pass = null;
        String[] array = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./conexion.txt"));

            array = bufferedReader.readLine().split(",");

            usuario = array[0];
            pass = array[1];

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(url, usuario, pass);
    }

    private static Usuario getUsuario() {
        Usuario usu = null;

        File datosUsuariosR = new File("entrada.json");

        if (datosUsuariosR.exists()) {
            try {
                Gson gson = new Gson();

                FileReader lector = new FileReader("entrada.json");
                JsonReader lectorJson = new JsonReader(lector);

                usu = gson.fromJson(lectorJson, Usuario.class);

                lectorJson.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usu;
    }
}
