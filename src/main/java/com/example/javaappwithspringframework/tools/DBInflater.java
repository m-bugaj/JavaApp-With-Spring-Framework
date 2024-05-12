package com.example.javaappwithspringframework.tools;

import com.example.javaappwithspringframework.model.Director;
import com.example.javaappwithspringframework.model.Movie;
import com.example.javaappwithspringframework.model.Actor;
import com.example.javaappwithspringframework.model.Award;
import com.example.javaappwithspringframework.repositories.ActorRepository;
import com.example.javaappwithspringframework.repositories.AwardRepository;
import com.example.javaappwithspringframework.repositories.DirectorRepository;
import com.example.javaappwithspringframework.repositories.MovieRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {
    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;
    private ActorRepository actorRepository;
    private AwardRepository awardRepository;

    public DBInflater(MovieRepository movieRepository, DirectorRepository directorRepository, ActorRepository actorRepository, AwardRepository awardRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.actorRepository = actorRepository;
        this.awardRepository = awardRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        // First set of data
        Director fincher = new Director("David", "Fincher", "28-08-1962");
        Actor pitt = new Actor("Brad", "Pitt", "18-12-1963");
        Award oscar = new Award("Oscar for Best Actor", "1999");
        Movie fightClub = new Movie("Fight Club", "drama", "1999", "english", "USA");

        directorRepository.save(fincher);
        awardRepository.save(oscar);

        fightClub.getDirectors().add(fincher);
        fightClub.getActors().add(pitt);
        pitt.getAwards().add(oscar);
        actorRepository.save(pitt);
        movieRepository.save(fightClub);


        Director nolan = new Director("Christopher", "Nolan", "30-07-1970");
        Actor dicaprio = new Actor("Leonardo", "DiCaprio", "11-11-1974");
        Award bafta = new Award("BAFTA for Best Film", "2010");
        Movie inception = new Movie("Inception", "Sci-fi", "2010", "English", "USA");

        directorRepository.save(nolan);
        actorRepository.save(dicaprio);
        awardRepository.save(bafta);

        inception.getDirectors().add(nolan);
        inception.getActors().add(dicaprio);
        inception.getAwards().add(bafta);
        movieRepository.save(inception);


        Director spielberg = new Director("Steven", "Spielberg", "18-12-1946");
        Actor goldblum = new Actor("Jeff", "Goldblum", "22-10-1952");
        Award goldenGlobe = new Award("Golden Globe for Best Director", "1993");
        Movie jurassicPark = new Movie("Jurassic Park", "Adventure", "1993", "English", "USA");

        actorRepository.save(goldblum);
        awardRepository.save(goldenGlobe);

        spielberg.getAwards().add(goldenGlobe);
        directorRepository.save(spielberg);
        jurassicPark.getDirectors().add(spielberg);
        jurassicPark.getActors().add(goldblum);
        movieRepository.save(jurassicPark);
    }


}
