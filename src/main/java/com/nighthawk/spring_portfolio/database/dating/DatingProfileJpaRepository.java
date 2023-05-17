package com.nighthawk.spring_portfolio.database.dating;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DatingProfileJpaRepository extends JpaRepository<DatingProfile, Long> {

    Optional<DatingProfile> findByEmail(String email);
    Optional<DatingProfile> findByName(String name);
    Optional<List<DatingProfile>> findAllByOrderByNameAsc();

    @Query(value = "SELECT * FROM DatingProfile d WHERE d.name LIKE ?1 or d.email LIKE ?1", nativeQuery = true)
    List<DatingProfile> findByLikeTermNative(String term);
    
}
