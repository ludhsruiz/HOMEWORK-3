package com.ironhack.homework_3.enums;

import lombok.Getter;

@Getter
public enum ReportBy {
    SALESREP("SALESREP","SALEREPS"),
    PRODUCT("PRODUCT","PRODUCTS"),
    COUNTRY("COUNTRY","COUNTRIES"),
    CITY("CITY","CITIES"),
    INDUSTRY("INDUSTRY","INDUSTRIES");

    private String singularForm;
    private String pluralForm;

    ReportBy(String singularForm, String pluralForm){
        this.singularForm = singularForm;
        this.pluralForm= pluralForm;
    }
}
