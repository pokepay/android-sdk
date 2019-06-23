package jp.pokepay.pokepaylib;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

    private static void addHeaders(HttpURLConnection con, final Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                con.setRequestProperty(key, value);
            }
        }
    }

    private static Map<String, Object> removeNullEntries(Map<String, Object> map) {
        if (map == null) return null;
        final Map<String, Object> ret = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            final String key = entry.getKey();
            final Object value = entry.getValue();
            if (key != null && value != null) {
                ret.put(key, value);
            }
        }
        return ret;
    }

    public static <T> T send(final Class<T> cls, final String url, final Method meth) {
        return send(cls, url, meth, null, null);
    }

    public static <T> T send(final Class<T> cls, final String url, final Method meth, final Map<String, Object> parameters) {
        return send(cls, url, meth, parameters, null);
    }

    public static <T> T send(final Class<T> cls, final String url, final Method meth, final Map<String, Object> parametersRaw, final Map<String, String> headers) {
        HttpURLConnection con = null;
        StringBuilder result = new StringBuilder();
        Map<String, Object> parameters = removeNullEntries(parametersRaw);
        try {
            switch (meth) {
            case GET: {
                String queryString = "";
                if (parameters != null) {
                    String [][] queryParameters = new String[parameters.size()][2];
                    int counter = 0;
                    for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                        queryParameters[counter][0] = entry.getKey();
                        queryParameters[counter][1] = String.valueOf(entry.getValue());
                    }
                    queryString = QueryString.build(queryParameters);
                }
                URL u = new URL(url + queryString);
                con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod(meth.toString());
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("accept", "*/*");
                addHeaders(con, headers);
                break;
            }
            case POST: {
                URL u = new URL(url);
                con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod(meth.toString());
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("accept", "*/*");
                addHeaders(con, headers);
                if(parameters != null) {
                    final String body = JsonConverter.toString(parameters);
                    con.setDoOutput(true);
                    con.setRequestProperty("Accept-Language", "jp");
                    con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                    con.setRequestProperty("Content-Length", String.valueOf(body.length()));
                    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                    out.write(body);
                    out.flush();
                }
                break;
            }
            case DELETE: {
                URL u = new URL(url);
                con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod(meth.toString());
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("accept", "*/*");
                addHeaders(con, headers);
                break;
            }
            case PATCH: {
                URL u = new URL(url);
                con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod(meth.toString());
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("accept", "*/*");
                addHeaders(con, headers);
                if(parameters != null) {
                    final String body = JsonConverter.toString(parameters);
                    con.setDoOutput(true);
                    con.setRequestProperty("Accept-Language", "jp");
                    con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                    con.setRequestProperty("Content-Length", String.valueOf(body.length()));
                    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                    out.write(body);
                    out.flush();
                }
                break;
            }
            case PUT: {
                URL u = new URL(url);
                con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod(meth.toString());
                con.setRequestProperty("accept", "application/json");
                con.setRequestProperty("accept", "*/*");
                addHeaders(con, headers);
                if(parameters != null) {
                    final String body = JsonConverter.toString(parameters);
                    con.setDoOutput(true);
                    con.setRequestProperty("Accept-Language", "jp");
                    con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                    con.setRequestProperty("Content-Length", String.valueOf(body.length()));
                    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                    out.write(body);
                    out.flush();
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
