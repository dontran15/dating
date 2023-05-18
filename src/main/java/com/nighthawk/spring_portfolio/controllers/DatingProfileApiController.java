package com.nighthawk.spring_portfolio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nighthawk.spring_portfolio.database.dating.DatingProfile;
import com.nighthawk.spring_portfolio.database.dating.DatingProfileJpaRepository;

@RestController
@RequestMapping("/api/dating")
public class DatingProfileApiController {
    
    @Autowired
    DatingProfileJpaRepository datingProfileJpaRepository;

    @GetMapping("/all")
    public ResponseEntity<Object> getDatingProfiles() {
        Optional<List<DatingProfile>> profiles = datingProfileJpaRepository.findAllByOrderByNameAsc();

        if (profiles.get().isEmpty()) {
            return new ResponseEntity<>("No dating profiles", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Object>(profiles.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDatingProfile(@PathVariable long id) {
        Optional<DatingProfile> profile = datingProfileJpaRepository.findById(id);

        if (profile.get() == null) {
            return new ResponseEntity<>("Dating profile not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(profile.get(), HttpStatus.OK);
    }

    // I'll do this crap later
    @GetMapping("/search/{term}")
    public ResponseEntity<Object> getDatingProfilesByTerm() {
        return new ResponseEntity<Object>(null, null, 0);
    }

}
