package com.ironhack.homework_3.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void getExpectedInput() {
        assertEquals("NEW",Command.NEW.getExpectedInput());
        assertEquals("CLOSE-LOST",Command.CLOSE_LOST.getExpectedInput());
        assertEquals("CLOSE-WON",Command.CLOSE_WON.getExpectedInput());
        assertFalse(Command.NEW.getExpectedInput() == "TEXT");
        assertTrue(Command.LOOKUP.getExpectedInput() == "LOOKUP");
    }

}