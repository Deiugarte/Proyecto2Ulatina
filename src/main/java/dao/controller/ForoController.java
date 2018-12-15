package dao.controller;

import dao.model.Foro;
import dao.model.Respuesta;
import dao.service.ForoDao;
import dao.service.RespuestaDao;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Kenneth MC
 */
@ManagedBean(name = "foroController")
@ViewScoped
public class ForoController implements Serializable {

    private int idForo, idAutor, idRespuesta;
    private String titulo, pregunta, nombreAutor, respuesta;
    private Date fecha;
    private List<Foro> foros = new ArrayList<>();

    private static String tituloView;
    private static String preguntaView;

    public ForoController() {
        init();

    }

    public void init() {

        this.buscarForo();

    }

    public void select(Foro foro) {
       
        ForoDao dao = new ForoDao();
        dao.dat(foro.getTitulo());
        this.setTitulo(foro.getTitulo());
        this.setPregunta(foro.getPregunta());

    }

    public String crearForo(int id) {
        ForoDao forDao = new ForoDao();
        Foro foro = new Foro(this.idForo, this.titulo, this.pregunta, id, this.fecha);

        try {
            forDao.insertForo(foro);
            return "Foro";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String Respuesta(int id) {
        RespuestaDao resDao = new RespuestaDao();
        Respuesta resp = new Respuesta(this.idRespuesta, this.respuesta, id, this.fecha);
        System.out.println(id);
        try {
            int idRespuesta = resDao.insert(resp);
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

    public void onRowSelect(SelectEvent event) throws IOException, ClassNotFoundException, SQLException {
        String t = "ForoRespuesta.xhtml";
        ForoController foro = new ForoController();
        Foro selectedColeccion = ((Foro) event.getObject());
        foro.setTituloView(selectedColeccion.getTitulo());
        this.setTituloView(foro.getTituloView());
        foro.setPreguntaView(selectedColeccion.getPregunta());
        this.setPreguntaView(foro.getPreguntaView());
        this.select(selectedColeccion);
        foro.redirect(t);
        
    }
        public void redirect(String url) {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static String getTituloView() {
        return tituloView;
    }

    public static void setTituloView(String tituloView) {
        ForoController.tituloView = tituloView;
    }

    public static String getPreguntaView() {
        return preguntaView;
    }

    public static void setPreguntaView(String preguntaView) {
        ForoController.preguntaView = preguntaView;
    }



}
