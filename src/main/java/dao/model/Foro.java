package dao.model;

import java.sql.Timestamp;

/**
 *
 * @author Kenneth MC
 */
public class Foro {

    private int idForo, idAutor, idRespuesta;
    private String titulo, pregunta, nombreAutor, respuesta;
    private Timestamp fecha;
    public Foro() {

    }

    public Foro(int idForo, String titulo, String pregunta, int idAutor, Timestamp fecha, int idRespuesta, String respuesta) {
        this.idForo = idForo;
        this.titulo = titulo;
        this.pregunta = pregunta;
        this.idAutor = idAutor;
        this.idRespuesta = idRespuesta;
        this.respuesta = respuesta;
        this.fecha = fecha;
    }   

    public Foro(String titulo, String pregunta, String nombreAutor,Timestamp fecha) {
        this.titulo = titulo;
        this.pregunta = pregunta;
        this.nombreAutor = nombreAutor;
        this.fecha = fecha;
    }
        public Foro(int idForo, String titulo, String pregunta, String nombreAutor,Timestamp fecha) {
        this.titulo = titulo;
         this.idForo = idForo;
        this.pregunta = pregunta;
        this.nombreAutor = nombreAutor;
        this.fecha = fecha;
    }
        public Foro(int idForo, String titulo, String pregunta, int idAutor, Timestamp fecha) {
        this.idForo = idForo;
        this.titulo = titulo;
        this.pregunta = pregunta;
        this.idAutor = idAutor;
        this.fecha = fecha;
    }   

    public int getIdForo() {
        return idForo;
    }

    public void setIdForo(int idForo) {
        this.idForo = idForo;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }


    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Foro{" + "idForo=" + idForo + ", titulo=" + titulo + ", pregunta=" + pregunta + '}';
    }

  

}
