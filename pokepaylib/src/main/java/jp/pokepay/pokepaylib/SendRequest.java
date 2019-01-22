package jp.pokepay.pokepaylib;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendRequest {
    private String urlString;

    public SendRequest(String url){
        this.urlString = url;
    }

    public Object proc(Object response, String crud){
        return proc(response, crud, null);
    }
    public Object proc(Object response, String crud, String json){
        return proc(response, crud, json, null, null);
    }
    public Object proc(Object response, String crud, String json, String headerS, String headerS1){
        System.out.println(urlString + ", " + crud + ", " + json + ": " + headerS + ", " + headerS1);
        HttpURLConnection con = null;
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod(crud);
            if(crud.equals("GET")){
                con.setRequestProperty("accept", "application/json");
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
                if (json != null){
                    con.setDoOutput(true);
                    con.setRequestProperty("Accept-Language", "jp");
                    con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                    con.setRequestProperty("accept", "application/json");
                    con.setRequestProperty("Content-Length", String.valueOf(json.length()));
                    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                    out.write(json);
                    out.flush();
                }
            }
            else if(crud.equals("POST")) {
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
                if(json != null) {
                    con.setDoOutput(true);
                    con.setRequestProperty("Accept-Language", "jp");
                    con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                    con.setRequestProperty("accept", "application/json");
                    con.setRequestProperty("Content-Length", String.valueOf(json.length()));
                    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                    out.write(json);
                    out.flush();
                }
            }
            else if(crud.equals("DELETE")){
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("accept", "*/*");
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
            }
            else if(crud.equals("PATCH")){
                con.setDoOutput(true);
                con.setRequestProperty("Accept-Language", "jp");
                con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("Content-Length", String.valueOf(json.length()));
                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                out.write(json);
                out.flush();
            }
            else if(crud.equals("PUT")){
                con.setRequestProperty("accept", "application/json");
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
            }
            con.connect();

            // HTTPレスポンスコード
            final int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                final InputStream in = con.getInputStream();
                String encoding = con.getContentEncoding();
                if(null == encoding){
                    encoding = "UTF-8";
                }
                final InputStreamReader inReader = new InputStreamReader(in, encoding);
                final BufferedReader bufReader = new BufferedReader(inReader);
                String line = null;
                while((line = bufReader.readLine()) != null) {
                    result.append(line);
                }
                bufReader.close();
                inReader.close();
                in.close();
            }else{
                 System.out.println(status);
                 if(response.getClass().toString().equals(String.class.toString())){
                     return String.valueOf(status);
                 }
                return null;
            }

        }catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        System.out.println(result.toString());

        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(result.toString(), response.getClass());
        } catch (IOException e) {
            e.printStackTrace();
            if(response.getClass().equals(String.class)){
                return result.toString();
            }
            return null;
        }
        return response;
    }
}
