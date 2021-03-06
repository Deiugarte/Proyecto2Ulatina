package dao.service;

import dao.model.Respuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Kenneth MC
 */
public class RespuestaDao implements IDao<Respuesta> {

    private final Conector conectorJDBC = Conector.getConector();
    private static final Logger LOG = LogManager.getLogger(UsuarioDao.class.getName());
    private int insertId;

    public List<Respuesta> buscar(int idPregun) {
        Connection conn = conectorJDBC.conectar();
        List<Respuesta> respuesta = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select resp.respuesta,per.nombre,resp.fecha\n"
                    + "from Foro fori, Respuesta resp, Persona per, Foro_Respuesta forResp\n"
                    + "where fori.idForoPregunta = forResp.foro_idPregunta\n"
                    + "and fori.idForoPregunta = ? \n"
                    + "and resp.idRespuesta = forResp.respuesta_idRespuesta\n"
                    + "and per.id = resp.Autor;");
            ps.setInt(1, idPregun);
            ps.executeQuery();
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta.add(new Respuesta(rs.getString("respuesta"), rs.getString("nombre"), rs.getTimestamp("fecha")));
            }
        } catch (SQLException e) {
            LOG.error("No se pudo realizar... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return respuesta;
    }

    @Override
    public int insert(Respuesta data) {
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("insert into Respuesta (idRespuesta,respuesta,fecha,Autor) values (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, data.getIdRespuesta());
            ps.setString(2, data.getDesrespuesta());
            ps.setDate(3, new java.sql.Date(data.getFecha().getTime()));
            ps.setInt(4, data.getIdAutor());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertId = rs.getInt(1);
                System.out.println(insertId);
            }
        } catch (SQLException e) {
            LOG.error("No se pudo insertar la Respuesta... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return insertId;
    }

    public void insertRP(int idRespuesta, int idForoPregunta) {
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("insert into Foro_Respuesta (respuesta_idRespuesta,foro_idPregunta) values (?,?);");
            ps.setInt(1, idRespuesta);
            ps.setInt(2, idForoPregunta);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("No se pudo insertar la Respuesta... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
    }

    public int getInsertId() {
        return insertId;
    }

    public void setInsertId(int insertId) {
        this.insertId = insertId;
    }

    @Override
    public void delete(Respuesta data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Respuesta data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Respuesta> buscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
