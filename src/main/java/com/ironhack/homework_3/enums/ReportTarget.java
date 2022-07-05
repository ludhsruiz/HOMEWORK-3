package com.ironhack.homework_3.enums;

import lombok.Getter;

@Getter
public enum ReportTarget {
    LEAD ("LEAD","LEADS"),
    OPPORTUNITY("OPPORTUNITY", "OPPORTINIES"),
    CLOSED_WON("CLOSE-WON", "CLOSE_WON"),
    CLOSED_LOST("CLOSED-LOST", "CLOSED-LOST"),
    OPEN("OPEN", "OPEN");

    private String singularForm;
    private String pluralForm;

    ReportTarget(String singularForm, String pluralForm){
        this.singularForm = singularForm;
        this.pluralForm= pluralForm;
    }

}
