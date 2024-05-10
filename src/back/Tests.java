package back;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class Tests {

    public static void main(String[] args) {
        // test1();
        // test3();
        // crearDatosJson();
        // Login login = new Login();
        // solicitarUsu();
        charToString();
    }

    private static void charToString() {
        char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f' };
        System.out.println(String.valueOf(chars));
    }

    private static void solicitarUsu() {
        Usuario usu = UsuarioDAO.obtenerUsuario("Usuario2","Contraseña2");
        System.out.println(usu);
    }

    private static void crearDatosJson()  {
        Gson gson = new Gson();
        Usuario usu = new Usuario("123","Alvaro");

        TreeSet<Nota> notas = new TreeSet<Nota>();
        for (int i = 0; i < 10; i++) {
            notas.add(new Nota(i, "nota " + i, "contenido " + i ));
        }

        usu.setListaNotas(notas);

        try {
            BufferedWriter datos = new BufferedWriter(new FileWriter("datosUsuario.json"));

            String json = gson.toJson(usu);
            datos.write(json);

            datos.close();
        } catch (IOException e) {
            System.err.println("error");
        }
    }

    private static void test3(){
        Usuario usu = UsuarioDAO.obtenerUsuario("Usuario1","Contraseña1");
        usu.getListaNotas().add(new Nota(usu.getListaNotas().last().getId() + 1,"notaPrueba","hola buenas"));
        System.out.println(usu);
        UsuarioDAO.actualizarNotas(usu);
        usu = UsuarioDAO.obtenerUsuario("Usuario1","Contraseña1");
        System.out.println(usu);
    }

    private static void test2() {
        Usuario usu = new Usuario("123", "Álvaro");

        TreeSet<Nota> notas = new TreeSet<Nota>();

        for (int i = 0; i < 100; i++) {
            notas.add(new Nota(i, "Mi nota " + i, "Esta es la nota " + i));
        }

        usu.setListaNotas(notas);

        System.out.println(usu);
    }

    private static void test1() {

        System.out.println(UsuarioDAO.borrarUsuario("Usuario1","Contraseña1"));
    }
}
