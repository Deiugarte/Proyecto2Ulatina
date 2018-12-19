package dao.controller;

import dao.model.Tema;
import dao.model.Wiki;
import dao.service.TemaDao;
import dao.service.WikiDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jose Pablo
 */
@ManagedBean(name = "wikiController")
@SessionScoped
public class WikiController implements Serializable {

    private int idWiki, idAutor, idTema;
    private String titulo, contenido, nombreAutor, tema;
    private List<Wiki> wikis = new ArrayList<>();
    private List<Wiki> wikisCargar = new ArrayList<>();
    private String value;

    public WikiController() {

    }

    @PostConstruct
    public void init() {
        buscar();
    }

    public String crearWiki() {
        WikiDao wikDao = new WikiDao();
        TemaDao temDao = new TemaDao();
        Wiki wik = new Wiki(this.idWiki, this.titulo, this.contenido, this.idAutor, this.idTema, this.tema);
        Tema tem = new Tema(this.idTema, this.tema);
        try {
            temDao.insert(tem);
            wikDao.insert(wik, temDao.insert(tem));
            return "Login";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Wiki> buscar() {
        WikiDao wiki = new WikiDao();
        wikisCargar = wiki.buscar();
        for (Wiki wik : wikisCargar) {
            System.out.println(wik);
        }
        return wikisCargar;
    }

    public int getIdWiki() {
        return idWiki;
    }

    public void setIdWiki(int idWiki) {
        this.idWiki = idWiki;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public List<Wiki> getWikis() {
        return wikis;
    }

    public void setWikis(List<Wiki> wikis) {
        this.wikis = wikis;
    }

    public List<Wiki> getWikisCargar() {
        return wikisCargar;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
