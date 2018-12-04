package dao.model;

import java.util.Date;

/**
 *
 * @author José Pablo
 */
public class PublicacionBlog {

    private int author;
    private String title, content;
    private Date creation;
        
    public PublicacionBlog() {

    }

    public PublicacionBlog(int author, String title, String content, Date creation) {
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

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
    
    
}