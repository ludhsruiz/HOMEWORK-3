package com.ironhack.homework_3.enums;

import lombok.Getter;

@Getter
public enum StateObject {
    EMPLOYEECOUNT("EMPLOYEECOUNT", "EMPLOYEECOUNTS"),
    QUANTITY("QUANTITY", "QUANTITIES"),
    OPPORTUNITY( "OPPORTUNITY", "OPPORTUNITIES");

    private String singularForm;
    private String pluralForm;

    StateObject(String singularForm, String pluralForm) {
        this.singularForm = singularForm;
        this.pluralForm = pluralForm;
    }
}
