package esgi.jobseeker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import esgi.jobseeker.model.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        if (!verifyResponse(response, 200)) {
            return false;
        }
        Type tokenType = new TypeToken<Map<String, String>>(){}.getType();
        String brutResponse = getContentFromResponse(response);
        Map<String, String> tokenMap = gson.fromJson(brutResponse, tokenType);
        this.token = tokenMap.get("token");
        return true;
    }

    public boolean saveAd(AdToSend ad) throws Exception {
        System.out.println("JSON Ad : " + ad.toString());
        HttpPost postRequest = new HttpPost(this.urlBase + "/ads/");
        postRequest.addHeader("Content-Type", "application/json");
        postRequest.addHeader("Authorization", this.token);
        System.out.println(postRequest.getURI().toString());
        postRequest.setEntity(createStringEntity(ad));
        HttpResponse response = client.execute(postRequest);
        boolean responseOk = verifyResponse(response, 201);
        EntityUtils.consume(response.getEntity());
        return responseOk;
    }

    public boolean closeAd(String id) throws Exception {
        System.out.println("Deleting Ad with id : " + id);
        HttpDelete deleteRequest = new HttpDelete(this.urlBase + "/ads/" + id);
        deleteRequest.addHeader("Authorization", this.token);
        System.out.println(deleteRequest.getURI().toString());
        HttpResponse response = client.execute(deleteRequest);
        EntityUtils.consume(response.getEntity());
        return verifyResponse(response, 200);
    }

    private boolean verifyResponse(HttpResponse response, int requiredCode) throws IOException {
        if (response.getStatusLine().getStatusCode() != requiredCode) {
            System.out.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            System.out.println("Failed : HTTP detail : " + getContentFromResponse(response));
            return false;
        }
        return true;
    }

    public List<Website> getAllWebsites() throws Exception {
        HttpResponse response = sendGetRequest("/websites/", false);
        String jsonResponse = getResponseContent(response);
        Type listType = new TypeToken<ArrayList<Website>>(){}.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public List<ContractType> getAllContractTypes() throws Exception {
        HttpResponse response = sendGetRequest("/contractTypes/", false);
        String jsonResponse = getResponseContent(response);
        Type listType = new TypeToken<ArrayList<ContractType>>(){}.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public List<Ad> getAllAds() throws Exception {
        HttpResponse response = sendGetRequest("/ads/getAds", true);
        String jsonResponse = getResponseContent(response);
        System.out.println("JSON ADS : " + jsonResponse);
        Type listType = new TypeToken<ArrayList<Ad>>(){}.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public List<QuantityPerDate> getAdFlowByTag(String tag) throws Exception {
        String request = "/stats/getAdFlow/" + tag;
        HttpResponse response = sendGetRequest(request, true);
        String jsonResponse = getResponseContent(response);
        Type listType = new TypeToken<List<QuantityPerDate>>(){}.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public List<QuantityPerDate> getAppFlowByTag(String tag) throws Exception {
        String request = "/stats/getAppFlow/" + tag;
        HttpResponse response = sendGetRequest(request, true);
        String jsonResponse = getResponseContent(response);
        Type listType = new TypeToken<List<QuantityPerDate>>(){}.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public List<QuantityPerState> getAppGlobalStateByTag(String tag) throws Exception {
        String request = "/stats/getAppGlobalState/" + tag;
        HttpResponse response = sendGetRequest(request, true);
        String jsonResponse = getResponseContent(response);
        Type listType = new TypeToken<List<QuantityPerState>>(){}.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    private HttpResponse sendGetRequest(String path, boolean withToken) throws Exception {
        HttpGet getRequest = new HttpGet(this.urlBase + path);
        getRequest.addHeader("Authorization", this.token);
        System.out.println(getRequest.getURI().toString());
        return client.execute(getRequest);
    }

    private String getResponseContent(HttpResponse response) throws IOException {
        BasicResponseHandler brh = new BasicResponseHandler();
        return brh.handleResponse(response);
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
