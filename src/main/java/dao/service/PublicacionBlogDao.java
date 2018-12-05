package dao.service;

import dao.model.PublicacionBlog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PublicacionBlogDao {

    private final Conector conectorJDBC = Conector.getConector();
    private static final Logger LOG = LogManager.getLogger(UsuarioDao.class.getName());

    public List<PublicacionBlog> buscarPublicaciones() {
        Connection connectionDB = conectorJDBC.conectar();
        List<PublicacionBlog> publicaciones = new ArrayList<>();
        try {
            System.out.println("Creating statement...");
            String sql;
            sql = "SELECT * FROM publicacion_blog;";
            PreparedStatement stmt = connectionDB.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                publicaciones.add(new PublicacionBlog(rs.getInt("author"), rs.getString("title"),
                        rs.getString("content"), rs.getTimestamp("creation_date")));
            }
            rs.close();
            stmt.close();
            conectorJDBC.cerrarConexion(connectionDB, stmt, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicaciones;
    }
    
        public List<PublicacionBlog> buscarPublicacionesPorCorreo(String correo) {
        Connection connectionDB = conectorJDBC.conectar();
        List<PublicacionBlog> publicaciones = new ArrayList<>();
        try {
            System.out.println("Creating statement...");
            String sql;
            sql = "SELECT * FROM publicacion_blog WHERE correo = ?;";
            PreparedStatement stmt = connectionDB.prepareStatement(sql);
             stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                publicaciones.add(new PublicacionBlog(rs.getInt("author"), rs.getString("title"),
                        rs.getString("content"), rs.getTimestamp("creation_date")));
            }
            rs.close();
            stmt.close();
            conectorJDBC.cerrarConexion(connectionDB, stmt, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicaciones;
    }

        
    public  void nuevaPublicacion(PublicacionBlog publicacion) {
        Connection connectionDB = conectorJDBC.conectar();
        PreparedStatement ps = null;
        try {
            ps = connectionDB.prepareStatement("INSERT INTO publicacion_blog (title, content, author, creation_date) VALUES (?,?,?,?);");
            ps.setString(1, publicacion.getTitle());
            ps.setString(2, publicacion.getContent());
            ps.setInt(3,publicacion.getAuthor());
            ps.setTimestamp(4, publicacion.getCreation());
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("No se puedo realizar la insercion del usuario... ", ex);
        } finally {
            conectorJDBC.cerrarConexion(connectionDB, ps, null);
        }
    }              
}
