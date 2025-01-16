package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService implements Serializable {

    public ArrayList<Pelicula> leeJSON() throws URISyntaxException, IOException,
            InterruptedException {
        API api = new API();
        String resultsAPI = api.getAllTourism();
        Gson gson = new Gson();
        ArrayList<Pelicula> lista = gson.fromJson(resultsAPI, new TypeToken<ArrayList<Pelicula>>() {}.getType());
        return lista;
    }
}