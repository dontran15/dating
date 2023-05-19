package com.nighthawk.spring_portfolio.database.dating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nighthawk.spring_portfolio.database.dating.ProfileTypes.Interest;
import com.nighthawk.spring_portfolio.database.dating.ProfileTypes.Lifestyle;
import com.nighthawk.spring_portfolio.database.dating.ProfileTypes.MiscType;
import com.nighthawk.spring_portfolio.database.dating.ProfileTypes.Personality;
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

    // profile (NOTE: still deciding whether to consider sentiment analysis or
    // making own algorithm for "pairings", probably don't need either)
    private ArrayList<Personality> personality = new ArrayList<>();

    private ArrayList<Lifestyle> lifestyle = new ArrayList<>();

    private ArrayList<Interest> interest = new ArrayList<>();

    private ArrayList<MiscType> miscType = new ArrayList<>();

    public DatingProfile(Person person) {
        this.person = person;
        initializeProfile();
    }

    private void initializeProfile() {

    }
}
