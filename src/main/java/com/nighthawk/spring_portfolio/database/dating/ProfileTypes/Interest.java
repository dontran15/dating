package com.nighthawk.spring_portfolio.database.dating.ProfileTypes;

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
public class Interest extends ProfileType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
