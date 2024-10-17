package org.example;

import org.example.dao.PeliculaDAO;
import org.example.models.Pelicula;
import org.example.view.JdbcUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(JdbcUtils.getConnection());
        var dao = new PeliculaDAO(JdbcUtils.getConnection());

        // Añadir una nueva película
        var peliculaNueva = new Pelicula();
        peliculaNueva.setTitulo("Seven");
        peliculaNueva.setGenero("thriller");
        peliculaNueva.setAño(1995);
        System.out.println(peliculaNueva);
        dao.save(peliculaNueva);
        System.out.println(peliculaNueva);

        // Contar el número de películas


        // Filtrar películas por género
        var peliculaByGenero = peliculaDAO.findByGenre("thriller");
        System.out.println(peliculaByGenero);

    }
}
