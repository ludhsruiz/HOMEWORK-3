package com.ironhack.homework_3.model;


import com.ironhack.homework_3.enums.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "opportunities_table")
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private Product product;

    private int quantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "decision_maker")
    private Contact decisionMaker;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "opportunity_status") //"status" is an SQL keyword
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_rep")
    private SalesRep salesRep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account")
    private Account account;

    //Constructor


    public Opportunity() {
    }

    public Opportunity(int id, Product product, int quantity, Contact decisionMaker, Status status, SalesRep salesRep, Account account) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.salesRep = salesRep;
        this.account = account;
    }

    public Opportunity(Product product, int quantity, Contact decisionMaker, SalesRep salesRep, Account account) {
        setProduct(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        setStatus(Status.OPEN);
        setSalesRep(salesRep);
        setAccount(account);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return quantity == that.quantity
                && product == that.product
                && Objects.equals(decisionMaker, that.decisionMaker)
                && status == that.status;
    }

    @Override
    public String toString() {
        return "=== Opportunity " + getId() + " ===" + '\n' +
                "· product : " + product + '\n' +
                "· quantity : " + quantity + '\n' +
                "· decision maker : " + decisionMaker.getName() + " - " + decisionMaker.getCompanyName() + '\n' +
                "· associate sales rep : " + salesRep.getName() + '\n' +
                "· status : " + status + '\n';
    }


}
