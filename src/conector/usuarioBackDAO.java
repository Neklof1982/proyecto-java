package conector;

import back.Usuario;

public interface usuarioBackDAO {

    Usuario obtenerUsuario(String nombre, String contrasenia);
    boolean crearUsuario(String nombre, String apellido, String contrasenia);
    boolean borrarUsuario(String nombre, String contrasenia);
    boolean actualizarNotas(Usuario usuario);
}

