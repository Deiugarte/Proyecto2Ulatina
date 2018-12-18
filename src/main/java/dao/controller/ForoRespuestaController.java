/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.controller;

import dao.model.Respuesta;
import dao.service.RespuestaDao;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author kenne
 */
@ManagedBean(name = "foroRespuestaController")
public class ForoRespuestaController implements Serializable {
    
        public String Respuesta(int id, int preguntaId, String respuesta, Date fecha) {
        System.out.println("OREGUBTA ID" + preguntaId);
        RespuestaDao resDao = new RespuestaDao();
        Respuesta resp = new Respuesta(0, respuesta, id, fecha);
        try {
            int idres = resDao.insert(resp);
            resDao = new RespuestaDao();
            System.out.println("inserting this into the db" + idres);
            System.out.println("inserting this pregunta into the db" + preguntaId + respuesta);

            resDao.insertRP(idres, preguntaId);
            return "";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
