package com.example.javaappwithspringframework.api.controllers;

import com.example.javaappwithspringframework.api.model.DirectorDTO;
import com.example.javaappwithspringframework.api.model.DirectorListDTO;
import com.example.javaappwithspringframework.services.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/director/")
public class DirectorApiController {

    private final DirectorService directorService;

    public DirectorApiController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectorDTO getDirectorById(@PathVariable Long id){
        return directorService.getDirectorById(id);
    }

    @GetMapping("getDirectorByFirstNameAndLastName")
    public ResponseEntity<DirectorDTO> getDirectorByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName){
        return new ResponseEntity<DirectorDTO>(directorService.getDirectorByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("getAllDirectors")
    public ResponseEntity<DirectorListDTO> getAllDirectors(){
        return new ResponseEntity<DirectorListDTO>(
                new DirectorListDTO(directorService.getAllDirectors()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> createNewDirector(@RequestBody DirectorDTO directorDTO){
        return new ResponseEntity<DirectorDTO>(directorService.createNewDirector(directorDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable Long id, @RequestBody DirectorDTO directorDTO){
        return new ResponseEntity<DirectorDTO>(directorService.updateDirector(id, directorDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id){

        directorService.deleteDirectorById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
