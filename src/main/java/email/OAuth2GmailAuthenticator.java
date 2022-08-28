package email;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class OAuth2GmailAuthenticator {
    private final static String GOOGLE_API_OAUTH2_URL = "https://oauth2.googleapis.com/token";
    private final static String GOOGLE_API_OAUTH2_CLIENT_ID = "1052945576635-vv55b7jprva8ss2vs02lgdj0d4dh70u6.apps.googleusercontent.com";
    private final static String GOOGLE_API_OAUTH2_SECRET = "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";
    private final static String GOOGLE_API_OAUTH2_REFRESH_TOKEN = "1//04NxJP8rg3KBhCgYIARAAGAQSNwF-L9Ir9kEcP7MqFvS1cnjCPh8ujQmm4jfXQLRvl2QIErRcgvEI6jnwKRQL50azUGfgh3FdLAk";

    private volatile String accessToken = "ya29.A0AVA9y1v_iq5dcQ8t7WYdnrkhh_7JVW43POLl2sD7S9BG-BTAz8hfHTQvHoblR85URnTSC382O_gZOGhS9PCOn96knJe8GlAmgG3uvil_q8YwnjG-t1NBtWujlGOb7SL8tK0ivZx-WnsuUxXcRwmmNrIS298XaCgYKATASAQASFQE65dr8SYkUkvD7BxPRBzjwEBUMlA0163";
    private volatile long tokenExpires = 1458168133864L;


    public String getAccessToken() {

        if (System.currentTimeMillis() > tokenExpires) {
            synchronized (this) {
                // double check!
                if (System.currentTimeMillis() > tokenExpires) {
                    refreshToken();
                }
            }
        }

        return accessToken;
    }

    private void refreshToken() {
        try {

            String request = new StringBuilder("client_id=").append(URLEncoder.encode(GOOGLE_API_OAUTH2_CLIENT_ID, "UTF-8"))
                    .append("&client_secret=").append(URLEncoder.encode(GOOGLE_API_OAUTH2_SECRET, "UTF-8"))
                    .append("&refresh_token=").append(URLEncoder.encode(GOOGLE_API_OAUTH2_REFRESH_TOKEN, "UTF-8"))
                    .append("&grant_type=refresh_token")
                    .toString();

            HttpURLConnection connection = (HttpURLConnection) new URL(GOOGLE_API_OAUTH2_URL).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(request); // note: println causes error
            out.flush();
            connection.connect();


            try {
                HashMap<String, Object> result;
                InputStream inputStream = connection.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                // simply convert json to hashtable
                result = objectMapper.readValue(inputStream, new TypeReference<HashMap<String, Object>>() {
                });

                accessToken = (String) result.get("access_token");

                tokenExpires = System.currentTimeMillis() + (((Number) result.get("expires_in")).intValue() * 1000);

            } catch (IOException e) {
                String errorMsg = convertStreamToString(connection.getErrorStream());
                // log error message

                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();

//            throw e;   // populate exception
        }
    }

    private String convertStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }


        return result.toString("UTF-8");
    }

}
