/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.controller;

/**
 *
 * @author blaken
 */

public class SearchResulSchema {
    private String tittle;
    private String author;
    private byte[] content;
    private String _id;
    private String type;

    public SearchResulSchema(String tittle, String author, byte[] content, String _id, String type) {
        this.tittle = tittle;
        this.author = author;
        this.content = content;
        this._id = _id;
        this.type = type;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    @Override
    public String toString() {
        return "SearchResulSchema{" + "tittle=" + tittle + ", author=" + author  +  ", type=" + type + ", _id=" + _id + '}';
    }
    
    
}
