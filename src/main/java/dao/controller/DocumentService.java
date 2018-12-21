package dao.controller;

import dao.model.Documentos;
import dao.model.SearchResulSchema;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.json.simple.parser.ParseException;

/**
 *
 * @author blaken
 */
@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService {

    private static String HOST = "https://7194ec6edb754dd598f032c609d1291a.us-east-1.aws.found.io:9243";
    private static String USERNAME = "elastic";
    private static String PASSWORD = "L0zHI1afvgHoL5JWiAlrfY2D";
    
    public List<SearchResulSchema> getDoc(String dato) throws ParseException {
        Documentos doc = new Documentos(HOST, USERNAME, PASSWORD);
        return doc.busqueda(dato);
    }
}
