package dao.controller;

import dao.model.Respuesta;
import dao.service.RespuestaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author kenne
 */
@ManagedBean(name = "foroRespuestaController")
public class ForoRespuestaController implements Serializable {

    private List<Respuesta> respues = new ArrayList<>();

    public String Respuesta(int id, int preguntaId, String respuesta, Date fecha) {
        System.out.println("PREGUNTA ID" + preguntaId);
        RespuestaDao resDao = new RespuestaDao();
        Respuesta resp = new Respuesta(0, respuesta, id, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
        try {
            int idres = resDao.insert(resp);
            resDao = new RespuestaDao();
            System.out.println("inserting this ID RESPUESTA into the db" + idres);
            System.out.println("inserting this ID PREGUNTA into the db" + preguntaId + respuesta);

            resDao.insertRP(idres, preguntaId);
            return "Foro";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Respuesta> buscarRespuestas(int idPregunta) {
        System.out.println("Me llamaron");
        RespuestaDao resDao = new RespuestaDao();
        respues = resDao.buscar(idPregunta);
        for (Respuesta respuesta : respues) {
            System.out.println(respuesta);
        }
        System.out.println("termine");
        return respues;
    }

    public List<Respuesta> getRespues() {
        return respues;
    }

    public void setRespues(List<Respuesta> respues) {
        this.respues = respues;
    }

}
