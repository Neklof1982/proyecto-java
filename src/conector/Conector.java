package conector;

import back.Usuario;
import back.UsuarioDAO;
import com.google.gson.Gson;


public class Conector {

    public static Usuario obtenerUsuarioCon(String nombre, String contrasenia) {
        Usuario usuario = new Usuario(nombre, contrasenia);
        Gson gson = new Gson();
        LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario, Usuario.class));
        return UsuarioDAO.obtenerUsuario();
    }

    public static boolean comprobarConexion() {
        return UsuarioDAO.comprobarConexion();
    }

    public static boolean crearUsuarioCon(String nombre, String apellido, String contrasenia) {
        Usuario usuario = new Usuario(nombre, contrasenia);
        usuario.setApellido(apellido);
        Gson gson = new Gson();
        LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario, Usuario.class));
        return UsuarioDAO.crearUsuario();
    }

    public static boolean borrarUsuarioCon(String nombre, String contrasenia) {
        Usuario usuario = new Usuario(nombre, contrasenia);
        Gson gson = new Gson();
        LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario, Usuario.class));
        return UsuarioDAO.borrarUsuario();
    }

    public static boolean actualizarNotasCon(Usuario usuario) {
        Gson gson = new Gson();
        LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario, Usuario.class));
        return UsuarioDAO.actualizarNotas();
    }
}
/* Uso del try/catch

public static Usuario obtenerUsuarioCon(String nombre, String contrasenia) {
        try {
            Usuario usuario = new Usuario(nombre, contrasenia);
            Gson gson = new Gson();
            LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario, Usuario.class));
            return UsuarioDAO.obtenerUsuario();
        } catch (Exception e) {
            System.out.println("Error al obtener usuario");
            return null;
        }
    }

    public static boolean comprobarConexion() {
        try {
            return UsuarioDAO.comprobarConexion();
        } catch (Exception e) {
            System.out.println("Error al comprobar la conexión")
            return false;
        }
    }

    public static boolean crearUsuarioCon(String nombre, String apellido, String contrasenia) {
        try {
            Usuario usuario = new Usuario(nombre, contrasenia);
            usuario.setApellido(apellido);
            Gson gson = new Gson();
            LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario, Usuario.class));
            return UsuarioDAO.crearUsuario();
        } catch (Exception e) {
            System.out.println("Error al crear usuario")
            return false;
        }
    }

    public static boolean borrarUsuarioCon(String nombre, String contrasenia) {
        try {
            Usuario usuario = new Usuario(nombre, contrasenia);
            Gson gson = new Gson();
            LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario, Usuario.class));
            return UsuarioDAO.borrarUsuario();
        } catch (Exception e) {
            System.out.println("Error al borrar el usuario")
            return false;
        }
    }

    public static boolean actualizarNotasCon(Usuario usuario) {
        try {
            Gson gson = new Gson();
            LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario, Usuario.class));
            return UsuarioDAO.actualizarNotas(usuario);
        } catch (Exception e) {
            System.out.println("Error al actualizar la nota")
            return false;
        }
    }
}

 */
