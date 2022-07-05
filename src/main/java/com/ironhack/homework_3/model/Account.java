package com.ironhack.homework_3.model;

import com.ironhack.homework_3.enums.Industry;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "accounts_table")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private Industry industry;

    @Column(name = "employees")
    private int employeeCount;

    private String city;

    private String country;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @Column(name = "opportunity_list")
    private Set<Opportunity> opportunityList;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @Column(name = "contact_list")
    private Set<Contact> contactList;

    // Constructor


    public Account(int id, Industry industry, int employeeCount, String city, String country, Set<Opportunity> opportunityList, Set<Contact> contactList) {
        this.id = id;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.opportunityList = opportunityList;
        this.contactList = contactList;
    }

    public Account() {

    }

    public Account(Industry industry, int employeeCount, String city, String country) {
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
    }
    // Methods

    public void addContactToList(Contact contact) {
        contactList.add(contact);
    }

    public void addOpportunityToList(Opportunity opportunity) {
        opportunityList.add(opportunity);
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(Set<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    public Set<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Set<Contact> contactList) {
        this.contactList = contactList;
    }


    //to string

    @Override
    public String toString() {
        return "=== Account " + getId() + " ===" + '\n' +
                "· industry : " + industry + '\n' +
                "· number of employees : " + employeeCount + '\n' +
                "· city : " + city + '\n' +
                "· country : " + country + '\n';
    }
}

