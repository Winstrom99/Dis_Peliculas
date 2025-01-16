package org.vaadin.example;


import com.vaadin.flow.component.tabs.TabSheet;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route("")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    public MainView(@Autowired PeliculaService service) {
        TabSheet hojas_tabuladas = new TabSheet();

        // Pasar el servicio a DatosGenerales y DatosAgrupados
        PeliculasGeneral datosGenerales = new PeliculasGeneral(service);
        datosGenerales.setWidth("1000px");
        hojas_tabuladas.add("Datos generales", datosGenerales);

        add(hojas_tabuladas);
    }

}