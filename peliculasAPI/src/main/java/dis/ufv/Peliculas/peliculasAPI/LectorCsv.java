package dis.ufv.Peliculas.peliculasAPI;
import java.io.*;
import java.util.ArrayList;


public class LectorCsv {

    public ArrayList<Pelicula> leerCsv() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String rutaArchivo = "/peliculas.csv"; // Asegúrate de tener este archivo en src/main/resources

        try (InputStream is = LectorCsv.class.getResourceAsStream(rutaArchivo);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linea;
            // Leer la cabecera y descartarla
            br.readLine();

            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                // Dividir la línea por comas
                String[] datos = linea.split(",");
                // Crear un objeto Pelicula con los datos
                Pelicula pelicula = new Pelicula(
                        Integer.parseInt(datos[0]),    // id
                        datos[1],                     // nombre
                        datos[2],                     // genero
                        Integer.parseInt(datos[3]),   // año
                        Double.parseDouble(datos[4])  // calificación
                );
                listaPeliculas.add(pelicula);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error en el formato numérico: " + e.getMessage());
        }

        return listaPeliculas;
    }

}
