package dao.controller;

import dao.model.SearchResulSchema;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.json.simple.parser.ParseException;

@ManagedBean
@SessionScoped
public class DocumentosResultado implements Serializable {

    private List<SearchResulSchema> docu;
    private String searchValue;

    @ManagedProperty("#{documentService}")
    private DocumentService service;

    public void search() {
        try {
            docu = service.getDoc(searchValue);
            for (SearchResulSchema searchResulSchema : docu) {
                System.out.println(searchResulSchema);
            }
        } catch (ParseException ex) {
            Logger.getLogger(DocumentosResultado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SearchResulSchema> getDocu() {
        return docu;
    }

    public void setService(DocumentService service) {
        this.service = service;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

}
