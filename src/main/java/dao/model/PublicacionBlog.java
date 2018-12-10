package dao.model;

import java.sql.Timestamp;

/**
 *
 * @author José Pablo
 */
public class PublicacionBlog {

    private int author;
    private String title, content;
    private Timestamp creation;
        
    public PublicacionBlog() {

    }

    public PublicacionBlog(int author, String title, String content, Timestamp creation) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.creation = creation;
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

    public Timestamp getCreation() {
        return creation;
    }

    public void setCreation(Timestamp creation) {
        this.creation = creation;
    }
    
    
}