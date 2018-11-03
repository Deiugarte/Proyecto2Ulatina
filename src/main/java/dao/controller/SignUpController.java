package dao.controller;

import dao.model.Usuario;
import dao.service.UsuarioDao;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author blaken
 */
@ManagedBean(name = "signUpController")
@SessionScoped
public class SignUpController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String contra, correo, nombre, apellido, segundoApellido, sexo, telefono;
    private Date fechaNacimiento;
    private boolean estado = true;

    public SignUpController() {
    }

    public String signUp() {
        UsuarioDao userDao = new UsuarioDao();
        Usuario usuario = new Usuario(this.contra, this.correo, this.nombre, this.apellido, this.segundoApellido, this.sexo, this.telefono, this.fechaNacimiento, 0.0, this.estado);
        userDao.insert(usuario);
        return "Login";
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
