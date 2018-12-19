package dao.model;

import java.sql.Timestamp;

/**
 *
 * @author Kenneth MC
 */
public class Respuesta {

    private int idRespuesta;
    private String desrespuesta;
    private Timestamp fecha;
    private String autor;
    private int idAutor;

    public Respuesta() {

    }

    public Respuesta(int idRespuesta, String desrespuesta, int idAutor, Timestamp fecha) {
        this.idRespuesta = idRespuesta;
        this.desrespuesta = desrespuesta;
        this.fecha = fecha;
        this.idAutor = idAutor;

    }

    public Respuesta(String desrespuesta, String autor, Timestamp fecha) {
        this.desrespuesta = desrespuesta;
        this.fecha = fecha;
        this.autor = autor;

    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getDesrespuesta() {
        return desrespuesta;
    }

    public void setDesrespuesta(String desrespuesta) {
        this.desrespuesta = desrespuesta;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "idRespuesta=" + idRespuesta + ", desrespuesta=" + desrespuesta + ", fecha=" + fecha + ", autor=" + autor + ", idAutor=" + idAutor + '}';
    }

}
