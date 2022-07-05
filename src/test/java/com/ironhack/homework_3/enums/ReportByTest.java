package com.ironhack.homework_3.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportByTest {

    @Test
    void getSingularForm() {
        assertEquals("COUNTRY", ReportBy.COUNTRY.getSingularForm());
        assertFalse(ReportBy.INDUSTRY.getSingularForm() == "INDUSTRIES");
        assertTrue(ReportBy.SALESREP.getSingularForm() == "SALESREP");
    }

    @Test
    void getPluralForm() {
        assertEquals("COUNTRIES", ReportBy.COUNTRY.getPluralForm().toString());
        assertFalse(ReportBy.INDUSTRY.getPluralForm().toString() == "INDUSTRY");
        assertTrue( ReportBy.SALESREP.getPluralForm().toString() == "SALESREPS");
    }

}