package dao.controller;

import dao.model.Foro;
import dao.model.Respuesta;
import dao.service.ForoDao;
import dao.service.RespuestaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Kenneth MC
 */
@ManagedBean(name = "foroController")
@SessionScoped
public class ForoController implements Serializable {

    private int idForo, idAutor, idRespuesta;
    private String titulo, pregunta, nombreAutor, respuesta;
    private Date fecha;
    private List<Foro> foros = new ArrayList<>();
    private Foro sltForo;
    private String pTitulo, pPregunta;
    private int pId;

    public ForoController() {

    }

    @PostConstruct
    public void cargar() {
        buscarForo();
    }

    public String crearForo(int id) {
        ForoDao forDao = new ForoDao();
        Foro foro = new Foro(this.idForo, this.titulo, this.pregunta, id, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));

        try {
            forDao.insertForo(foro);
            return "Foro";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Foro> buscarForo() {
        System.out.println("Me llamaron");
        ForoDao forDao = new ForoDao();
        foros = forDao.buscar();
        for (Foro foro : foros) {
            System.out.println(foro);
        }
        System.out.println("termine");
        return foros;
    }

    public String onRowSelect(String titulo, int idpreg, String pregunta) {
        this.pTitulo = titulo;
        this.pPregunta = pregunta;
        this.pId = idpreg;
        System.out.println(titulo + idpreg + pregunta);
        return "ForoRespuesta";
    }

    public Foro getSltForo() {
        return sltForo;
    }

    public void setSltForo(Foro sltForo) {
        this.sltForo = sltForo;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Foro> getForos() {
        return foros;
    }

    public void setForos(List<Foro> foros) {
        this.foros = foros;
    }

    public String getpTitulo() {
        return pTitulo;
    }

    public void setpTitulo(String pTitulo) {
        this.pTitulo = pTitulo;
    }

    public String getpPregunta() {
        return pPregunta;
    }

    public void setpPregunta(String pPregunta) {
        this.pPregunta = pPregunta;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

}
