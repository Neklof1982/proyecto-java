package back;

public class Nota implements Comparable<Nota> {

    // #### ATRIBUTOS #################

    private final int id;
    private String titulo;
    private String contenido;

    // #### CONSTRUCTOR ###############

    public Nota(int id, String titulo, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    // #### METODOS ###################

    @Override
    public int compareTo(Nota o) {
        return id - o.getId();
    }

    @Override
    public String toString() {
        return "Nota{" + "id=" + id + ", titulo='" + titulo + '\'' + ", contenido='" + contenido + '\'' + '}';
    }

    // #### GET and SET ###############

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
