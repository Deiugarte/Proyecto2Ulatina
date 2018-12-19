package dao.service;

import dao.model.Foro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Kenneth MC
 */
public class ForoDao implements IDao<Foro> {

    private final Conector conectorJDBC = Conector.getConector();
    private static final Logger LOG = LogManager.getLogger(UsuarioDao.class.getName());

    @Override
    public List<Foro> buscar() {
        Connection conn = conectorJDBC.conectar();
        List<Foro> foros = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select forr.idForoPregunta, forr.titulo, forr.pregunta, forr.fecha, per.nombre from Foro forr join Persona per on forr.autor = per.id");
            rs = ps.executeQuery();
            while (rs.next()) {
                foros.add(new Foro(rs.getInt("idForoPregunta"), rs.getString("titulo"), rs.getString("pregunta"), rs.getString("nombre"), rs.getDate("fecha")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("No se pudo realizar... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return foros;
    }


    public void insertForo(Foro data) {
        int insertId = 0;
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("insert into Foro (idForoPregunta,titulo,pregunta,fecha,autor) values (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, data.getIdForo());
            ps.setString(2, data.getTitulo());
            ps.setString(3, data.getPregunta());
            ps.setDate(4, new java.sql.Date(data.getFecha().getTime()));
            ps.setInt(5, data.getIdAutor());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertId = rs.getInt(1);
                System.out.println(insertId);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Foro> foros(String pregunta) {
        Connection conn = conectorJDBC.conectar();
        List<Foro> foros = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT f.* , p.nombre FROM Foro as f join Persona p on p.id = f.autor where f.titulo=?");
            ps.setString(1, pregunta);
            rs = ps.executeQuery();
            while (rs.next()) {
                foros.add(new Foro(rs.getString("titulo"), rs.getString("pregunta"), rs.getString("nombre"), rs.getDate("fecha")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("No se pudo realizar... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return foros;
    }

    @Override
    public int insert(Foro data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Foro data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Foro data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
