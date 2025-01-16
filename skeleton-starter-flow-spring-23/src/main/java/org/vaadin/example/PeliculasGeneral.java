package org.vaadin.example;


import org.vaadin.example.PeliculaService;
import org.vaadin.example.Pelicula;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import java.awt.*;

public class PeliculasGeneral extends VerticalLayout {

    private final Grid<Pelicula> grid = new Grid<>(Pelicula.class);


    private final RestTemplate restTemplate;
    private final PeliculaService peliculaService;

    public PeliculasGeneral(@Autowired PeliculaService service) {

        this.peliculaService = service;
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter(new ObjectMapper()));

        setSizeFull();
        configureGrid();
        // configureDatePickers();

        updateGrid(); // Carga inicial de todos los registros
    }

    private void configureGrid() {
        // Configurar las columnas del Grid manualmente
        grid.addColumn(item -> item.getId()).setHeader("id");
        grid.addColumn(item -> item.getNombre()).setHeader("nombre");
        grid.addColumn(item -> item.getGenero()).setHeader("genero");
        grid.addColumn(item -> item.getAño()).setHeader("año");
        grid.addColumn(item -> item.getCalificacion()).setHeader("calificacion");

        // Ajustar ancho automático de las columnas
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }


//    private void configureDatePickers() {
//        startDatePicker.addValueChangeListener(event -> filterGridByDate());
//        endDatePicker.addValueChangeListener(event -> filterGridByDate());
//    }

    private void updateGrid() {
        try {
            // Obtén los datos desde el servicio
            List<Pelicula> data = peliculaService.leeJSON();

            // Asigna los datos al Grid
            grid.setItems(data);

        } catch (Exception e) {
            // Muestra una notificación si ocurre un error
            Notification.show("Error al cargar los datos: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }



}



//    private void filterGridByDate() {
//        String startDate = startDatePicker.getValue() != null ? startDatePicker.getValue().toString() : "";
//        String endDate = endDatePicker.getValue() != null ? endDatePicker.getValue().toString() : "";
//
//        List<Turismo> filteredData = service.;
//        grid.setItems(filteredData);
//
//        if (filteredData.isEmpty()) {
//            Notification.show("No hay datos para el rango de fechas seleccionado.", 3000, Notification.Position.MIDDLE);
//        }
//    }