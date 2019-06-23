package jp.pokepay.pokepaylib;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    public static enum Method {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE"),
        PATCH("PATCH");

        private final String name;

        private Method(String s) {
            name = s;
        }

        public String toString() {
            return name;
        }
    };

    public static <T> T send(final Class<T> cls, final String url, final Method meth) {
        return send(cls, url, meth, null);
    }

    public static <T> T send(final Class<T> cls, final String url, final Method meth, final String body) {
        return send(cls, url, meth, body, null, null);
    }

    public static <T> T send(final Class<T> cls, final String url, final Method meth, final String body, final String headerS, final String headerS1) {
        // System.out.println(url + ", " + meth + ", " + body + ": " + headerS + ", " + headerS1);
        HttpURLConnection con = null;
        StringBuffer result = new StringBuffer();
        try {
            URL u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod(meth.toString());
            switch (meth) {
            case GET: {
                con.setRequestProperty("accept", "application/json");
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
                if (body != null){
                    con.setDoOutput(true);
                    con.setRequestProperty("Accept-Language", "jp");
                    con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                    con.setRequestProperty("accept", "application/json");
                    con.setRequestProperty("Content-Length", String.valueOf(body.length()));
                    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                    out.write(body);
                    out.flush();
                }
                break;
            }
            case POST: {
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
                if(body != null) {
                    con.setDoOutput(true);
                    con.setRequestProperty("Accept-Language", "jp");
                    con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                    con.setRequestProperty("accept", "application/json");
                    con.setRequestProperty("Content-Length", String.valueOf(body.length()));
                    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                    out.write(body);
                    out.flush();
                }
                break;
            }
            case DELETE: {
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("accept", "*/*");
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
                break;
            }
            case PATCH: {
                con.setDoOutput(true);
                con.setRequestProperty("Accept-Language", "jp");
                con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("Content-Length", String.valueOf(body.length()));
                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                out.write(body);
                out.flush();
                break;
            }
            case PUT: {
                con.setRequestProperty("accept", "application/json");
                if(headerS != null && headerS1 != null){
                    con.setRequestProperty(headerS, headerS1);
                }
                break;
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
            } else {
                 // FIXME:!!!
                 // System.out.println(status);
                 // if (response.getClass().toString().equals(String.class.toString())) {
                 //     return String.valueOf(status);
                 // }
                 return null;
            }

        } catch (Exception e1) {
            // FIXME:!!!!
            e1.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        // System.out.println(result.toString());

        try {
            ObjectMapper mapper = new ObjectMapper();
            T response = mapper.readValue(result.toString(), cls);
            return response;
        } catch (Exception e) {
            // FIXME: !!!
            return null;
        }
    }
}
