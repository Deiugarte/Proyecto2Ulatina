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

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //--------- Restablecer Contrasena" -----------
    private String correo; //SOLO PARA RESTABLECER CONTRASENA, 
                          //USAR USER PARA TODO LO DEMAS...
    
    private String auxPass; // SOLO PARA RESTABLECER CONTRASENA,
                           // USAR PASS PARA TODO LO DEMAS...
    
    private String veriCode = null, code;
    //---------- FIN Restablecer contrasena ---------
    
    private boolean logeado = false;
    
    private int intentos = 0; // para bloquear cuenta...

    private int id;
    private String user, pass;
    private String nombre, ape, ape2, naci, sexo, tel;
    private boolean estado = false;   
    
    Usuario usuario = new Usuario();
    
    public LoginController() {
        
    }
    
    public String login() {
        UsuarioDao users = new UsuarioDao();

        if (users.isBlocked(this.user)) {
            return "CuentaBloqueada";
        } else if (loginUserWithCredentials(users)) {
            return "Bienvenida";
        }
        return "";
    }

    private boolean loginUserWithCredentials(UsuarioDao users) {
        FacesMessage msg;
        Usuario userLogin = users.getUser(this.user, this.pass);
        if (userLogin != null) {
            usuario = userLogin;
            id = usuario.getId();
            logeado = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.user);
            return true;
        } else {
            logeado = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
            intentos++;
            if (intentos > 2) {
                users.setEstado(this.estado, this.user);
            }
        }
        return false;
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

    public String nuevaContra() {
        if (code.equals(veriCode)) {          
            if (this.pass.equals(this.auxPass)) {
                UsuarioDao usuerNewPass = new UsuarioDao();
                usuerNewPass.nuevaContra(this.pass, this.user);
                usuerNewPass.desbloqueo(this.user);
                return "Login";
            } else {
                return "ErrorContra";
            }
        }
        
        else {
            return "ErrorContra";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
        public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
