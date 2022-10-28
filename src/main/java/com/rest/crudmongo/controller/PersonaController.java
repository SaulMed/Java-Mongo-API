package com.rest.crudmongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.crudmongo.model.Persona;
import com.rest.crudmongo.respository.PersonaRepository;

//@CrossOrigin
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository repo;

    @GetMapping
    List<Persona> find() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    Persona findById(@PathVariable String id) {
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Persona save(@RequestBody Persona persona) {
        return repo.save(persona);
    }

    @PutMapping("/{id}")
    Persona update(@PathVariable String id, @RequestBody Persona persona) {
        Persona personaEdit = repo.findById(id).orElseThrow(RuntimeException::new);
        personaEdit.setNombre(persona.getNombre());
        personaEdit.setApellidos(persona.getApellidos());
        personaEdit.setEmail(persona.getEmail());
        personaEdit.setEdad(persona.getEdad());
        return repo.save(personaEdit);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        Persona personaToDelete = repo.findById(id).orElseThrow(RuntimeException::new);
        repo.delete(personaToDelete);
    }

}