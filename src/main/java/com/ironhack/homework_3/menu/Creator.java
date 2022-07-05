package com.ironhack.homework_3.menu;

import com.ironhack.homework_3.enums.Industry;
import com.ironhack.homework_3.enums.Product;
import com.ironhack.homework_3.repository.*;
import lombok.Getter;
import com.ironhack.homework_3.model.*;
import com.ironhack.homework_3.style.Style;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Getter
@Setter

@Component
public class Creator {

    private AccountRepository accountRepository;
    private SalesRepsRepository salesRepsRepository;
    private ContactRepository contactRepository;
    private LeadRepository leadRepository;
    private OpportunityRepository opportunityRepository;

    @Autowired
    Data data;

    private Input input;
    private Printer printer;

    @Autowired
    public Creator(AccountRepository accountRepository, SalesRepsRepository salesRepsRepository, ContactRepository contactRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, Input input, Printer printer) {
        this.accountRepository = accountRepository;
        this.salesRepsRepository = salesRepsRepository;
        this.contactRepository = contactRepository;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
        this.input = input;
        this.printer = printer;
    }


    // _NEW functionality methods_

    // Method used to create a SALES REP independently
    public void createSalesRep() {
        System.out.println(Style.OCHER + "\nCreating a new SALES REP." + Style.DEFAULT);

        String name;
        boolean errorName = false;
        do {

            System.out.println("\nPlease input a name:");
            name = input.getString();

            try {
                errorName = Validator.isStringValid(name);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorName);

        // New SALES REP Object saved in database
        SalesRep salesRep = new SalesRep(name);
        salesRepsRepository.save(salesRep);

        // Print a SALES REP creation message
        System.out.println(Style.OCHER + "\nA new SALES REP has been created with the following info:" + Style.DEFAULT);
        System.out.println(salesRep);

    }

    // Method used to create an ACCOUNT independently or when a LEAD is converted
    public void createAccount() {

        System.out.println(Style.OCHER + "\nCreating a new ACCOUNT." + Style.DEFAULT);
        System.out.println("Please input the following:");

        System.out.println("\nChoose type of Industry:");
        Industry industry = input.chooseIndustry();

        System.out.println("\nNumber of employees of the Company:");
        int employeeCount = input.getIntegerHigherThanZero();


        String city;
        boolean errorCity = false;
        do {

            System.out.println("\nCity where the Company is based:");
            city = input.getString();

            try {
                errorCity = Validator.isStringValid(city);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCity);


        String country;
        boolean errorCountry = false;
        do {

            System.out.println("\nCountry where the Company is based:");
            country = input.getString();

            try {
                errorCountry = Validator.isStringValid(country);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCountry);

        // New ACCOUNT Object saved in database
        Account account = new Account(industry, employeeCount, city, country);
        accountRepository.save(account);

        // Print an ACCOUNT creation message
        System.out.println(Style.OCHER + "\nNew ACCOUNT created:" + Style.DEFAULT);
        System.out.println(account);

    }

    // Method used to create a LEAD independently
    public void createLead() {
        // First it is checked if there is any Sales Rep as we need to associate one to our Lead.
        if (salesRepsRepository.count() == 0L) {
            System.out.println(Style.RED + "\nThere is no Sales Rep saved in this database. A new Lead cannot be created." + Style.DEFAULT);
            System.out.println("\nPlease, select another option.");
            return;
        } else {
            System.out.println(Style.OCHER + "\nCreating a new LEAD." + Style.DEFAULT);
            System.out.println("Please input the following:");
        }

        // Then, the user is asked for all the necessary info

        String name;
        boolean errorName = false;
        do {

            System.out.println("\nName:");
            name = input.getString();

            try {
                errorName = Validator.isStringValid(name);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorName);


        String phoneNumber;
        boolean errorPhone = false;
        do {

            System.out.println("\nPhone Number:");
            phoneNumber = input.getString();

            try {
                errorPhone = Validator.isPhoneNumberValid(phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println("The phone number must have 9 digits. A prefix can be included.");
            }

        } while (!errorPhone);


        String email;
        boolean errorEmail = false;
        do {

            System.out.println("\nEmail:");
            email = input.getString();

            try {
                errorEmail = Validator.isEmailValid(email);
            } catch (IllegalArgumentException e) {
                System.out.println("The email address format is not valid.");
            }

        } while (!errorEmail);


        String companyName;
        boolean errorCompany = false;
        do {

            System.out.println("\nCompany Name:");
            companyName = input.getString();

            try {
                errorCompany = Validator.isStringValid(companyName);
            } catch (IllegalArgumentException e) {
                System.out.println("Input is whether empty or too long, or it may contain not valid characters.");
            }

        } while (!errorCompany);


        int idSalesRep = getExistingSalesRep();

        // New LEAD Object saved in database
        Lead lead = new Lead(name, phoneNumber, email, companyName, salesRepsRepository.findById(idSalesRep).get());
        leadRepository.save(lead);

        // Print a LEAD creation message
        System.out.println(Style.OCHER + "\nA new LEAD has been created with the following info:" + Style.DEFAULT);
        System.out.println(lead);

    }

    // Method used to create a CONTACT independently
    public void createContact() {
        // First it is checked if there is any Lead and any Account as we need to associate one of each to our Contact.
        if (leadRepository.count() == 0L) {
            System.out.println(Style.RED + "\nThere is no Lead saved in this database. A new Contact cannot be created." + Style.DEFAULT);
            System.out.println("\nPlease, select another option.");
            return;
        } else if (accountRepository.count() == 0L) {
            System.out.println(Style.RED + "\nThere is no Account saved in this database. A new Contact cannot be created." + Style.DEFAULT);
            System.out.println("\nPlease, select another option.");
            return;
        } else {
            System.out.println(Style.OCHER + "\nCreating a new CONTACT." + Style.DEFAULT);
            System.out.println("Please input the following:");
        }

        // Then, the user is asked for all the necessary info

        int idLead = getExistingLead();

        int idAccount = getExistingAccount();

        // New CONTACT Object saved in database
        Contact contact = new Contact(leadRepository.findById(idLead).get(), accountRepository.findById(idAccount).get());
        contactRepository.save(contact);

        // Print a CONTACT creation message
        System.out.println(Style.OCHER + "\nA new CONTACT has been created with the following info:" + Style.DEFAULT);
        System.out.println(contact);

    }

    //  Method that creates a CONTACT automatically when LEAD is converted, creating a new ACCOUNT
    public void createContact(Lead lead) {
        // This method is implemented right after the createAccount(Lead lead) method, triggered by the "convert <id number>" command.
        // This means that the Account associated is the last one saved in the accountRepository.
        Contact contact = new Contact(lead, accountRepository.findById((int)(accountRepository.count())).get());

        // New CONTACT Object saved in database with the LEAD information
        contactRepository.save(contact);

        // Print a CONTACT creation message
        System.out.println(Style.OCHER + "\nA new CONTACT has been created with the following info:" + Style.DEFAULT);
        System.out.println(contact);
    }

    //  Method that creates a CONTACT automatically when LEAD is converted, selecting an existing ACCOUNT
    public void createContact(Lead lead, Account account) {
        // This method is triggered by selecting that you do not want to create a new Account
        Contact contact = new Contact(lead, account);

        // New CONTACT Object saved in database with the LEAD information
        contactRepository.save(contact);

        // Print a CONTACT creation message
        System.out.println(Style.OCHER + "\nA new CONTACT has been created with the following info:" + Style.DEFAULT);
        System.out.println(contact);
    }

    // Method used when an OPPORTUNITY is created independently
    public void createOpportunity() {
        // First it is checked if there is any Contact and Sales Rep as we need to associate one of each to our Opportunity.
        if (contactRepository.count() == 0L) {
            System.out.println(Style.RED + "\nThere is no Contact saved in this database. A new Opportunity cannot be created." + Style.DEFAULT);
            System.out.println("\nPlease, select another option.");
            return;
        } else if (salesRepsRepository.count() == 0L) {
            System.out.println(Style.RED + "\nThere is no Sales Rep saved in this database. A new Opportunity cannot be created." + Style.DEFAULT);
            System.out.println("\nPlease, select another option.");
            return;
        } else {
            System.out.println(Style.OCHER + "\nCreating a new Opportunity." + Style.DEFAULT);
            System.out.println("Please input the following:");
        }

        // Then, the user is asked for all the necessary info


        int idContact;
        boolean errorContact = false;
        // Since decisionMaker and opportunity have a oneToOne relationship, it must be ensured that the contact exists
        // and is not previously linked to any other opportunity.
        do {

            System.out.println("\nDECISION MAKER id:");
            idContact = input.getIntegerHigherThanZero();

            // Determine whether or not that contact exists in the database
            try {
                contactRepository.findById(idContact).get();

                // Determine if the contact has been previously linked to another opportunity
                try {
                    Opportunity opportunity =
                            opportunityRepository.findByDecisionMaker(contactRepository.findById(idContact).get()).get();
                    if(opportunity != null) {
                        System.out.println(Style.RED + "\nThis decision maker is already linked to an Opportunity." + Style.DEFAULT);
                    }
                } catch (NoSuchElementException e) {
                    errorContact = true;
                }

            } catch (NoSuchElementException e) {
                System.out.println(Style.RED + "\nThe id entered does not correspond to any contact."  + Style.DEFAULT);
            }

            // Ask if the user wants to come back to the main menu
            if(!errorContact) {
                System.out.println(Style.DEFAULT + "\nWould you like to go back to the main menu? (Y/N)");
                while (true) {
                    String answer = input.getYesOrNo();
                    if (answer.equals("Y") || answer.equals("YES")) {
                        System.out.println("\nPlease, select other option.");
                        return;
                    } else if (answer.equals("N") || answer.equals("NO")) {
                        break;
                    } else {
                        System.out.println(Style.LIGHT_GRAY + "Invalid answer." + Style.DEFAULT);
                    }
                }
            }

        } while (!errorContact);


        int idSalesRep = getExistingSalesRep();


        System.out.println("\nChoose the product");
        Product product = input.chooseProduct();


        System.out.println("\nQuantity of trucks:");
        int quantity = input.getIntegerHigherThanZero();

        // New OPPORTUNITY Object saved in database
        Opportunity opportunity = new Opportunity(product, quantity, contactRepository.findById(idContact).get(),
                salesRepsRepository.findById(idSalesRep).get(), contactRepository.findById(idContact).get().getAccount());
        opportunityRepository.save(opportunity);

        // Print an OPPORTUNITY creation message
        System.out.println(Style.OCHER + "\nA new OPPORTUNITY has been created with the following info:" + Style.DEFAULT);
        System.out.println(opportunity);
    }





    // Method that creates an OPPORTUNITY when LEAD is converted, creating a new ACCOUNT
    public void createOpportunityByLeadConversion(Lead lead) {
        System.out.println(Style.OCHER + "\nAdditional information required for the OPPORTUNITY." + Style.DEFAULT);
        System.out.println("\nPlease input the following:");


        System.out.println("\nProduct type:");
        Product product = input.chooseProduct();


        System.out.println("\nQuantity of trucks");
        int quantity = input.getIntegerHigherThanZero();


        // This method is implemented right after the createContact(Lead lead) method and the createAccount(Lead lead) method,
        // triggered by the "convert <id number>" command. This means that the Contact associated with this Opportunity is the
        // last one saved in the contactRepository and the Account associated is the last one saved in the accountRepository.
        Opportunity opportunity = new Opportunity(product, quantity, contactRepository.findById((int)(contactRepository.count())).get(),
                lead.getSalesRep(), accountRepository.findById((int)(accountRepository.count())).get());

        // New OPPORTUNITY Object saved in database
        opportunityRepository.save(opportunity);

        // Print an OPPORTUNITY creation message
        System.out.println(Style.OCHER + "\nA new OPPORTUNITY has been created with the following info:" + Style.DEFAULT);
        System.out.println(opportunity);
    }

    // Method that creates an OPPORTUNITY when LEAD is converted, selecting an existing ACCOUNT
    public void createOpportunityByLeadConversion(Lead lead, Account account) {
        System.out.println(Style.OCHER + "\nAdditional information required for the OPPORTUNITY." + Style.DEFAULT);
        System.out.println("\nPlease input the following:");


        System.out.println("\nProduct type:");
        Product product = input.chooseProduct();


        System.out.println("\nQuantity of trucks");
        int quantity = input.getIntegerHigherThanZero();


        // This method is implemented right after the createContact(Lead lead) method triggered by the "convert <id number>" command.
        // This means that the Contact associated with this Opportunity is the last one saved in the contactRepository.
        Opportunity opportunity = new Opportunity(product, quantity, contactRepository.findById((int)(contactRepository.count())).get(),
                lead.getSalesRep(), account);

        // New OPPORTUNITY Object saved in database
        opportunityRepository.save(opportunity);

        // Print an OPPORTUNITY creation message
        System.out.println(Style.OCHER + "\nA new OPPORTUNITY has been created with the following info:" + Style.DEFAULT);
        System.out.println(opportunity);
    }


    // Utility methods

    // Requests an ACCOUNT id and validates whether or not it exists in the database
    public int getExistingAccount() {

        int idAccount;
        boolean errorAccount = false;
        do {

            System.out.println("\nACCOUNT id:");
            idAccount = input.getIntegerHigherThanZero();

            try {
                accountRepository.findById(idAccount).get();
                errorAccount = true;
            } catch (NoSuchElementException e) {
                System.out.println(Style.RED + "\nThe id entered does not correspond to any account.");
                System.out.println(Style.DEFAULT + "\nPlease, try again.");
            }

        } while (!errorAccount);

        return idAccount;

    }

    // Requests a LEAD id and validates whether or not it exists in the database
    public int getExistingLead() {
        int idLead;
        boolean errorLead = false;
        do {

            System.out.println("\nLEAD id:");
            idLead = input.getIntegerHigherThanZero();

            try {
                leadRepository.findById(idLead).get();
                errorLead = true;
            } catch (NoSuchElementException e) {
                System.out.println(Style.RED + "\nThe id entered does not correspond to any lead.");
                System.out.println(Style.DEFAULT + "\nPlease, try again.");
            }

        } while (!errorLead);

        return idLead;

    }

    // Requests a LEAD id and validates whether or not it exists in the database
    public int getExistingSalesRep() {

        int idSalesRep;
        boolean errorSRep = false;
        do {

            System.out.println("\nSALES REP id:");
            idSalesRep = input.getIntegerHigherThanZero();

            try {
                salesRepsRepository.findById(idSalesRep).get();
                errorSRep = true;
            } catch (NoSuchElementException e) {
                System.out.println(Style.RED + "\nThe id entered does not correspond to any sales rep.");
                System.out.println(Style.DEFAULT + "\nPlease, try again.");
            }

        } while (!errorSRep);

        return idSalesRep;

    }


    // _SHOW functionality methods_

    public void printAllAccounts() {
        for (Account account : accountRepository.findAll()) {
            System.out.println(account);
        }
    }

    public void printAllContacts() {
        for (Contact contact : contactRepository.findAll()) {
            System.out.println(contact);
        }
    }

    public void printAllLeads() {
        for (Lead lead : leadRepository.findAll()) {
            System.out.println(lead);
        }
    }

    public void printAllOpportunities() {
        for (Opportunity opportunity : opportunityRepository.findAll()) {
            System.out.println(opportunity);
        }
    }

    public void printAllSalesRep() {
        for (SalesRep salesRep : salesRepsRepository.findAll()) {
            System.out.println(salesRep);
        }
    }

}
