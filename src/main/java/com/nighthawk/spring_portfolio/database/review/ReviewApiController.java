package com.nighthawk.spring_portfolio.database.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/review") // all requests in file begin with this URI
public class ReviewApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private ReviewJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Review>> getReviewername() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Review> addReviewername(@RequestParam("name") String reviewername,
            @RequestParam("Age") int age,
            @RequestParam("Review") String reviewtext,
            @RequestParam("Contact") String contact) {
        repository.save(new Review(null, reviewername, age, reviewtext, contact)); // JPA save
        long maxId = repository.getMaxId();
        Optional<Review> optional = repository.findById(maxId);
        if (optional.isPresent()) {
            Review review1 = optional.get();
            return new ResponseEntity(review1, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }
}