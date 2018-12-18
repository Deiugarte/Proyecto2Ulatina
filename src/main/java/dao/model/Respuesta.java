package dao.model;

import java.util.Date;

/**
 *
 * @author Kenneth MC
 */
public class Respuesta {

    private int idRespuesta;
    private String desrespuesta;
    private Date fecha;
    private String autor;
    private int idAutor;
    public Respuesta() {

    }

    public Respuesta(int idRespuesta, String desrespuesta,int idAutor, Date fecha) {
        this.idRespuesta = idRespuesta;
        this.desrespuesta = desrespuesta;
        this.fecha=fecha;
        this.idAutor=idAutor;
        
    }
    public Respuesta(String desrespuesta, String autor, Date fecha) {
        this.desrespuesta = desrespuesta;
        this.fecha=fecha;
        this.autor=autor;
        
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

}
