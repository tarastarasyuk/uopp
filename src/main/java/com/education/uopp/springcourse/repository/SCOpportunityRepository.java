package com.education.uopp.springcourse.repository;

import com.education.uopp.springcourse.model.SCOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SCOpportunityRepository extends JpaRepository<SCOpportunity, Long> {
}
