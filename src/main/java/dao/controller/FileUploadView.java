/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.controller;

import dao.model.Documentos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.json.simple.parser.ParseException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.util.Base64;

@ManagedBean
public class FileUploadView {

    private static String HOST = "https://7194ec6edb754dd598f032c609d1291a.us-east-1.aws.found.io:9243";
    private static String USERNAME = "elastic";
    private static String PASSWORD = "L0zHI1afvgHoL5JWiAlrfY2D";

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() throws IOException, ParseException {
        Documentos doc = new Documentos(HOST, USERNAME, PASSWORD);
        if (file != null) {
            System.out.println(file.getFileName());
            byte[] fileData = file.getContents();
            String encodedString = Base64.encodeToString(fileData, false);
            System.out.println(doc.subida(encodedString, file.getFileName().replaceAll("\\s", "")));
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
