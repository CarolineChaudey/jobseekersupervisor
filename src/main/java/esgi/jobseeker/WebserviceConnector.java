package esgi.jobseeker;

import com.google.gson.Gson;
import esgi.jobseeker.model.Login;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

/**
 * Created by caroline on 18/06/17.
 */
public class WebserviceConnector {

    private static WebserviceConnector ourInstance = new WebserviceConnector();

    private String urlBase;
    private String token;
    private Gson gson = new Gson();
    private DefaultHttpClient client = new DefaultHttpClient();
    private final String HOST = "localhost";
    private final String PORT = "3000";

    public static WebserviceConnector getInstance() {
        return ourInstance;
    }

    private WebserviceConnector() {
        this.urlBase = "http://" + HOST + ":" + PORT;
    }

    public boolean getConnectionToken(String login, String pswd) throws Exception {

        HttpPost postRequest = new HttpPost(this.urlBase + "/users/supervisor-auth");
        postRequest.addHeader("Content-Type", "application/json");
        System.out.println(postRequest.getURI().toString());
        Login credentials = new Login(login, pswd);
        postRequest.setEntity(createStringEntity(credentials));
        HttpResponse response = client.execute(postRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            System.out.println("Failed : HTTP detail : " + getContentFromResponse(response));
            return false;
        }
        this.token = getContentFromResponse(response);
        return true;
    }

    private StringEntity createStringEntity(Object o) throws UnsupportedEncodingException {
        String param = gson.toJson(o);
        return new StringEntity(param);
    }

    private String getContentFromResponse(HttpResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null) {
            result.append(output);
        }
        return result.toString();
    }
}
