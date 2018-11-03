package dao.controller;

import dao.model.*;
import dao.service.*;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author José Pablo
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user, pass, auxPass, correo;
    private boolean logeado = false;
    private String veriCode = null, code;
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

    public String restablecerContrasena() {
        return "RestablecerContrasena";
    }

    public String emailNuevaContrasena() throws EmailException {
        //credentials for email and email account

        veriCode = Integer.toString(((int) (Math.random() * ((9999 - 1010) + 1))));

        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("proyecto2ulatina@gmail.com", "proyecto_2_ulatina"));
        email.setSSLOnConnect(true);
        email.setFrom("proyecto2ulatina@gmail.com");
        email.setSubject("Restablecer Contrasena");
        email.setMsg("Su codigo de verificacion es: " + veriCode + " y expirará en 2 horas (not really...).\n");
        email.addTo(this.correo);
        email.send();
        return "NuevaContrasena";
    }

    public String emailDesbloqueo() throws EmailException {
        //credentials for email and email account

        veriCode = Integer.toString(((int) (Math.random() * ((9999 - 1010) + 1))));

        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("proyecto2ulatina@gmail.com", "proyecto_2_ulatina"));
        email.setSSLOnConnect(true);
        email.setFrom("proyecto2ulatina@gmail.com");
        email.setSubject("Restablecer Contrasena");
        email.setMsg("Su codigo de desbloqueo es: " + veriCode + " y expirará en 2 horas (not really...).\n");
        email.addTo(this.correo);
        email.send();
        return "ConfirmaryDesbloquear";
    }

    public String nuevaContra() {
        UsuarioDao usuerNewPass = new UsuarioDao();
        usuerNewPass.nuevaContra(this.pass,this.correo);
        usuerNewPass.desbloqueo(this.correo);
        return "Login";
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

    public String getVeriCode() {
        return veriCode;
    }

    public void setVeriCode(String veriCode) {
        this.veriCode = veriCode;
    }

    public String getAuxPass() {
        return auxPass;
    }

    public void setAuxPass(String oldPass) {
        this.auxPass = oldPass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
