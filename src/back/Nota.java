package back;

public class Nota implements Comparable<Nota> {

    // #### ATRIBUTOS #################

    private final int id;
    private String titulo;
    private String contenido;
    private String fechaCreacion;
    private int tamanoTexto;

    // #### CONSTRUCTOR ###############

    public Nota(int id, String titulo, String contenido,String fechaCreacion, int tamanoTexto) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.tamanoTexto = tamanoTexto;
    }

    // #### METODOS ###################

    @Override
    public int compareTo(Nota o) {

        return fechaCreacion.compareTo(o.fechaCreacion);
    }

    @Override
    public String toString() {
        return "Nota{" + "id=" + id + ", titulo='" + titulo + '\'' + ", contenido='" + contenido + '\'' + ", fechaCreacion='" + fechaCreacion + '\'' +'}';
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public int getTamanoTexto() {
        return tamanoTexto;
    }

    public void setTamanoTexto(int tamanoTexto) {
        this.tamanoTexto = tamanoTexto;
    }
}
