package dao.model;

import java.util.Date;



/**
 *
 * @author José Pablo
 */
public class Usuario {

    private String contra, correo, nombre, apellido, segundoApellido, sexo, telefono;
    private Date fechaNacimiento;
    private double calificacion;

    public Usuario() {

    }

    public Usuario(String contra, String correo, String nombre, String apellido, String segundoApellido, String sexo, String telefono, Date fechaNacimiento, double calificacion) {
        this.contra = contra;
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.segundoApellido = segundoApellido;
        this.sexo = sexo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.calificacion = calificacion;
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

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("user: ").append(this.correo);
        sb.append("\n");
        sb.append("contra: ").append(this.contra);
        sb.append("\n");
        return sb.toString();
    }

}
