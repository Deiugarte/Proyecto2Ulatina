/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.service;

import dao.model.Tema;
import dao.model.Wiki;
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
public class TemaDao implements IDao<Tema> {

    private final Conector conectorJDBC = Conector.getConector();
    private static final Logger LOG = LogManager.getLogger(UsuarioDao.class.getName());
    private int insertId = 0;

    @Override
    public List<Tema> buscar() {
        Connection conn = conectorJDBC.conectar();
        List<Tema> temas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from Temas");
            ps.executeQuery();
            while (rs.next()) {
                temas.add(new Tema(rs.getInt("idTemas"), rs.getString("Tema")));
            }
        } catch (SQLException e) {
            LOG.error("No se pudo realizar la busqueda... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return temas;
    }

    @Override
    public int insert(Tema data) {
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("insert into Temas (idTemas,Tema) values (?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, data.getIdTema());
            ps.setString(2, data.getDescripcion());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertId = rs.getInt(1);
                System.out.println(insertId);
            }
        } catch (SQLException e) {
            LOG.error("No se pudo insertar el tema... ", e);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return insertId;
    }

    @Override
    public void delete(Tema data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Tema data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getInsertId() {
        return insertId;
    }

    public void setInsertId(int insertId) {
        this.insertId = insertId;
    }

}
