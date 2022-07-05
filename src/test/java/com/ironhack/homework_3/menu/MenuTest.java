package com.ironhack.homework_3.menu;

import com.ironhack.homework_3.Homework3Application;
import com.ironhack.homework_3.enums.Status;
import com.ironhack.homework_3.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuTest {


    @MockBean
    private com.ironhack.homework_3.Homework3Application Homework3Application;

    @Autowired
    private Data data;

    @Autowired
    private Menu menu;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SalesRepsRepository salesRepsRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Test
    public void interpretInput_nonRecognizableCommand() throws InterruptedException {
        String[] command1 = {"Hola"};
        String[] command2 = {"123"};
        String[] command3 = {"covert"};

        assertThrows(NullPointerException.class, () -> menu.interpretInput(command1));
        assertThrows(NullPointerException.class, () -> menu.interpretInput(command2));
        assertThrows(NullPointerException.class, () -> menu.interpretInput(command3));
    }

    @Test
    public void changeStatus_validModification() {
        data.populateRepos();
        menu.changeStatus(Status.CLOSED_WON, 1);
        assertEquals(Status.CLOSED_WON, opportunityRepository.findById(1).get().getStatus());
    }


}