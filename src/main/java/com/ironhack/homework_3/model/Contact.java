package com.ironhack.homework_3.model;


import javax.persistence.*;

@Entity
@Table(name = "contacts_table")
public class Contact extends Item {

    @OneToOne(mappedBy = "decisionMaker", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Opportunity opportunity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account")
    private Account account;

    public Contact(int id, String name, String phoneNumber, String email, String companyName, Opportunity opportunity, Account account) {
        super(id, name, phoneNumber, email, companyName);
        this.opportunity = opportunity;
        this.account = account;
    }


    // Constructor
    public Contact(int id, String name, String phoneNumber, String email, String companyName, Opportunity opportunity) {
        super(id, name, phoneNumber, email, companyName);
        this.opportunity = opportunity;
    }

    public Contact() {
    }

    // Constructor called when a LEAD is converted
    public Contact(Lead lead, Account account) {
        setName(lead.getName());
        setPhoneNumber(lead.getPhoneNumber());
        setEmail(lead.getEmail());
        setCompanyName(lead.getCompanyName());
        setAccount(account);
    }

    // New Contact constructor including an associated Account
    public Contact(String name, String phoneNumber, String email, String companyName, Account account) {
        super(name, email, companyName, phoneNumber);
        setAccount(account);
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "=== Contact " + getId() + " ===" + '\n' +
                "· name : " + getName() + '\n' +
                "· phone number : " + getPhoneNumber() + '\n' +
                "· email : " + getEmail() + '\n' +
                "· company name : " + getCompanyName() + '\n' +
                ". account : " +  getAccount();
    }



}

