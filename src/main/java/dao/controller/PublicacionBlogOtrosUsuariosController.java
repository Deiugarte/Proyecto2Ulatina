package dao.controller;

import dao.model.PublicacionBlog;
import dao.service.PublicacionBlogDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "publicacionBlogOtrosUsuariosController")
@SessionScoped
public class PublicacionBlogOtrosUsuariosController implements Serializable {

    private int author;
    private String title, content, nombre, value;
    private Date creation;
    private List<PublicacionBlog> blogs = new ArrayList<>();
    private PublicacionBlog selectedPub;

    public PublicacionBlogOtrosUsuariosController() {

    }

    public PublicacionBlogOtrosUsuariosController(int author, String title, String content, Date creation) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.creation = creation;
    }

    public String submitEntry(int auth) {
        author = auth;
        PublicacionBlogDao blogDao = new PublicacionBlogDao();
        blogDao.nuevaPublicacion(new PublicacionBlog(this.author, this.title, this.content, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now())));
        return "Blog";
    }

    public List<PublicacionBlog> buscarPublicacionesPorAutor(String author) {
        PublicacionBlogDao blogDao = new PublicacionBlogDao();
        blogs = blogDao.buscarPublicacionesPorAutor(author);
        for (PublicacionBlog pub : blogs) {
            System.out.println(pub);
        }
        return blogs;
    }

    public void onRowSelect(SelectEvent event) {
        //Coleccion selectedColeccion = (Coleccion)
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<PublicacionBlog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<PublicacionBlog> blogs) {
        this.blogs = blogs;
    }

    public PublicacionBlog getSelectedPub() {
        return selectedPub;
    }

    public void setSelectedPub(PublicacionBlog selectedPub) {
        this.selectedPub = selectedPub;
    }

    public void setPub(PublicacionBlog pub) {
        this.selectedPub = pub;
    }

    @Override
    public String toString() {
        return "BlogController{" + "author=" + author + ", title=" + title + ", content=" + content + ", creation=" + creation + '}';
    }

}
