package dao.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Conector {

    private static final Logger LOG = LogManager.getLogger(Conector.class.getName());
//    private final String DB_URL = "jdbc:mysql://db4free.net:3306/proy_2_ulatina";
//    
//    
//  //Database credentials
//    private final String USER = "admin_proyecto";
//    private final String PASS = "admin_proyecto";
    
    private final String DB_URL = "jdbc:mysql://localhost:3306/proy_2_ulatina";
    //  Database credentials
    private final String USER = "root";
    private final String PASS = "root";

    private static Conector conector;

    public static Conector getConector() {
        if (conector == null) {
            conector = new Conector();
        }
        return conector;
    }

    private Conector() {
    }

    public Connection conectar() {

        Connection conn = null;
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            LOG.error("Error conectando a la base de datos", ex);
        } catch (ClassNotFoundException ex) {
            LOG.error("Error conectando a la base de datos", ex);
        }
        return conn;
    }

    public void cerrarConexion(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            LOG.error("No se pudo cerrar la conexion con la base de datos", e);
        }
    }
}
