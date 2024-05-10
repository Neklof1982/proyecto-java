package back;

public class Tests {

    public static void main(String[] args) {
         test1();
        // test3();
        // crearDatosJson();
        // Login login = new Login();
        // solicitarUsu();
        //charToString();
    }

    private static void charToString() {
        char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f' };
        System.out.println(String.valueOf(chars));
    }

    private static void solicitarUsu() {
        Usuario usu = UsuarioDAO.obtenerUsuario("Usuario2","Contraseña2");
        System.out.println(usu);
    }


    private static void test3(){
        Usuario usu = UsuarioDAO.obtenerUsuario("alvaro","123");
        System.out.println(usu);
    }


    private static void test1() {
        Usuario usu = UsuarioDAO.obtenerUsuario("alvaro","123");
        System.out.println(usu);
        System.out.println(UsuarioDAO.actualizarNotas(usu));
        usu = UsuarioDAO.obtenerUsuario("alvaro","123");
        UsuarioDAO.actualizarNotas(usu);
        System.out.println(usu);
    }
}
