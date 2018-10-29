package dao.controller;

import dao.model.*;
import dao.service.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author José Pablo
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user, pass;
    private boolean logeado = false;

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
            return "bienvenida";
        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Credenciales no validas");
            return "error";
        }
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

}
