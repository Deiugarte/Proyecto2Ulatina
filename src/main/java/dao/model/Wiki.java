package dao.model;

/**
 *
 * @author Jose Pablo
 */
public class Wiki {

    private int idWiki, idAutor, idTema;
    private String titulo, contenido, nombreAutor, tema;

    public Wiki() {

    }

    public Wiki(int idWiki, String titulo, String contenido, int idAutor, int idTema, String tema) {
        this.idWiki = idWiki;
        this.titulo = titulo;
        this.contenido = contenido;
        this.idAutor = idAutor;
        this.idTema = idTema;
        this.tema = tema;
    }

    public Wiki(String titulo, String contenido, String nombreAutor, String tema) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.nombreAutor = nombreAutor;
        this.tema = tema;
    }

    public int getIdWiki() {
        return idWiki;
    }

    public void setIdWiki(int idWiki) {
        this.idWiki = idWiki;
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

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return "Wiki{" + "titulo=" + titulo + ", contenido=" + contenido + ", nombreAutor=" + nombreAutor + ", tema=" + tema + '}';
    }

}
