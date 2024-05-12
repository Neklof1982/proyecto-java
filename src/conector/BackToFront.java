package conector;


public class BackToFront {
    public static final String EXITO = "EXITO";
    public static final String ERROR = "ERROR";

    // Métodos estáticos para devolver respuestas de éxito
    public static String obtenerUsuarioExitoso() {
        return EXITO + ": Usuario obtenido exitosamente";
    }

    public static String crearUsuarioExitoso() {
        return EXITO + ": Usuario creado exitosamente";
    }

    public static String borrarUsuarioExitoso() {
        return EXITO + ": Usuario borrado exitosamente";
    }

    public static String actualizarNotasExitoso() {
        return EXITO + ": Notas actualizadas exitosamente";
    }

    // Métodos estáticos para devolver respuestas de error
    public static String errorObtenerUsuario(String mensaje) {
        return ERROR + ": Error al obtener usuario - " + mensaje;
    }

    public static String errorCrearUsuario(String mensaje) {
        return ERROR + ": Error al crear usuario - " + mensaje;
    }

    public static String errorBorrarUsuario(String mensaje) {
        return ERROR + ": Error al borrar usuario - " + mensaje;
    }

    public static String errorActualizarNotas(String mensaje) {
        return ERROR + ": Error al actualizar notas - " + mensaje;
    }
}