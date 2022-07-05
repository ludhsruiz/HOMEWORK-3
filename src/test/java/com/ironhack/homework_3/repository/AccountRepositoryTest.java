package com.ironhack.homework_3.repository;

import com.ironhack.homework_3.Homework3Application;
import com.ironhack.homework_3.menu.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @MockBean
    private com.ironhack.homework_3.Homework3Application Homework3Application;

    @Autowired
    private Data data;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        data.populateRepos();
    }

    @AfterEach
    void tearDown() {
        data.cleanAllTables();
    }

    @Test
    void meanEmployeeCount() {
        int result = accountRepository.meanEmployeeCount();
        assertEquals(97.0,result);
    }

    @Test
    void medianEmployeeCount() {
    }

    @Test
    void maxEmployeeCount() {
        int result = accountRepository.maxEmployeeCount();
        assertEquals(250,result);
    }

    @Test
    void minEmployeeCount() {
        int result = accountRepository.minEmployeeCount();
        assertEquals(12,result);
    }

}