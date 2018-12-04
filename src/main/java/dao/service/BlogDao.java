package dao.service;

import dao.model.PublicacionBlog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlogDao {

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
                        rs.getString("content"), rs.getDate("creation_date")));
            }
            rs.close();
            stmt.close();
            conectorJDBC.cerrarConexion(connectionDB, stmt, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicaciones;
    }
}
