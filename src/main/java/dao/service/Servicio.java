//package dao.service;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
///**
// *
// * @author José Pablo
// */
//public class Servicio {
//
//    // JDBC driver name and database URL
//    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    String DB_URL = "jdbc:mysql://db4free.net:3306/proy_2_ulatina";
//
//    //  Database credentials
//    String user = "admin_proyecto";
//    String pass = "admin_proyecto";
//    Connection conn = null;
//
//    private void conectar() {
//
//        try {
//            //Step 1
//            Class.forName(JDBC_DRIVER);
//
//            //Step 2
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, user, pass);
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    protected Connection getConnection() {
//        this.conectar();
//        return this.conn;
//    }
//
//    protected void closeConn() {
//        try {
//            if (!conn.isClosed()) {
//                this.conn.close();
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//}
