package back;

import java.util.TreeSet;

public class Usuario extends Persona{
    private String nombre;
    private String contraseña;

    private TreeSet<Nota> listaNotas = new TreeSet<Nota>();

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", listaNotas=" + listaNotas +
                '}';
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuario(String contraseña, String nombre) {
        this.contraseña = contraseña;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TreeSet<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(TreeSet<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }
}
