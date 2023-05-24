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

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(@RequestParam("name") String reviewername,
            @RequestParam("Age") int age,
            @RequestParam("Review") String reviewtext,
            @RequestParam("Contact") String contact) {
        List<Review> reviewsToDelete = repository.findByReviewernameAndAgeAndReviewtextAndContact(reviewername, age,
                reviewtext, contact);
        if (!reviewsToDelete.isEmpty()) {
            repository.deleteAll(reviewsToDelete);
            return new ResponseEntity<>("Review(s) deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }
    // Finds reviews based on name, age, reviewtext, and contact. If it finds the
    // matching review, it deletes it. If it doesn't, review isn't deleted.
}
