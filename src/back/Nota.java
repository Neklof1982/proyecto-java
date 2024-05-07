package back;

public class Nota implements Comparable<Nota> {

    // #### ATRIBUTOS #################

    private final int id;
    private String nombre;
    private String nota;


// #### CONSTRUCTOR ###############

    public Nota(int id, String nombre, String nota) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
    }

    // #### GET and SET ###############

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public int compareTo(Nota o) {
        return id - o.getId();
    }
}
