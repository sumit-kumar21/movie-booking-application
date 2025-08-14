package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.MovieDTO;
import com.example.MovieBookingApplication.Entity.Movie;
import com.example.MovieBookingApplication.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());

        return  movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(String genre){
        Optional<List<Movie>> listOfMovieBox = movieRepository.findByGenre(genre);

        if(listOfMovieBox.isPresent()){
            return listOfMovieBox.get();
        }
        else throw  new RuntimeException("No movies found for genre "+genre);
    }

    public List<Movie> getMoviesByLanguage(String language){
        Optional<List<Movie>> listOfMovieBox = movieRepository.findByLanguage(language);

        if(listOfMovieBox.isPresent()){
            return listOfMovieBox.get();
        }
        else throw  new RuntimeException("No movies found of language "+language);
    }

    public Movie getMovieByTitle(String title){
        Optional<Movie> movieBox = movieRepository.findByName(title);

        if(movieBox.isPresent()){
            return movieBox.get();
        }
        else throw new RuntimeException("No movie found for the title "+ title);
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No movie found for the id "+ id));

        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

}
