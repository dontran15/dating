package com.nighthawk.spring_portfolio.database.dating;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private ProfileDetailJpaRepository detailRepository;

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

    @PostMapping("/addDetail")
    public ResponseEntity<Object> addDetail(@RequestBody final Map<String, Object> map) {
        String id = (String) map.get("id"); // datingProfile ID
        String type = (String) map.get("type");
        String detail = (String) map.get("detail");

        DatingProfile datingProfile = datingRepository.findById(Long.valueOf(id)).orElse(null);

        if (datingProfile == null) {
            return new ResponseEntity<>("profile not found", HttpStatus.OK);
        }

        ProfileDetail oldDetail = detailRepository.findByDetail(detail);
        if (oldDetail == null) {
            ProfileDetail newDetail = new ProfileDetail(type);
            newDetail.setDetail(detail);

            datingProfile.getProfileDetail().add(newDetail);

            return new ResponseEntity<>(newDetail, HttpStatus.OK);
        } else {
            datingProfile.getProfileDetail().add(oldDetail);
            return new ResponseEntity<>(oldDetail, HttpStatus.OK);
        }
    }

    @PostMapping("/updateDetail")
    public ResponseEntity<Object> updateDetail(@RequestBody final Map<String, Object> map) {
        String type = (String) map.get("type");
        String detail = (String) map.get("detail");

        ProfileDetail profileDetail = detailRepository.findByDetail(detail);
        if (profileDetail == null) {
            return new ResponseEntity<>("detail does not exists", HttpStatus.OK);
        }

        profileDetail.setDetail(detail);
        detailRepository.save(profileDetail);

        return new ResponseEntity<>(detail + " detail saved", HttpStatus.OK);
    }

    @GetMapping("/detailSearch")
    public ResponseEntity<Object> getProfile(@RequestBody final Map<String, Object> map) {
        String detailString = (String) map.get("detail");

        ProfileDetail detail = detailRepository.findByDetail(detailString);

        if (detail == null) {
            return new ResponseEntity<>("detail not found", HttpStatus.OK);
        }

        return new ResponseEntity<>(datingRepository.findAllByProfileDetail(detail), HttpStatus.OK);
    }
}
