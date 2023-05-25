package com.nighthawk.spring_portfolio.database.matches;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface MatchesJpaRepository extends JpaRepository<Matches, Long> {
    // JPA has many built in methods, these few have been prototyped for this
    // application
    void save(String name);

    // A
    List<Matches> findByNameIgnoreCase(String name); // look to see if Joke(s) exist

    @Query(value = "SELECT * FROM Practice p WHERE p.problem LIKE ?1 or p.Tags LIKE ?1", nativeQuery = true)
    List<Matches> findByNameorLocation(String term);

    List<Matches> findByNameAndAgeAndLocationAndPronouns(String name, int age, String location, String pronouns);

   //  @Query(value = "SELECT max(id) FROM Practice")
    // long getMaxId();
}
