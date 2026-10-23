package com.roman.barber_shop.persistence.repository;

import com.roman.barber_shop.persistence.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
}
