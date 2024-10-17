package org.example.dao;

import org.example.models.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO implements DAO<Pelicula> {
    public static final String INSERT_INTO_PELICULA = "insert into pelicula (titulo, genero, año) values (?, ?, ?)";
    public static final String SELECT_FROM_PELICULA_WHERE_GENERO = "select * from pelicula where genero = ?";
    private static Connection connection = null;

    public PeliculaDAO(Connection c) {
        connection = c;
    }

    @Override
    public List<Pelicula> findAll() {
        return null;
    }

    @Override
    public Pelicula findById(Integer id) {
        return null;
    }

    @Override
    public void save(Pelicula pelicula) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT_INTO_PELICULA, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getGenero());
            ps.setInt(3, pelicula.getAño());

            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                pelicula.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Pelicula pelicula) {

    }

    @Override
    public void delete(Pelicula pelicula) {

    }

    @Override
    public Pelicula count(Integer id) {
        Pelicula pelicula = null;

        try(PreparedStatement ps = connection.prepareStatement("")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setId(rs.getInt("id"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setAño(rs.getInt("año"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pelicula;
    }

    @Override
    public Pelicula findByGenre(String genero) {
        Pelicula pelicula = null;
        try(PreparedStatement ps = connection.prepareStatement(SELECT_FROM_PELICULA_WHERE_GENERO)) {
            ps.setString(1, genero);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setId(rs.getInt("id"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setAño(rs.getInt("año"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pelicula;

        //var resultado = new ArrayList<Pelicula>();

        //try {
        //var st = connection.createStatement();
        //ResultSet rs = st.executeQuery(SELECT_FROM_PELICULA_WHERE_GENERO);
        //while (rs.next()) {
        //Pelicula pelicula = new Pelicula();
        //pelicula.setId(rs.getInt("id"));
        //pelicula.setTitulo(rs.getString("titulo"));
        //pelicula.setGenero(rs.getString("genero"));
        //pelicula.setAño(rs.getInt("año"));
        //resultado.add(pelicula);
        //}
        //} catch (SQLException e) {
        //throw new RuntimeException(e);
        //}
        //return resultado;
    }
}
