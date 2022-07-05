package com.ironhack.homework_3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sales_rep_table")
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sales_rep_name")
    private String name;

    //SalesRep should have OneToMany relations with Leads and Opportunities
    @OneToMany(mappedBy = "salesRep")
    private Set<Lead> salesRepLeads;

    @OneToMany(mappedBy = "salesRep")
    private Set<Opportunity> salesRepOpportunities;


    // CONSTRUCTORS

    public SalesRep(int id, String name, Set<Lead> salesRepLeads, Set<Opportunity> salesRepOpportunities) {
        this.id = id;
        this.name = name;
        this.salesRepLeads = salesRepLeads;
        this.salesRepOpportunities = salesRepOpportunities;
    }


    public SalesRep() {
    }

    public SalesRep(String name) {
        this.name = name;
    }


    // GETTERS N SETTERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Lead> getSalesRepLeads() {
        return salesRepLeads;
    }

    public void setSalesRepLeads(Set<Lead> salesRepLeads) {
        this.salesRepLeads = salesRepLeads;
    }

    public Set<Opportunity> getSalesRepOpportunities() {
        return salesRepOpportunities;
    }

    public void setSalesRepOpportunities(Set<Opportunity> salesRepOpportunities) {
        this.salesRepOpportunities = salesRepOpportunities;
    }

    @Override
    public String toString() {
        return "=== SalesRep " + getId() + " ===" + '\n' +
                "· name : " + name + '\n';
    }
}
