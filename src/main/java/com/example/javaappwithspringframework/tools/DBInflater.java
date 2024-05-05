package com.example.javaappwithspringframework.tools;

import com.example.javaappwithspringframework.model.Director;
import com.example.javaappwithspringframework.model.Movie;
import com.example.javaappwithspringframework.repositories.DirectorRepository;
import com.example.javaappwithspringframework.repositories.MovieRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {
    public DBInflater(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Director fincher = new Director("David", "Fincher", "28-08-1962");
        Movie fightClub = new Movie("Fight Club", "drama", "1999", "english", "USA");
        fincher.getMovies().add(fightClub);
        fightClub.getDirectors().add(fincher);
        directorRepository.save(fincher);
        movieRepository.save(fightClub);


        Director bareja = new Director("Stanisław", "Bareja", "05-12-1929");
        Movie zmiennicy = new Movie("Zmiennicy", "Komedia", "1986", "polish", "Poland");
        bareja.getMovies().add(zmiennicy);
        zmiennicy.getDirectors().add(bareja);
        directorRepository.save(bareja);
        movieRepository.save(zmiennicy);



        Director kurosawa = new Director("Akira", "Kurosawa", "23-03-1910");
        Movie sevenSamurai = new Movie("Seven Samurai", "action", "1954", "japanese", "Japan");
        kurosawa.getMovies().add(sevenSamurai);
        fightClub.getDirectors().add(kurosawa);
        directorRepository.save(kurosawa);
        movieRepository.save(sevenSamurai);

    }

}
