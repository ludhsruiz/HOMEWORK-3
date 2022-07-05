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
class OpportunityRepositoryTest {

    @MockBean
    private com.ironhack.homework_3.Homework3Application Homework3Application;

    @Autowired
    private Data data;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @BeforeEach
    void setUp() {
        data.populateRepos();
    }

    @AfterEach
    void tearDown() {
        data.cleanAllTables();
    }

    @Test
    void reportOpportunityByProduct() {
        String[][] result = opportunityRepository.reportOpportunityByProduct();

        assertEquals(3, result.length);
        assertEquals("2",result[2][1]);
        assertEquals("FLATBED",result[1][0]);
    }

    @Test
    void reportOpportunityClosedWonByProduct() {
        String[][] result = opportunityRepository.reportClosedWonOpportunityByProduct();

        assertEquals(0, result.length);
    }

    @Test
    void reportOpportunityClosedLostByProduct() {
        String[][] result = opportunityRepository.reportClosedLostOpportunityByProduct();

        assertEquals(0, result.length);
    }

    @Test
    void reportOpportunityOpenByProduct() {
        String[][] result = opportunityRepository.reportOpenOpportunityByProduct();

        assertEquals(3, result.length);
        assertEquals("2",result[0][1]);
        assertEquals("FLATBED",result[1][0]);
    }

    @Test
    void reportOpportunitiesByCountry() {
        String[][] result = opportunityRepository.reportOpportunitiesByCountry();

        assertEquals(4, result.length);
        assertEquals("2",result[1][1]);
        assertEquals("Portugal",result[3][0]);
    }

    @Test
    void reportClosedWonOpportunitiesByCountry() {
        String[][] result = opportunityRepository.reportClosedWonOpportunitiesByCountry();

        assertEquals(0, result.length);
    }

    @Test
    void reportClosedLostOpportunitiesByCountry() {
        String[][] result = opportunityRepository.reportClosedLostOpportunitiesByCountry();

        assertEquals(0, result.length);
    }

    @Test
    void reportOpenOpportunitiesByCountry() {
        String[][] result = opportunityRepository.reportOpenOpportunitiesByCountry();

        assertEquals(4, result.length);
        assertEquals("2",result[0][1]);
        assertEquals("Switzerland",result[1][0]);
    }

    @Test
    void reportOpportunitiesByCity() {
        String[][] result = opportunityRepository.reportOpportunitiesByCity();

        assertEquals(4, result.length);
        assertEquals("1",result[0][1]);
        assertEquals("Faro",result[3][0]);
    }

    @Test
    void reportClosedWonOpportunitiesByCity() {
        String[][] result = opportunityRepository.reportClosedWonOpportunitiesByCity();

        assertEquals(0, result.length);
    }

    @Test
    void reportClosedLostOpportunitiesByCity() {
        String[][] result = opportunityRepository.reportClosedLostOpportunitiesByCity();

        assertEquals(0, result.length);
    }

    @Test
    void reportOpenOpportunitiesByCity() {
        String[][] result = opportunityRepository.reportOpenOpportunitiesByCity();

        assertEquals(4, result.length);
        assertEquals("2",result[0][1]);
        assertEquals("Basel",result[1][0]);
    }

    @Test
    void reportOpportunitiesByIndustry() {
        String[][] result = opportunityRepository.reportOpportunitiesByIndustry();

        assertEquals(4, result.length);
        assertEquals("1",result[0][1]);
        assertEquals("OTHER",result[3][0]);
    }

    @Test
    void reportClosedWonOpportunitiesByIndustry() {
        String[][] result = opportunityRepository.reportClosedWonOpportunitiesByIndustry();

        assertEquals(0, result.length);
    }

    @Test
    void reportClosedLostOpportunitiesByIndustry() {
        String[][] result = opportunityRepository.reportClosedLostOpportunitiesByIndustry();

        assertEquals(0, result.length);
    }

    @Test
    void reportOpenOpportunitiesByIndustry() {
        String[][] result = opportunityRepository.reportOpenOpportunitiesByIndustry();

        assertEquals(4, result.length);
        assertEquals("2",result[0][1]);
        assertEquals("MANUFACTURING",result[1][0]);
    }

    @Test
    void meanQuantity() {
        double result = opportunityRepository.meanQuantity();
        assertEquals(6.4,result);
    }

    @Test
    void medianQuantity() {
    }

    @Test
    void maxQuantity() {
        double result = opportunityRepository.maxQuantity();
        assertEquals(9.0,result);
    }

    @Test
    void minQuantity() {
        double result = opportunityRepository.minQuantity();
        assertEquals(1.0,result);
    }

    @Test
    void meanOppsPerAccount() {
        double result = opportunityRepository.meanOppsPerAccount();
        assertEquals(1.25,result);
    }

    @Test
    void medianOppsPerAccount() {

    }

    @Test
    void maxOppsPerAccount() {
        double result = opportunityRepository.maxOppsPerAccount();
        assertEquals(2.0,result);
    }

    @Test
    void minOppsPerAccount() {
        double result = opportunityRepository.minOppsPerAccount();
        assertEquals(1.0,result);
    }
}