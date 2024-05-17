package conector;

import back.Usuario;
import com.google.gson.Gson;

public class Conector {

    private usuarioBackDAO usuarioBDAO;

    public Conector(usuarioBackDAO usuarioBDAO) {
        this.usuarioBDAO = usuarioBDAO;
    }

    public String obtenerUsuarioEnJson(String nombre, String contraseñia) {
        Usuario usuario = usuarioBDAO.obtenerUsuario(nombre, contrasenia);
        if (usuario != null) {
            Gson gson = new Gson();
            return gson.toJson(usuario);
        } else {
            return null;
            /*
             JsonObject errorJson = new JsonObject();
             errorJson.addProperty("error", "El usuario no fue encontrado");
             Gson gson = new Gson();
             return gson.toJson(errorJson);
             */
        }
    }
    public boolean crearUsuarioDesdeJson(String jsonUsuario) {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(jsonUsuario, Usuario.class);
        return usuarioBDAO.crearUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getContrasenia());
    }

    public boolean borrarUsuarioDesdeJson(String jsonUsuario) {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(jsonUsuario, Usuario.class);
        return usuarioBDAO.borrarUsuario(usuario.getNombre(), usuario.getContrasenia());
    }

    public boolean actualizarNotasDesdeJson(String jsonUsuario) {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(jsonUsuario, Usuario.class);
        return usuarioBDAO.actualizarNotas(usuario);
    }

}
