package com.ironhack.homework_3.repository;

import com.ironhack.homework_3.model.Account;
import com.ironhack.homework_3.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
}
