package com.example.demo.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class AlienJPAController {
    @Autowired
    private AlienRepo repository;

    @GetMapping("/get/{id}")
    public ResponseEntity<Alien> getAlien(@PathVariable(name = "id") final int id) {
        Alien alien = repository.findById(id).get();
        return ResponseEntity.ok().body(alien);
    }

    @PostMapping("/create")
    public ResponseEntity<Alien> addAlien(@RequestBody final Alien alien) {
        repository.save(alien);
        return ResponseEntity.created(URI.create("/api/create")).body(alien);
    }
}
