package com.nighthawk.spring_portfolio.database.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewApiController {

    @Autowired
    private ReviewJpaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Review>> getReviewername() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Review> addReviewername(@RequestParam("name") String reviewername,
            @RequestParam("Age") int age,
            @RequestParam("Review") String reviewtext,
            @RequestParam("Contact") String contact) {
        Review savedReview = repository.save(new Review(null, reviewername, age, reviewtext, contact));
        if (savedReview != null) {
            return new ResponseEntity<>(savedReview, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}