package com.nighthawk.spring_portfolio.database.dating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nighthawk.spring_portfolio.database.person.Person;
import com.nighthawk.spring_portfolio.database.person.PersonJpaRepository;

@RestController
@RequestMapping("/api/dating")
public class DatingAPIController {

    @Autowired
    private PersonJpaRepository personRepository;

    @Autowired
    private DatingProfileJpaRepository datingRepository;

    /*
     * GET List of Dating Profiles
     */
    @GetMapping("/all")
    public ResponseEntity<List<DatingProfile>> getProfiles() {
        return new ResponseEntity<>(datingRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
    }

    /*
     * GET individual DatingProfile using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable long id) {
        DatingProfile profile = datingRepository.findById(id).orElse(null);
        if (profile == null) {
            return new ResponseEntity<>("profile not found", HttpStatus.OK);
        }

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return new ResponseEntity<>("person not found", HttpStatus.OK);
        }

        personRepository.delete(person);

        return new ResponseEntity<>("" + id + " and all relevant grades deleted", HttpStatus.OK);
    }
}
