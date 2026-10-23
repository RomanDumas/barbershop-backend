package com.roman.barber_shop.persistence.repository;

import com.roman.barber_shop.persistence.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
