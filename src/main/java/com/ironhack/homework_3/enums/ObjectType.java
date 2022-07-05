package com.ironhack.homework_3.enums;


import lombok.Getter;

@Getter
public enum ObjectType {
    ACCOUNT("ACCOUNT", "ACCOUNTS"),
    CONTACT("CONTACT", "CONTACTS"),
    LEAD("LEAD", "LEADS"),
    OPPORTUNITY("OPPORTUNITY", "OPPORTUNITIES"),
    SALESREP("SALESREP", "SALESREPS");


    private String singularForm;
    private String pluralForm;

    ObjectType(String singularForm, String pluralForm) {
        this.singularForm = singularForm;
        this.pluralForm = pluralForm;
    }


}