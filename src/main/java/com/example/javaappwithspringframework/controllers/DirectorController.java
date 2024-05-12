package com.example.javaappwithspringframework.controllers;

import com.example.javaappwithspringframework.commands.DirectorCommand;
import com.example.javaappwithspringframework.model.Director;
import com.example.javaappwithspringframework.repositories.DirectorRepository;
import com.example.javaappwithspringframework.converters.DirectorCommandToDirectorConverter;
import com.example.javaappwithspringframework.converters.DirectorToDirectorCommandConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class DirectorController {
    private DirectorRepository directorRepository;
    private DirectorCommandToDirectorConverter directorCommandToDirector;
    private DirectorToDirectorCommandConverter directorToDirectorCommand;

    public DirectorController(DirectorRepository directorRepository, DirectorCommandToDirectorConverter directorCommandToDirector, DirectorToDirectorCommandConverter directorToDirectorCommand) {

        this.directorRepository = directorRepository;
        this.directorCommandToDirector = directorCommandToDirector;
        this.directorToDirectorCommand = directorToDirectorCommand;
    }

    @RequestMapping(value = {"/directors", "director/list"})
    public String getDirectors(Model model) {
        model.addAttribute("directors", directorRepository.findAll());
        return "director/list";
    }

    @RequestMapping("/director/{id}/show")
    public String getDirectorDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("director", directorRepository.findById(id).get());
        return "director/show";
    }

    @GetMapping("/director/new")
    public String newDirector(Model model){
        model.addAttribute("director", new DirectorCommand());
        return "director/addedit";
    }

    @GetMapping("/director/{id}/edit")
    public String editDirector(@PathVariable Long id, Model model) {

        Optional<Director> directorOptional = directorRepository.getFirstById(id);

        if (directorOptional.isPresent()) {
            Director director = directorOptional.get();
            DirectorCommand directorCommand = directorToDirectorCommand.convert(director);
            model.addAttribute("director", directorCommand);
            return "director/addedit";
        } else {
            System.out.println("Director not found!");
            return "directors";
        }
    }

    @GetMapping("/director/{id}/delete")
    public String deleteDirector(@PathVariable Long id) {
        Optional<Director> directorOptional = directorRepository.findById(id);

        if (directorOptional.isPresent()) {
            Director directorToRemove = directorOptional.get();
            // Usuń powiązania z filmami
            directorToRemove.getMovies().forEach(movie -> movie.getDirectors().remove(directorToRemove));
            directorToRemove.getMovies().clear();  // Clear the list of movies
            directorRepository.save(directorToRemove);  // Update the movies before deleting the director
            directorRepository.delete(directorToRemove);
            return "redirect:/directors";
        } else {
            return "redirect:/directors";
        }
    }

    @PostMapping("/director/")
    public String saveOrUpdate(@ModelAttribute DirectorCommand command){

        Optional<Director> directorOptional = directorRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());
        Optional<Director> directorById = directorRepository.getFirstById(command.getId());

        if (directorById.isPresent()){
            Director editedDirector = directorById.get();
            editedDirector.setFirstName(command.getFirstName());
            editedDirector.setLastName(command.getLastName());
            editedDirector.setDateOfBirth(command.getDateOfBirth());
            directorRepository.save(editedDirector);
            return "redirect:/director/" + editedDirector.getId() + "/show";
        } else if (directorOptional.isEmpty()) {
            Director detachedDirector = directorCommandToDirector.convert(command);
            Director savedDirector = directorRepository.save(detachedDirector);
            return "redirect:/director/" + savedDirector.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such artist in db");
            return "redirect:/director/" + directorOptional.get().getId() + "/show";
        }
    }

}
