package dao.model;

/**
 *
 * @author Jose Pablo
 */
public class Tema {

    private int idTema;
    private String descripcion;

    public Tema() {

    }

    public Tema(int idTema, String descripcion) {
        this.idTema = idTema;
        this.descripcion = descripcion;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
