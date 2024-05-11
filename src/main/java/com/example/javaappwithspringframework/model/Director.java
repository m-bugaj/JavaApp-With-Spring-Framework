package com.example.javaappwithspringframework.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "director_awards",
            joinColumns = @JoinColumn(name = "director_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id")
    )
    private Set<Award> awards = new HashSet<>();


    public Director(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Director() {
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", movies=" + movies +
                '}';
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public void setId(Long id) {this.id = id;}

    public Long getId() {return id;}
}
