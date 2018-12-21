/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.controller;

import dao.model.Documentos;
import dao.model.SearchResulSchema;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.json.simple.parser.ParseException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadView {

    private static String HOST = "https://7194ec6edb754dd598f032c609d1291a.us-east-1.aws.found.io:9243";
    private static String USERNAME = "elastic";
    private static String PASSWORD = "L0zHI1afvgHoL5JWiAlrfY2D";

    private StreamedContent file;

    public FileDownloadView() throws ParseException {
        Documentos doc = new Documentos(HOST, USERNAME, PASSWORD);
        List<SearchResulSchema> resul = doc.busqueda("er");
        InputStream targetStream = new ByteArrayInputStream(resul.get(0).getContent());
        file = new DefaultStreamedContent(targetStream, resul.get(0).getType(), resul.get(0).getId());
    }

    public StreamedContent getFile(String busqueda) throws ParseException {
        Documentos doc = new Documentos(HOST, USERNAME, PASSWORD);
        SearchResulSchema resul = doc.downloadDocument(busqueda);
        InputStream targetStream = new ByteArrayInputStream(resul.getContent());
        file = new DefaultStreamedContent(targetStream, resul.getType(), resul.getId());
        return file;
    }

}
