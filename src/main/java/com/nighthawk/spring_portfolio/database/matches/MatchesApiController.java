package com.nighthawk.spring_portfolio.database.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/matches") // all requests in file begin with this URI
public class MatchesApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private MatchesJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Matches>> getName() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Matches> addName(@RequestParam("name") String name,
            @RequestParam("Age") int age,
            @RequestParam("Location") String location,
            @RequestParam("Pronouns") String pronouns) {
        Matches savedMatches = repository.save(new Matches(null, name, age, location, pronouns));
        if (savedMatches != null) {
            return new ResponseEntity<>(savedMatches, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMatches(@RequestParam("name") String name,
            @RequestParam("Age") int age,
            @RequestParam("Location") String location,
            @RequestParam("Pronouns") String pronouns) {
        List<Matches> matchesToDelete = repository.findByNameAndAgeAndLocationAndPronouns(name, age,
                location, pronouns);
        if (!matchesToDelete.isEmpty()) {
            repository.deleteAll(matchesToDelete);
            return new ResponseEntity<>("match(es) deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Match not found", HttpStatus.NOT_FOUND);
    }
}