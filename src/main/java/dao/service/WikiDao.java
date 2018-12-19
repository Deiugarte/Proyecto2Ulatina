package dao.service;

import dao.model.Wiki;
import java.awt.PageAttributes;
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
 * @author Jose Pablo
 */
public class WikiDao implements IDao<Wiki> {

    private final Conector conectorJDBC = Conector.getConector();
    private static final Logger LOG = LogManager.getLogger(UsuarioDao.class.getName());

    @Override
    public List<Wiki> buscar() {
        Connection conn = conectorJDBC.conectar();
        List<Wiki> wikis = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT \n"
                    + "wik.Titulo, wik.Contenido, per.nombre, tem.Tema\n"
                    + "FROM\n"
                    + "    Wiki AS wik,\n"
                    + "    Temas AS tem,\n"
                    + "    Persona AS per,\n"
                    + "    Temas_Wiki AS temWik\n"
                    + "WHERE\n"
                    + "    wik.idWiki = temWik.id_wiki\n"
                    + "        AND tem.idTemas = temWik.id_temas\n"
                    + "        AND per.id = wik.Autor;");
            rs = ps.executeQuery();
            while (rs.next()) {
                wikis.add(new Wiki(rs.getString("Titulo"), rs.getString("Contenido"), rs.getString("nombre"), rs.getString("Tema")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("No se pudo realizar la busqueda... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return wikis;
    }

    public void insert(Wiki data, int idTema) {
        int insertId = 0;
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        PreparedStatement ps_2 = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("insert into Wiki (idWiki,Titulo,Contenido,Autor) values (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, data.getIdWiki());
            ps.setString(2, data.getTitulo());
            ps.setString(3, data.getContenido());
            ps.setInt(4, data.getIdAutor());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertId = rs.getInt(1);
                System.out.println(insertId);
            }
            ps_2 = conn.prepareStatement("insert into Temas_Wiki (id_wiki,id_temas) values (?,?);");
            ps_2.setInt(1, insertId);
            ps_2.setInt(2, idTema);
            ps_2.executeUpdate();
            ps_2.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Wiki> wikis(String tema) {
        Connection conn = conectorJDBC.conectar();
        List<Wiki> wikis = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT wik.Titulo, wik.Contenido, per.nombre, tem.Tema FROM Wiki AS wik,Temas AS tem, Persona AS per, Temas_Wiki AS temWik WHERE wik.idWiki = temWik.id_wiki AND tem.idTemas = temWik.id_temas AND per.id = wik.Autor AND tem.Tema = ?;");
            ps.setString(1, tema);
            rs = ps.executeQuery();
            while (rs.next()) {
                wikis.add(new Wiki(rs.getString("Titulo"), rs.getString("Contenido"), rs.getString("nombre"), rs.getString("Tema")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("No se pudo realizar la busqueda... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return wikis;
    }

    @Override
    public int insert(Wiki data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Wiki data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Wiki data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
