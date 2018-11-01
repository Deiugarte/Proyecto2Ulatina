package dao.controller;

import dao.model.*;
import dao.service.*;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author José Pablo
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user, pass, correo;
    private boolean logeado = false;
    private int intentos = 0;
    private boolean estado = false;

    public LoginController() {

    }

    public String login() {
        System.out.println("LOGIN");
        UsuarioDao users = new UsuarioDao();
        Usuario userLogin = users.getUser(this.user, this.pass);
        FacesMessage msg = null;

        if (userLogin != null) {
            logeado = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.user);
            return "Bienvenida";
        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Credenciales no validas");
            intentos++;
        }
        if (intentos > 2) {
            users.setEstado(this.estado, this.user);
        }
        return "";
    }

    public void logout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .invalidateSession();
            logeado = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String desbloquear() {
        return "DesbloqueoCuenta";
    }
    
    public String restablecerContrasena(){
        return "RestablecerContrasena";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
