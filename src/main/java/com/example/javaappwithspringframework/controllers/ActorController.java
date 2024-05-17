package com.example.javaappwithspringframework.controllers;

import com.example.javaappwithspringframework.commands.ActorCommand;
import com.example.javaappwithspringframework.model.Actor;
import com.example.javaappwithspringframework.converters.ActorCommandToActorConverter;
import com.example.javaappwithspringframework.converters.ActorToActorCommandConverter;
import com.example.javaappwithspringframework.repositories.ActorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ActorController {
    private ActorRepository actorRepository;
    private ActorCommandToActorConverter actorCommandToActor;
    private ActorToActorCommandConverter actorToActorCommand;

    public ActorController(ActorRepository actorRepository, ActorCommandToActorConverter actorCommandToActor, ActorToActorCommandConverter actorToActorCommand) { this.actorRepository = actorRepository;
        this.actorCommandToActor = actorCommandToActor;
        this.actorToActorCommand = actorToActorCommand;
    }

    @RequestMapping(value = {"/actors", "actor/list"})
    public String getActors(Model model) {
        model.addAttribute("actors", actorRepository.findAll());
        return "actor/list";
    }

    @RequestMapping("/actor/{id}/show")
    public String getActorDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("actor", actorRepository.findById(id).orElse(null));
        return "actor/show";
    }

    @GetMapping("/actor/new")
    public String newActor(Model model){
        model.addAttribute("actor", new ActorCommand());
        return "actor/addedit";
    }

    @GetMapping("/actor/{id}/edit")
    public String editActor(@PathVariable Long id, Model model) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            ActorCommand actorCommand = actorToActorCommand.convert(actor);
            model.addAttribute("actor", actorCommand);
            return "actor/addedit";
        } else {
            System.out.println("Actor not found!");
            return "redirect:/actors";
        }
    }

    @GetMapping("/actor/{id}/delete")
    public String deleteActor(@PathVariable Long id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);

        if (actorOptional.isPresent()) {
            Actor actorToRemove = actorOptional.get();
            // Usuń powiązania z filmami
            actorToRemove.getMovies().forEach(movie -> movie.getActors().remove(actorToRemove));
            actorToRemove.getMovies().clear();
            actorRepository.save(actorToRemove);  // Update movies before deleting actor
            actorRepository.delete(actorToRemove);
            return "redirect:/actors";
        } else {
            return "redirect:/actors";
        }
    }

    @PostMapping("/actor/")
    public String saveOrUpdate(@ModelAttribute ActorCommand command) {
        Optional<Actor> actorById = actorRepository.findById(command.getId());
        Optional<Actor> actorByName = actorRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (actorById.isPresent()) {
            Actor editedActor = actorById.get();
            editedActor.setFirstName(command.getFirstName());
            editedActor.setLastName(command.getLastName());
            editedActor.setDateOfBirth(command.getDateOfBirth());
            actorRepository.save(editedActor);
            return "redirect:/actor/" + editedActor.getId() + "/show";
        } else if (actorByName.isEmpty()) {
            Actor newActor = actorCommandToActor.convert(command);
            Actor savedActor = actorRepository.save(newActor);
            return "redirect:/actor/" + savedActor.getId() + "/show";
        } else {
            System.out.println("Sorry, there's such actor in db");
            return "redirect:/actor/" + actorByName.get().getId() + "/show";
        }
    }


}
