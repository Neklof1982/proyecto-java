package back;

import java.util.HashSet;

public class Usuario {
    private String nombre;
    private String contraseña;

    private HashSet<Nota> listaNotas = new HashSet<Nota>();

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

    public HashSet<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(HashSet<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }
}
