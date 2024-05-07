package back;

import java.util.Set;
import java.util.TreeSet;

public class Usuario {
    private String nombre;
    private String contraseña;
    private Set<Nota> listaNotas = new TreeSet<Nota>();

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

    public Set<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(Set<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }
}
