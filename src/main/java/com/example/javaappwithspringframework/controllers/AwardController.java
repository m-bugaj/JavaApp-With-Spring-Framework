package com.example.javaappwithspringframework.controllers;

import com.example.javaappwithspringframework.commands.AwardCommand;
import com.example.javaappwithspringframework.converters.AwardCommandToAwardConverter;
import com.example.javaappwithspringframework.converters.AwardToAwardCommandConverter;
import com.example.javaappwithspringframework.model.Award;
import com.example.javaappwithspringframework.repositories.AwardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AwardController {
    private AwardRepository awardRepository;
    private AwardCommandToAwardConverter awardCommandToAward;
    private AwardToAwardCommandConverter awardToAwardCommand;

    public AwardController(AwardRepository awardRepository, AwardCommandToAwardConverter awardCommandToAward, AwardToAwardCommandConverter awardToAwardCommand) {
        this.awardRepository = awardRepository;
        this.awardCommandToAward = awardCommandToAward;
        this.awardToAwardCommand = awardToAwardCommand;
    }

    @RequestMapping(value = {"/awards", "award/list"})
    public String getAwards(Model model) {
        model.addAttribute("awards", awardRepository.findAll());
        return "award/list";
    }

    @RequestMapping("/award/{id}/show")
    public String getAwardDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("award", awardRepository.findById(id).orElse(null));
        return "award/show";
    }

    @GetMapping("/award/new")
    public String newAward(Model model){
        model.addAttribute("award", new AwardCommand());
        return "award/addedit";
    }

    @GetMapping("/award/{id}/edit")
    public String editAward(@PathVariable Long id, Model model) {
        Optional<Award> awardOptional = awardRepository.findById(id);
        if (awardOptional.isPresent()) {
            Award award = awardOptional.get();
            AwardCommand awardCommand = awardToAwardCommand.convert(award);
            model.addAttribute("award", awardCommand);
            return "award/addedit";
        } else {
            System.out.println("Award not found!");
            return "redirect:/awards";
        }
    }

    @GetMapping("/award/{id}/delete")
    public String deleteAward(@PathVariable Long id) {
        Optional<Award> awardOptional = awardRepository.findById(id);

        if (awardOptional.isPresent()) {
            Award awardToRemove = awardOptional.get();
            // Usuń powiązania z filmami
            awardToRemove.getMovies().forEach(movie -> movie.getAwards().remove(awardToRemove));
            awardToRemove.getMovies().clear();
            awardRepository.save(awardToRemove);  // Update movies before deleting award
            awardRepository.delete(awardToRemove);
            return "redirect:/awards";
        } else {
            return "redirect:/awards";
        }
    }

    @PostMapping("/award/")
    public String saveOrUpdate(@ModelAttribute AwardCommand command) {
        Optional<Award> awardById = awardRepository.findById(command.getId());
        Optional<Award> awardByName = awardRepository.getFirstByNameAndYear(command.getName(), command.getYear());

        if (awardById.isPresent()) {
            Award editedAward = awardById.get();
            editedAward.setName(command.getName());
            editedAward.setYear(command.getYear());
            awardRepository.save(editedAward);
            return "redirect:/award/" + editedAward.getId() + "/show";
        } else if (awardByName.isEmpty()) {
            Award newAward = awardCommandToAward.convert(command);
            Award savedAward = awardRepository.save(newAward);
            return "redirect:/award/" + savedAward.getId() + "/show";
        } else {
            System.out.println("Sorry, there's such award in db");
            return "redirect:/award/" + awardByName.get().getId() + "/show";
        }
    }


}
