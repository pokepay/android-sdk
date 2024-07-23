package jp.pokepay.pokepaylib;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.PartnerAPI.ExternalServiceRequestError;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.VeritransError;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.VeritransRequestError;
import jp.pokepay.pokepaylib.Responses.BankError;
import jp.pokepay.pokepaylib.Responses.NoContent;
import jp.pokepay.pokepaylib.Responses.OAuthError;

public class Request {

    /**
     * @deprecated use {@link #getFormatter()} instead.
     */
    @Deprecated
    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");

    public static final SimpleDateFormat getFormatter() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter;
    }

    private static void addHeaders(HttpURLConnection con, final Map<String, String> headers) {
        if (headers == null) return;
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

    private static String getResponseBody(final InputStream in, final String encoding) throws IOException {
        StringBuilder responseBody = new StringBuilder();
        final InputStreamReader inReader = new InputStreamReader(in, encoding);
        final BufferedReader bufReader = new BufferedReader(inReader);
        String line = null;
        while ((line = bufReader.readLine()) != null) {
            responseBody.append(line);
        }
        bufReader.close();
        inReader.close();
        return responseBody.toString();
    }

    public static <R> R send(final Class<R> cls, final Class errCls, final String url, final Method meth) throws ProcessingError, BankRequestError, OAuthRequestError, ExternalServiceRequestError {
        return send(cls, errCls, url, meth, null, null);
    }

    public static <R> R send(final Class<R> cls, final Class errCls, final String url, final Method meth, final Map<String, Object> parameters) throws ProcessingError, BankRequestError, OAuthRequestError, ExternalServiceRequestError {
        return send(cls, errCls, url, meth, parameters, null);
    }

    public static <R> R send(final Class<R> cls, final Class errCls, final String url, final Method meth, final Map<String, Object> parametersRaw, final Map<String, String> headers) throws ProcessingError, BankRequestError, OAuthRequestError, ExternalServiceRequestError {
        final int CONNECTION_TIMEOUT = 30 * 1000;
        final int READ_TIMEOUT = 30 * 1000;
        HttpURLConnection con = null;
        Map<String, Object> parameters = removeNullEntries(parametersRaw);
        try {
            switch (meth) {
                case GET: {
                    String queryString = "";
                    if (parameters != null) {
                        String[][] queryParameters = new String[parameters.size()][2];
                        int counter = 0;
                        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                            queryParameters[counter][0] = entry.getKey();
                            queryParameters[counter][1] = String.valueOf(entry.getValue());
                            counter++;
                        }
                        queryString = QueryString.build(queryParameters);
                    }
                    URL u = new URL(url + queryString);
                    con = (HttpURLConnection) u.openConnection();
                    con.setConnectTimeout(CONNECTION_TIMEOUT);
                    con.setReadTimeout(READ_TIMEOUT);
                    con.setRequestMethod(meth.toString());
                    con.setRequestProperty("accept", "application/json");
                    con.setRequestProperty("accept", "*/*");
                    addHeaders(con, headers);
                    break;
                }
                case POST:
                case DELETE:
                case PATCH:
                case PUT: {
                    URL u = new URL(url);
                    con = (HttpURLConnection) u.openConnection();
                    con.setConnectTimeout(CONNECTION_TIMEOUT);
                    con.setReadTimeout(READ_TIMEOUT);
                    con.setRequestMethod(meth.toString());
                    con.setRequestProperty("accept", "application/json");
                    con.setRequestProperty("accept", "*/*");
                    addHeaders(con, headers);
                    if (parameters != null) {
                        final byte[] body = JsonConverter.toString(parameters).getBytes(StandardCharsets.UTF_8);
                        con.setDoOutput(true);
                        con.setRequestProperty("Accept-Language", "jp");
                        con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
                        con.setRequestProperty("Content-Length", String.valueOf(body.length));
                        OutputStream out = con.getOutputStream();
                        out.write(body);
                        out.flush();
                        out.close();
                    }
                    break;
                }
            }
            con.connect();
            String encoding = con.getContentEncoding();
            if (null == encoding) {
                encoding = "UTF-8";
            }
            final int status = con.getResponseCode();
            final ObjectMapper mapper = JsonConverter.createObjectMapper();
            if (HttpURLConnection.HTTP_OK <= status && status < HttpURLConnection.HTTP_MULT_CHOICE) {
                final String responseBody = getResponseBody(con.getInputStream(), encoding);
                if (cls == String.class) {
                    return (R) responseBody;
                }
                if (cls == NoContent.class && responseBody.isEmpty()) {
                    return (R) (new NoContent());
                }
                return mapper.readValue(responseBody, cls);
            } else {
                final String responseBody = getResponseBody(con.getErrorStream(), encoding);
                if (errCls == BankRequestError.class) {
                    final BankError error = mapper.readValue(responseBody, BankError.class);
                    throw new BankRequestError(status, error);
                } else if (errCls == OAuthRequestError.class) {
                    final OAuthError error = mapper.readValue(responseBody, OAuthError.class);
                    throw new OAuthRequestError(status, error);
                } else if (errCls == VeritransRequestError.class) {
                    final VeritransError error = mapper.readValue(responseBody, VeritransError.class);
                    throw new VeritransRequestError(error);
                } else {
                    throw new ProcessingError("Invalid Error type specified");
                }
            }
        } catch (BankRequestError | OAuthRequestError | ExternalServiceRequestError e) {
            throw e;
        } catch (Exception e) {
            final StringWriter st = new StringWriter();
            e.printStackTrace(new PrintWriter(st));
            throw new ProcessingError(st.toString());
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    public enum Method {
        GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), PATCH("PATCH");

        private final String name;

        Method(String s) {
            name = s;
        }

        public String toString() {
            return name;
        }
    }
}
