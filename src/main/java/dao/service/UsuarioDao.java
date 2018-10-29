package dao.service;

import dao.model.Usuario;
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
 * @author José Pablo
 */
public class UsuarioDao implements IDao<Usuario> {
    private final Conector conectorJDBC  = Conector.getConector();
    private static final Logger LOG = LogManager.getLogger(UsuarioDao.class.getName());

    @Override
    public List<Usuario> buscarUsuarios() {
        Connection connectionDB = conectorJDBC.conectar();
        List<Usuario> usuarios = new ArrayList<>();
        try {

            System.out.println("Creating statement...");

            String sql;
            sql = "SELECT * FROM Persona;";
            PreparedStatement stmt = connectionDB.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getString("contrasena"), rs.getString("correo"),
                        rs.getString("nombre"), rs.getString("apellido"), rs.getString("segundoApellido"),
                        rs.getString("sexo"), rs.getString("telefono"),
                        rs.getDate("fechaNacimiento"), rs.getDouble("calificacion")));
            }
            rs.close();
            stmt.close();
            conectorJDBC.cerrarConexion(connectionDB, stmt, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    @Override
    public void insert(Usuario usuario) {
        Connection connectionDB = conectorJDBC.conectar();
        PreparedStatement ps = null;
        try {
            ps = connectionDB.prepareStatement("INSERT INTO Persona (nombre, apellido, segundoApellido, contrasena, correo, fechaNacimiento, sexo, telefono, calificacion)  values (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3,usuario.getSegundoApellido());
            ps.setString(4, usuario.getContra());
            ps.setString(5, usuario.getCorreo());
            ps.setDate(6, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            ps.setString(7, usuario.getSexo());
            ps.setString(8, usuario.getTelefono());
            ps.setDouble(9, usuario.getCalificacion());
            ps.executeUpdate();

        } catch (SQLException ex) {
            LOG.error("No se puedo realizar la insercion del usuario... ", ex);
        } finally {
            conectorJDBC.cerrarConexion(connectionDB, ps, null);
        }

    }
    
    public Usuario getUser(String name, String password) {
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario userLogged = null;
        try {
            ps = conn.prepareStatement("Select * from Persona where nombre=? and contrasena=? ");
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                userLogged = new Usuario(rs.getString("contrasena"), rs.getString("correo"),
                        rs.getString("nombre"), rs.getString("apellido"), rs.getString("segundoApellido"),
                        rs.getString("sexo"), rs.getString("telefono"),
                        rs.getDate("fechaNacimiento"), rs.getDouble("calificacion"));
            }
            return userLogged;
        } catch (SQLException ex) {
            LOG.error("No se pudo obtener el usuario", ex);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return userLogged;
    }


    @Override
    public void delete(Usuario data) {

    }

    @Override
    public void update(Usuario data) {

    }

}
