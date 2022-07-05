package com.ironhack.homework_3.menu;

import com.ironhack.homework_3.repository.*;
import com.ironhack.homework_3.style.Style;
import com.ironhack.homework_3.model.*;
import com.ironhack.homework_3.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Component
public class Menu {


    private AccountRepository accountRepository;
    private SalesRepsRepository salesRepsRepository;
    private ContactRepository contactRepository;
    private LeadRepository leadRepository;
    private OpportunityRepository opportunityRepository;

    @Autowired
    Data data;
    @Autowired
    Creator creator;

    @Autowired
    public Menu(AccountRepository accountRepository, SalesRepsRepository salesRepsRepository, ContactRepository contactRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository) {
        this.accountRepository = accountRepository;
        this.salesRepsRepository = salesRepsRepository;
        this.contactRepository = contactRepository;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
    }


    private final Printer printer = new Printer();
    private final Input input = new Input(printer);


    public void controlLoop() throws InterruptedException {
        Command command;
        do {
            String[] inputList = splitInput(input.getString());
            command = input.getCommandFromString(inputList[0]);
            if (Objects.isNull(command)) {
                printer.printTypoInfo(inputList[0]);
            } else {
                try {
                    interpretInput(inputList);
                } catch (NullPointerException exception) {
                    printer.print(exception.getMessage());
                } catch (NumberFormatException exception) {
                    printer.print("Incorrect format of Id. Please try again.");
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printer.print("Must input Id number. Please try again.");
                }
            }
        } while (command != Command.EXIT);
        System.out.println(Style.LIGHT_GRAY + "\n\n\n\n\n\n\n\n\n\n\nAll data saved");
        System.out.println(Style.OCHER + "Thank you for using the cleanCRM. Have a nice day.\n" + Style.DEFAULT);
        Thread.sleep(500);
        input.close();
    }

    // Separates each word of the input
    public String[] splitInput(String string) {
        return string.trim().split(" ");
    }

    // Read the input and trigger the corresponding methods
    public void interpretInput(String[] inputList) throws InterruptedException {
        creator = new Creator(accountRepository, salesRepsRepository, contactRepository, leadRepository, opportunityRepository, input, printer);
        Command command = input.getCommandFromString(inputList[0]);
        ObjectType objectType;
        int id;

        switch (command) {
            case NEW:
                objectType = input.getObjectType(inputList[1]);
                if (Objects.isNull(objectType)) {
                    printer.printTypoInfo(inputList[1]);
                } else {
                    create(objectType);
                }
                break;

            case SHOW:
                objectType = input.getObjectType(inputList[1]);
                if (Objects.isNull(objectType)) {
                    printer.printTypoInfo(inputList[1]);
                } else {
                    show(objectType);
                }
                break;

            case LOOKUP:
                objectType = input.getObjectType(inputList[1]);
                if (Objects.isNull(objectType)) {
                    printer.printTypoInfo(inputList[1]);
                } else {
                    id = Integer.parseInt(inputList[2]);
                    lookup(objectType, id);
                }
                break;

            case CONVERT:
                id = Integer.parseInt(inputList[1]);
                convert(id);
                break;

            case REPORT:
                ReportTarget reportTarget = input.getReportTarget(inputList[1]);
                ReportBy reportBy = input.getReportBy(inputList[3]);
                if (Objects.isNull(reportTarget)) {
                    printer.printTypoInfo(inputList[1]);
                } else if(Objects.isNull(reportBy)) {
                    printer.printTypoInfo(inputList[3]);
                } else if(inputList[2].equalsIgnoreCase("by")) {
                    report(reportTarget, reportBy);
                } else {
                    printer.print("Incorrect command structure. Please try again.");
                }
                break;

            case CLOSE_LOST:
                id = Integer.parseInt(inputList[1]);
                changeStatus(Status.CLOSED_LOST, id);
                break;
            case CLOSE_WON:
                id = Integer.parseInt(inputList[1]);
                changeStatus(Status.CLOSED_WON, id);
                break;
            case OPEN:
                id = Integer.parseInt(inputList[1]);
                changeStatus(Status.OPEN, id);
                break;

            case MEAN:
            case MEDIAN:
            case MIN:
            case MAX:
                StateObject stateObject = input.getStateObject(inputList[1]);
                if (Objects.isNull(stateObject)) {
                    printer.printTypoInfo(inputList[1]);
                } else {
                    reportStates(command, stateObject);
                }
                break;

            case POPULATE:
                data = new Data(accountRepository, salesRepsRepository, contactRepository, leadRepository, opportunityRepository);
                System.out.println(Style.LIGHT_GRAY + "\nThis operation may take some seconds");
                printer.pleaseWait();
                data.populateRepos();
                break;

            case HELP:
                printer.helpPage();
                break;
        }
    }

    public void create(ObjectType objectType) {
        switch (objectType) {
            case ACCOUNT:
                creator.createAccount();
                break;
            case CONTACT:
                creator.createContact(); //When a CONTACT is created independently, it will ask for a LEAD Id
                break;
            case LEAD:
                creator.createLead();
                break;
            case OPPORTUNITY:
                creator.createOpportunity();
                break;
            case SALESREP:
                creator.createSalesRep();
                break;
        }
    }

    public void show(ObjectType objectType) {
        System.out.println(Style.OCHER + "\nShows all " + objectType.getPluralForm() + ".\n" + Style.DEFAULT);
        switch (objectType) {
            case ACCOUNT:
                creator.printAllAccounts();
                break;
            case CONTACT:
                creator.printAllContacts();
                break;
            case LEAD:
                creator.printAllLeads();
                break;
            case OPPORTUNITY:
                creator.printAllOpportunities();
                break;
            case SALESREP:
                creator.printAllSalesRep();
                break;
        }
    }

    // Print an specific Object fetched from database
    public void lookup(ObjectType objectType, int id) {
        try{
            switch (objectType) {
                case ACCOUNT:
                    System.out.println(creator.getAccountRepository().findById(id).get());
                    break;
                case CONTACT:
                    System.out.println(creator.getContactRepository().findById(id).get());
                    break;
                case LEAD:
                    System.out.println(creator.getLeadRepository().findById(id).get());
                    break;
                case OPPORTUNITY:
                    System.out.println(creator.getOpportunityRepository().findById(id).get());
                    break;
                case SALESREP:
                    System.out.println(creator.getSalesRepsRepository().findById(id).get());
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println(Style.RED + "\nThe id entered does not correspond to any " + objectType.toString().toLowerCase() + ".");
            System.out.println(Style.DEFAULT + "\nPlease, try again.");
            return;
        }
    }

    public void convert(int idLead) throws InterruptedException {
        // When a Lead is converted a Contact, Opportunity and Account are automatically created and the Lead must be deleted.
        Lead lead;
        try{
            lead = leadRepository.findById(idLead).get();
        } catch (NoSuchElementException e) {
            System.out.println(Style.RED + "\nThe id entered does not correspond to any lead.");
            System.out.println(Style.DEFAULT + "\nPlease, select another option.");
            return;
        }
        System.out.println(Style.OCHER + "\nConverting LEAD nº " + idLead + " to CONTACT, ACCOUNT and OPPORTUNITY\n" + Style.DEFAULT);
        Thread.sleep(500);
        System.out.println("\nWould you like to create a new Account? (Y/N)");
        while(true) {
            String answer = input.getYesOrNo();
            if (answer.equals("Y") || answer.equals("YES")) {
                creator.createAccount();
                Thread.sleep(1000);
                System.out.println(Style.OCHER + "\nConverting LEAD to CONTACT..." + Style.DEFAULT);
                Thread.sleep(2000);
                creator.createContact(lead);
                Thread.sleep(1800);
                creator.createOpportunityByLeadConversion(lead);
                break;
            } else if (answer.equals("N") || answer.equals("NO")) {
                int idAccount = creator.getExistingAccount();
                Account account = accountRepository.findById(idAccount).get();
                System.out.println(account);
                Thread.sleep(800);
                System.out.println(Style.OCHER + "\nConverting LEAD to CONTACT..." + Style.DEFAULT);
                Thread.sleep(2000);
                creator.createContact(lead, account);
                Thread.sleep(1800);
                creator.createOpportunityByLeadConversion(lead, account);
                break;
            } else {
                System.out.println("Invalid response.");
            }
        }

        Thread.sleep(800);
        leadRepository.deleteById(idLead);
        printer.pleaseWait();
        System.out.println(Style.OCHER + "LEAD HAS BEEN SUCCESSFULLY CONVERTED AND DELETED\n\n" + Style.DEFAULT);
    }

    // Change the Opportunity status to Closed-Won, Closed-Lost or Open
    public void changeStatus(Status status, int id) {
        Opportunity opportunity;

        try{
            opportunity = opportunityRepository.findById(id).get();
        } catch (NoSuchElementException e){
            System.out.println(Style.RED + "\nThe id entered does not correspond to any opportunity.");
            System.out.println(Style.DEFAULT + "\nPlease, select another option.");
            return;
        }

        opportunityRepository.findById(id);
        opportunity.setStatus(status);
        opportunityRepository.save(opportunity);
        System.out.println(Style.OCHER + "OPPORTUNITY with an id of " + id + " changed to " + status +".\n" + Style.DEFAULT);
    }

    // Create a report by Sales Rep, Product, City, Country or Industry
    public void report(ReportTarget reportTarget, ReportBy reportBy) {
        System.out.println(Style.OCHER + "\n" + reportTarget + " BY " + reportBy + Style.DEFAULT);

        switch (reportBy) {
            case SALESREP:
                switch (reportTarget) {
                    case LEAD:
                        printer.printReport(salesRepsRepository.reportLeadsBySalesRep());
                        break;
                    case OPPORTUNITY:
                        printer.printReport(salesRepsRepository.reportOpportunitiesBySalesRep());
                        break;
                    case CLOSED_WON:
                        printer.printReport(salesRepsRepository.reportClosedWonOpportunitiesBySalesRep());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(salesRepsRepository.reportClosedLostOpportunitiesBySalesRep());
                        break;
                    case OPEN:
                        printer.printReport(salesRepsRepository.reportOpenOpportunitiesBySalesRep());
                        break;
                }
                break;
            case PRODUCT:
                switch (reportTarget) {
                    case OPPORTUNITY:
                        printer.printReport(opportunityRepository.reportOpportunityByProduct());
                        break;
                    case CLOSED_WON:
                        printer.printReport(opportunityRepository.reportClosedWonOpportunityByProduct());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(opportunityRepository.reportClosedLostOpportunityByProduct());
                        break;
                    case OPEN:
                        printer.printReport(opportunityRepository.reportOpenOpportunityByProduct());
                        break;
                }
                break;
            case COUNTRY:
                switch (reportTarget) {
                    case OPPORTUNITY:
                        printer.printReport(opportunityRepository.reportOpportunitiesByCountry());
                        break;
                    case CLOSED_WON:
                        printer.printReport(opportunityRepository.reportClosedWonOpportunitiesByCountry());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(opportunityRepository.reportClosedLostOpportunitiesByCountry());
                        break;
                    case OPEN:
                        printer.printReport(opportunityRepository.reportOpenOpportunitiesByCountry());
                        break;
                }
                break;
            case CITY:
                switch (reportTarget) {
                    case OPPORTUNITY:
                        printer.printReport(opportunityRepository.reportOpportunitiesByCity());
                        break;
                    case CLOSED_WON:
                        printer.printReport(opportunityRepository.reportClosedWonOpportunitiesByCity());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(opportunityRepository.reportClosedLostOpportunitiesByCity());
                        break;
                    case OPEN:
                        printer.printReport(opportunityRepository.reportOpenOpportunitiesByCity());
                        break;
                }
                break;
            case INDUSTRY:
                switch (reportTarget) {
                    case OPPORTUNITY:
                        printer.printReport(opportunityRepository.reportOpportunitiesByIndustry());
                        break;
                    case CLOSED_WON:
                        printer.printReport(opportunityRepository.reportClosedWonOpportunitiesByIndustry());
                        break;
                    case CLOSED_LOST:
                        printer.printReport(opportunityRepository.reportClosedLostOpportunitiesByIndustry());
                        break;
                    case OPEN:
                        printer.printReport(opportunityRepository.reportOpenOpportunitiesByIndustry());
                        break;
                }
                break;
        }
        System.out.println("\n");
    }

    // Create a report of the current mean/median/max/min
    public void reportStates(Command command, StateObject stateObject) {

        switch (stateObject) {
            case EMPLOYEECOUNT:
                System.out.println(Style.OCHER + "\n" + command + " " + stateObject + Style.DEFAULT);
                switch (command) {
                    case MEAN:
                        System.out.println(accountRepository.meanEmployeeCount());
                        break;
                    case MEDIAN:
//                        System.out.println(accountRepository.medianEmployeeCount());
                        break;
                    case MAX:
                        System.out.println(accountRepository.maxEmployeeCount());
                        break;
                    case MIN:
                        System.out.println(accountRepository.minEmployeeCount());
                        break;
                }
                break;
            case QUANTITY:
                System.out.println(Style.OCHER + "\n" + command + " " + stateObject + " of products order" + Style.DEFAULT);
                switch (command) {
                    case MEAN:
                        System.out.println(opportunityRepository.meanQuantity());
                        break;
                    case MEDIAN:
//                        System.out.println(opportunityRepository.medianQuantity());
                        break;
                    case MAX:
                        System.out.println(opportunityRepository.maxQuantity());
                        break;
                    case MIN:
                        System.out.println(opportunityRepository.minQuantity());
                        break;

                }
                break;
            case OPPORTUNITY:
                System.out.println(Style.OCHER + "\n" + command + " number of " + stateObject.getPluralForm()
                        + " associated with an ACCOUNT" + Style.DEFAULT);
                switch (command) {
                    case MEAN:
                        System.out.println(opportunityRepository.meanOppsPerAccount());
                        break;
                    case MEDIAN:
//                        System.out.println(opportunityRepository.medianOppsPerAccount());
                        break;
                    case MAX:
                        System.out.println(opportunityRepository.maxOppsPerAccount());
                        break;
                    case MIN:
                        System.out.println(opportunityRepository.minOppsPerAccount());
                        break;
                }
                break;
        }
        System.out.println("\n");
    }
}