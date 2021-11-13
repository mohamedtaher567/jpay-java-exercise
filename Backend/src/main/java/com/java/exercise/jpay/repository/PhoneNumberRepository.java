package com.java.exercise.jpay.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.exercise.jpay.model.PhoneNumber;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

  List<PhoneNumber> findByValidIn(Set<Boolean> valid, Pageable pageable);

  Long countByValidIn(Set<Boolean> valid);

  List<PhoneNumber> findByCountryCodeIn(Set<Integer> countryCode, Pageable pageable);

  Long countByCountryCodeIn(Set<Integer> countryCode);

  List<PhoneNumber> findByValidInAndCountryCodeIn(Set<Boolean> valid, Set<Integer> countryCode, Pageable pageable);

  Long countByValidInAndCountryCodeIn(Set<Boolean> valid, Set<Integer> countryCode);
}
