package com.ironhack.homework_3.model;


import javax.persistence.*;

@Entity
@Table(name = "leads_table")
public class Lead extends Item{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_rep")
    private SalesRep salesRep;

    // Constructor


    public Lead(int id, String name, String phoneNumber, String email, String companyName, SalesRep salesRep) {
        super(id, name, phoneNumber, email, companyName);
        this.salesRep = salesRep;
    }

    public Lead() {

    }

    public Lead(String name, String phoneNumber, String email, String companyName, SalesRep salesRep) {
        super(name, email, companyName, phoneNumber);
        setSalesRep(salesRep);
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }

    @Override
    public String toString() {
        return "=== Lead " + getId() + " ===" + '\n' +
                "· name : " + getName() + '\n' +
                "· phone number : " + getPhoneNumber() + '\n' +
                "· email : " + getEmail() + '\n' +
                "· company name : " + getCompanyName() + '\n' +
                "· associate sales rep : " + salesRep.getName() + '\n';
    }



}
