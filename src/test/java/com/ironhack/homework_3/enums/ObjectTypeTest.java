package com.ironhack.homework_3.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectTypeTest {
    @Test
    void getSingularForm() {
        assertEquals("ACCOUNT",ObjectType.ACCOUNT.getSingularForm());
        assertFalse(ObjectType.ACCOUNT.getSingularForm() == "ACCOUNTS");
        assertTrue( ObjectType.CONTACT.getSingularForm() == "CONTACT");
    }

    @Test
    void getPluralForm() {
        assertEquals("LEADS",ObjectType.LEAD.getPluralForm());
        assertFalse(ObjectType.ACCOUNT.getPluralForm() == "ACCOUNT");
        assertTrue( ObjectType.CONTACT.getPluralForm() == "CONTACTS");
    }

}