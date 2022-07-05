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
class SalesRepsRepositoryTest {


    @MockBean
    private com.ironhack.homework_3.Homework3Application Homework3Application;

    @Autowired
    private Data data;

    @Autowired
    private SalesRepsRepository salesRepsRepository;

    @BeforeEach
    void setUp() {
        data.populateRepos();
    }

    @AfterEach
    void tearDown() {
        data.cleanAllTables();
    }


    @Test
    void  reportLeadsBySalesRep(){
        String[][] result = salesRepsRepository.reportLeadsBySalesRep();

        assertEquals(4, result.length);
        assertEquals("2",result[2][1]);
        assertEquals("Atul Gupta",result[3][0]);
    }

    @Test
    void reportOpportunitiesBySalesRep() {
        String[][] result = salesRepsRepository.reportOpportunitiesBySalesRep();

        assertEquals(3, result.length);
        assertEquals("2",result[0][1]);
        assertEquals("Julia Dunst",result[2][0]);
    }

    @Test
    void reportClosedWonOpportunitiesBySalesRep() {
        String[][] result = salesRepsRepository.reportClosedWonOpportunitiesBySalesRep();

        assertEquals(0, result.length);
    }

    @Test
    void reportClosedLostOpportunitiesBySalesRep() {
        String[][] result = salesRepsRepository.reportClosedLostOpportunitiesBySalesRep();

        assertEquals(0, result.length);
    }

    @Test
    void reportOpenOpportunitiesBySalesRep() {
        String[][] result = salesRepsRepository.reportOpenOpportunitiesBySalesRep();

        assertEquals(3, result.length);
        assertEquals("2",result[0][1]);
        assertEquals("Marian Garcia",result[0][0]);
    }

}