package com.nighthawk.spring_portfolio.database;

import com.nighthawk.spring_portfolio.database.role.Role;
import com.nighthawk.spring_portfolio.database.role.RoleJpaRepository;
import com.nighthawk.spring_portfolio.database.person.Person;
import com.nighthawk.spring_portfolio.database.person.PersonJpaRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {
    // Inject repositories
    @Autowired
    RoleJpaRepository roleJpaRepository;
    @Autowired
    ModelRepository modelRepository;
    @Autowired
    PersonJpaRepository personJpaRepository;

    @Bean
    CommandLineRunner run() { // The run() method will be executed after the application starts
        return args -> {

            System.out.println("Person Databases Init");

            String[] roles = { "ROLE_USER", "ROLE_ADMIN", "ROLE_ANONYMOUS" };
            for (String role : roles) {
                if (roleJpaRepository.findByName(role) == null)
                    roleJpaRepository.save(new Role(null, role));
            }

            Date dob1 = new SimpleDateFormat("MM-dd-yyyy").parse("11-07-2005");
            Date dob2 = new SimpleDateFormat("MM-dd-yyyy").parse("08-11-2005");

            String email1 = "ok@ok.com";
            String email2 = "user@user.com";

            Person person1 = new Person(email1, "123qwerty", "Admin", dob1,
                    modelRepository.findRole("ROLE_ADMIN"));
            Person person2 = new Person(email2, "123qwerty", "Test User", dob2,
                    modelRepository.findRole("ROLE_USER"));

            if (personJpaRepository.findByEmail(email1) == null) {
                modelRepository.save(person1);
            }

            if (personJpaRepository.findByEmail(email2) == null) {
                modelRepository.save(person2);
            }

            System.out.println("Databases Init");
        };
    }
}