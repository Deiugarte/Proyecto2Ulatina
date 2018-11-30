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

public class UsuarioDao implements IDao<Usuario> {

    private final Conector conectorJDBC = Conector.getConector();
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
                        rs.getDate("fechaNacimiento"), rs.getDouble("calificacion"), rs.getBoolean("estado")));
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
            ps = connectionDB.prepareStatement("INSERT INTO Persona (nombre, apellido, segundoApellido, contrasena, correo, fechaNacimiento, sexo, telefono, calificacion,estado)  values (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getSegundoApellido());
            ps.setString(4, usuario.getContra());
            ps.setString(5, usuario.getCorreo());
            ps.setDate(6, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            ps.setString(7, usuario.getSexo());
            ps.setString(8, usuario.getTelefono());
            ps.setDouble(9, usuario.getCalificacion());
            ps.setBoolean(10, true);
            ps.executeUpdate();

        } catch (SQLException ex) {
            LOG.error("No se puedo realizar la insercion del usuario... ", ex);
        } finally {
            conectorJDBC.cerrarConexion(connectionDB, ps, null);
        }

    }

    public Usuario getUser(String correo, String password) {
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario userLogged = null;
        try {
            ps = conn.prepareStatement("Select * from Persona where correo=? and contrasena=? ");
            ps.setString(1, correo);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                userLogged = new Usuario(rs.getString("contrasena"), rs.getString("correo"),
                        rs.getString("nombre"), rs.getString("apellido"), rs.getString("segundoApellido"),
                        rs.getString("sexo"), rs.getString("telefono"),
                        rs.getDate("fechaNacimiento"), rs.getDouble("calificacion"), rs.getBoolean("estado"));
            }
            System.out.println(userLogged.getNombre());
            return userLogged;
        } catch (SQLException ex) {
            LOG.error("No se pudo obtener el usuario", ex);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return userLogged;
    }
    
    public boolean isBlocked(String correo){
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean block = false;
        try {
            ps = conn.prepareStatement("SELECT estado FROM Persona WHERE correo=?");
            ps.setString(1, correo);
            rs = ps.executeQuery();
            while(rs.next()){
            block = !rs.getBoolean("estado");
            }
        } catch (SQLException ex) {
            LOG.error("Error al intentar leer datos.", ex);
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return block;
    }
    
    public void setEstado(boolean estado, String nombre) {
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("Update Persona set estado = ? where correo = ?");
            ps.setBoolean(1, estado);
            ps.setString(2, nombre);
            ps.execute();
        } catch (SQLException ex) {
            ex.getStackTrace();
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
    }

    public Usuario nuevaContra(String newPass, String correo) {
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario nuevaContra = null;
        try {
            ps = conn.prepareStatement("Update Persona set contrasena = ? where correo = ?");
            ps.setString(1, newPass);
            ps.setString(2, correo);
            ps.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return nuevaContra;
    }

    public Usuario desbloqueo(String correoVerification) {
        Connection conn = conectorJDBC.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario nuevaContra = null;
        try {
            ps = conn.prepareStatement("Update Persona set estado = 0 where correo = ?");
            ps.setString(1, correoVerification);
            ps.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            conectorJDBC.cerrarConexion(conn, ps, rs);
        }
        return nuevaContra;
    }

    @Override
    public void delete(Usuario data) {

    }

    @Override
    public void update(Usuario data) {

    }

}
