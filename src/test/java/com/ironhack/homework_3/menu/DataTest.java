package com.ironhack.homework_3.menu;

import com.ironhack.homework_3.Homework3Application;
import com.ironhack.homework_3.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataTest {


    @MockBean
    private com.ironhack.homework_3.Homework3Application Homework3Application;

    @Autowired
    private Data data;

    @Autowired
    private SalesRepsRepository salesRepsRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;


    @Test
    void populateRepos_correctImplementationOfAccounts() {

        int numberAccountsBefore = accountRepository.findAll().size();
        data.populateRepos();
        int numberAccountsAfter = accountRepository.findAll().size();

        assertEquals(numberAccountsBefore + 5, numberAccountsAfter);
    }

    @Test
    void populateRepos_correctImplementationOfSalesRep() {

        int numberSalesRepBefore = salesRepsRepository.findAll().size();
        data.populateRepos();
        int numberSalesRepAfter = salesRepsRepository.findAll().size();

        assertEquals(numberSalesRepBefore + 5, numberSalesRepAfter);
    }

    @Test
    void populateRepos_correctImplementationOfContacts() {

        int numberContactsBefore = contactRepository.findAll().size();
        data.populateRepos();
        int numberContactsAfter = contactRepository.findAll().size();

        assertEquals(numberContactsBefore + 5, numberContactsAfter);
    }

    @Test
    void populateRepos_correctImplementationOfLeads() {

        int numberLeadsBefore = leadRepository.findAll().size();
        data.populateRepos();
        int numberLeadsAfter = leadRepository.findAll().size();

        assertEquals(numberLeadsBefore + 5, numberLeadsAfter);
    }

    @Test
    void populateRepos_correctImplementationOfOpportunities() {

        int numberOpportunitiesBefore = opportunityRepository.findAll().size();
        data.populateRepos();
        int numberOpportunitiesAfter = opportunityRepository.findAll().size();

        assertEquals(numberOpportunitiesBefore + 5, numberOpportunitiesAfter);
    }

    @Test
    void cleanAllTables() {

        data.cleanAllTables();
        assertEquals(0, accountRepository.findAll().size());
        assertEquals(0, salesRepsRepository.findAll().size());
        assertEquals(0, contactRepository.findAll().size());
        assertEquals(0, leadRepository.findAll().size());
        assertEquals(0, opportunityRepository.findAll().size());
    }
}

