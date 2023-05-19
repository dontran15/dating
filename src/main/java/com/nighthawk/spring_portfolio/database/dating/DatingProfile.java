package com.nighthawk.spring_portfolio.database.dating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nighthawk.spring_portfolio.database.person.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DatingProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Person person;

    // personal profile
    private Map<String, String> profile = new HashMap<>();

    // preference for profile ** (NOTE: not sure if we need this)
    private Map<String, String> interests = new HashMap<>();

    private static String[] types = { "personality1", "personality2", "personality3", "lifestyle1", "lifestyle2",
            "lifestyle3", "song", "food", "quote", "movie", "ethnicity", "school", "job", "interest1", "interest2",
            "interest3" };

    public DatingProfile(Person person) {
        this.person = person;
        initializeProfile();
    }

    private void initializeProfile() {

    }
}
