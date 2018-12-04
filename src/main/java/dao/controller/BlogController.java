package dao.controller;

import dao.model.PublicacionBlog;
import dao.model.Usuario;
import dao.service.BlogDao;
import dao.service.UsuarioDao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author blaken
 */
@ManagedBean(name = "blogController")
@SessionScoped
public class BlogController implements Serializable {
    private int author;
    private String title, content;
    private Date creation;

    public BlogController() {
    }

    public BlogController(int author, String title, String content, Date creation) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.creation = creation;
    }

    public String test(){
        BlogDao blogDao = new BlogDao();
        return blogDao.buscarPublicaciones().get(0).getContent();
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

    @Override
    public String toString() {
        return "BlogController{" + "author=" + author + ", title=" + title + ", content=" + content + ", creation=" + creation + '}';
    }
    
    
    
    



}
