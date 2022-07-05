package com.ironhack.homework_3.repository;

import com.ironhack.homework_3.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

        // Mean number of employees of all the registered companies
        @Query(value = "SELECT AVG(employees) FROM accounts_table", nativeQuery = true)
        int meanEmployeeCount();

        // Median number of employees of all registered companies
//        @Query(value = int medianEmployeeCount();

        // Number of employees of the registered company with more staff
        @Query(value = "SELECT MAX(employees) FROM accounts_table", nativeQuery = true)
        int maxEmployeeCount();

        // Number of employees of the registered company with fewer staff
        @Query(value = "SELECT MIN(employees) FROM accounts_table", nativeQuery = true)
        int minEmployeeCount();


}
