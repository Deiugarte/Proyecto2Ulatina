package dao.controller;

import dao.model.PublicacionBlog;
import dao.service.PublicacionBlogDao;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "publicacionBlogController")
@SessionScoped
public class PublicacionBlogController implements Serializable {
    private int author;
    private String title, content;
    private Date creation;

    public PublicacionBlogController() {
    }

    public PublicacionBlogController(int author, String title, String content, Date creation) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.creation = creation;
    }

    public void submitEntry(int auth){
        author = auth;
        
        PublicacionBlogDao blogDao = new PublicacionBlogDao();
        blogDao.nuevaPublicacion(new PublicacionBlog(this.author, this.title, this.content, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now())));       
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
