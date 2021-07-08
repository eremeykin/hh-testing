package pete.eremeykin.testing.apiclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiClient {

    private static final String PROTOCOL = "https";
    private static final String HOST = "api.hh.ru";
    private static final String PATH = "vacancies";
    private static final String PARAM_TEXT = "text";
    private static final URIBuilder uriBuilder = new URIBuilder();
    private static final String URL = PROTOCOL + "://" + HOST + "/" + PATH + "?" + PARAM_TEXT + "=";

    static {
        uriBuilder.setScheme(PROTOCOL);
        uriBuilder.setHost(HOST);
        uriBuilder.setPath(PATH);
    }

    public static Response get(String text) throws IOException, URISyntaxException {
        uriBuilder.setParameter(PARAM_TEXT, text);
        URI uri = uriBuilder.build();
        System.out.println(uri.toString());
        HttpGet request = new HttpGet(uri);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        return new Response(response);
    }

    public static Response getRaw(String name) throws IOException {
        HttpGet request = new HttpGet(URL + name);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        return new Response(response);
    }
}
