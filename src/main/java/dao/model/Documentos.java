/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.model;

import dao.model.SearchResulSchema;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author blaken
 */
public class Documentos {

    private String HOST;
    private String USERNAME;
    private String PASSWORD;

    public Documentos() {
    }

    public Documentos(String HOST, String USERNAME, String PASSWORD) {
        this.HOST = HOST;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public List<SearchResulSchema> busqueda(String dato) throws ParseException {
        try {

            HttpURLConnection conn = createGETConnection();
            setSearchBodyContent(conn, dato);
            String elkResponse = getResponse(conn);
            return decodeELKResponse(elkResponse);

        }
        catch (MalformedURLException e) {
        }
        catch (IOException e) {
        }
        return null;
    }

    public SearchResulSchema downloadDocument(String dato) throws ParseException {
        try {

            HttpURLConnection conn = createDownloadFileConnection(dato);
            String elkResponse = getResponse(conn);
            return decodeFILEELKResponse(elkResponse);

        }
        catch (MalformedURLException e) {
        }
        catch (IOException e) {
        }
        return null;
    }

    public String subida(String archivo, String fileId) throws ParseException {
        try {

            HttpURLConnection conn = createPUTFileConnection(fileId);
            setFileContent(conn, archivo);
            String elkResponse = getResponse(conn);
            return elkResponse;

        }
        catch (MalformedURLException e) {
        }
        catch (IOException e) {
        }
        return null;
    }

    public String borrarDocumento(String fileId) throws ParseException {
        try {

            HttpURLConnection conn = createDELETEFileConnection(fileId);
            String elkResponse = getResponse(conn);
            return elkResponse;

        }
        catch (MalformedURLException e) {
        }
        catch (IOException e) {
        }
        return null;
    }

    private static List<SearchResulSchema> decodeELKResponse(String elkResponse) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(elkResponse);
        json = (JSONObject) json.get("hits");
        JSONArray hits = (JSONArray) json.get("hits");
        List<SearchResulSchema> resul = new ArrayList<>();
        for (Object hit : hits) {
            JSONObject data = (JSONObject) ((JSONObject) hit).get("_source");
            byte[] content = String.valueOf(data.get("data")).getBytes();
            byte[] content2 = Base64.getDecoder().decode(content);
            data = (JSONObject) data.get("attachment");
            resul.add(new SearchResulSchema(String.valueOf(data.get("title")), String.valueOf(data.get("author")), content2, String.valueOf(((JSONObject) hit).get("_id")), String.valueOf(data.get("content_type"))));
        }
        return resul;
    }

    private static SearchResulSchema decodeFILEELKResponse(String elkResponse) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(elkResponse);
        JSONObject data = (JSONObject) ((JSONObject) json).get("_source");
        byte[] content = String.valueOf(data.get("data")).getBytes();
        byte[] content2 = Base64.getDecoder().decode(content);
        data = (JSONObject) data.get("attachment");
        return new SearchResulSchema(String.valueOf(data.get("title")), String.valueOf(data.get("author")), content2, String.valueOf(((JSONObject) json).get("_id")), String.valueOf(data.get("content_type")));

    }

    private String getResponse(HttpURLConnection conn) throws IOException, RuntimeException {
        if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
            System.out.println("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        StringBuilder response = new StringBuilder();
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        conn.disconnect();
        return response.toString();
    }

    private HttpURLConnection createGETConnection() throws ProtocolException, IOException, MalformedURLException {
        URL url = new URL(HOST + "/componentes/componentes/_search");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        String encoded = Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes(StandardCharsets.UTF_8));
        conn.setRequestProperty("Authorization", "Basic " + encoded);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        return conn;
    }

    private HttpURLConnection createPUTFileConnection(String fileId) throws ProtocolException, IOException, MalformedURLException {
        URL url = new URL(HOST + "/componentes/componentes/" + fileId + "?pipeline=attachment");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        String encoded = Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes(StandardCharsets.UTF_8));
        conn.setRequestProperty("Authorization", "Basic " + encoded);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        return conn;
    }

    private HttpURLConnection createDELETEFileConnection(String fileId) throws ProtocolException, IOException, MalformedURLException {
        URL url = new URL(HOST + "/componentes/componentes/" + fileId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        String encoded = Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes(StandardCharsets.UTF_8));
        conn.setRequestProperty("Authorization", "Basic " + encoded);
        return conn;
    }

    private HttpURLConnection createDownloadFileConnection(String fileId) throws ProtocolException, IOException, MalformedURLException {
        URL url = new URL(HOST + "/componentes/componentes/" + fileId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        String encoded = Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes(StandardCharsets.UTF_8));
        conn.setRequestProperty("Authorization", "Basic " + encoded);
        return conn;
    }

    private void setSearchBodyContent(HttpURLConnection conn, String dato) throws IOException {
        DataOutputStream wr;
        wr = new DataOutputStream(conn.getOutputStream());
        String data = "{"
                + "  \"query\":{"
                + "     \"query_string\":{"
                + "			\"fields\": [\n"
                + "				\"attachment.content\",\n"
                + "				\"attachment.title\",\n"
                + "				\"attachment.author\"\n"
                + "			],"
                + "        \"query\": \"*" + dato + "*\""
                + "     }"
                + "  } }";
        wr.writeBytes(data);
        wr.flush();
        wr.close();
    }

    private void setFileContent(HttpURLConnection conn, String file) throws IOException {
        DataOutputStream wr;
        wr = new DataOutputStream(conn.getOutputStream());
        String data = "{ \"data\": \"" + file + "\" }";
        wr.writeBytes(data);
        wr.flush();
        wr.close();
    }
}
