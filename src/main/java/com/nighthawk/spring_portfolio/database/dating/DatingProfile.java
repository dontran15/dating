package com.nighthawk.spring_portfolio.database.dating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

    @JoinColumn(name = "person_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    // profile (NOTE: still deciding whether to consider sentiment analysis or
    // making own algorithm for "pairings", probably don't need either)
    @JoinColumn(name = "profileDetail_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProfileDetail> profileDetails;

    public DatingProfile(Person person) {
        this.person = person;
        this.profileDetails = new ArrayList<>();
    }

    public void addProfile(ProfileDetail detail) {
        profileDetails.add(detail);
    }

    @Override
    public String toString() {
        return "";
    }
}
