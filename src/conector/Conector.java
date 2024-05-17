package conector;

import back.Usuario;
import back.UsuarioDAO;
import com.google.gson.Gson;


public class Conector {

    public static Usuario obtenerUsuarioCon(String nombre, String contrasenia) {
        Usuario usuario = new Usuario(nombre,contrasenia);
        Gson gson = new Gson();
        LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario,Usuario.class));
        return UsuarioDAO.obtenerUsuario();
    }
    public static boolean crearUsuarioCon(String nombre, String contrasenia,String apellido) {
        Usuario usuario = new Usuario(nombre,contrasenia);
        usuario.setApellido(apellido);
        Gson gson = new Gson();
        LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario,Usuario.class));
        return UsuarioDAO.crearUsuario();
    }
    public static boolean borrarUsuarioCon(String nombre, String contrasenia) {
        Usuario usuario = new Usuario(nombre,contrasenia);
        Gson gson = new Gson();
        LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario,Usuario.class));
        return UsuarioDAO.borrarUsuario();
    }

    public static boolean actualizarNotasCon(Usuario usuario) {
        Gson gson = new Gson();
        LecturaEscrituraFicheros.escribirJson(gson.toJson(usuario,Usuario.class));
        return UsuarioDAO.actualizarNotas();
    }


}
