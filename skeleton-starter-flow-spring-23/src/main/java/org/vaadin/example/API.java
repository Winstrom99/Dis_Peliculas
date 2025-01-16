package org.vaadin.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private static final String urlPrefix = "http://127.0.0.1:8082/%s";
    //private static final String urlPrefix = "http://reverent_shirley:8097/%s";

    public String getAllTourism() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "peliculas");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println("Respuesta de la API: " + response.body());
        return response.body();
    }
}
