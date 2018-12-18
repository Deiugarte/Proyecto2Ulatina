package dao.controller;

import dao.model.*;
import dao.service.PublicacionBlogDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "blogController")
@SessionScoped
public class BlogController implements Serializable {
    private static final long serialVersionUID = 1L;
    List<PublicacionBlog> publicaciones = new ArrayList<>();
    PublicacionBlogDao publicacionBlogDao;

//    public BlogController() {
//        publicaciones  = cargarPublicacionesBlog(14);
//        
//    }
    
//    public List<PublicacionBlog> cargarPublicacionesBlog(int ID){
//        return publicacionBlogDao.buscarPublicacionesPorID(ID);        
//    }

    public List<PublicacionBlog> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PublicacionBlog> publicaciones) {
        this.publicaciones = publicaciones;
    }
    
    
    
    
}
