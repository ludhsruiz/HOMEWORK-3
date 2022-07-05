package com.ironhack.homework_3.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void isPhoneNumberValid() {
        assertTrue(Validator.isPhoneNumberValid("123-456-789"));
        assertTrue(Validator.isPhoneNumberValid("666999555"));
        assertTrue(Validator.isPhoneNumberValid("(+598)666999555"));

        assertThrows(IllegalArgumentException.class, () -> Validator.isPhoneNumberValid("46464860378912333"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isPhoneNumberValid("abc"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isPhoneNumberValid(""));
    }

    @Test
    void isEmailValid() {
        assertTrue(Validator.isEmailValid("abcdhhd@defhd.com"));
        assertTrue(Validator.isEmailValid("shdog@kj.de"));
        assertTrue(Validator.isEmailValid("koziolek@matolek.pl"));

        assertThrows(IllegalArgumentException.class, () -> Validator.isEmailValid("kjhajhfgi.iaugh"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isEmailValid("hdhdhdh@uhgab"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isEmailValid("abcfgdhah"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isEmailValid("abcfg@dhah.sgs"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isEmailValid(""));
    }

    @Test
    void isStringValid() {
        assertTrue(Validator.isStringValid("abcdhhddefhdcom"));
        assertTrue(Validator.isStringValid("shdogködá"));
        assertTrue(Validator.isStringValid("qwerñyuiopasdfghjklzxcvbnmqwertyui"));
        assertTrue(Validator.isStringValid("aa"));

        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid("kjhajhfgi 2iaugh"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid("hdhdhdh@uhgab"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid(""));
        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid("a"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid("."));
        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid("!@#$%^&*()"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid(" "));
    }

    @Test
    void isAnswerYesOrNoValid() {
        assertTrue(Validator.isAnswerYesOrNoValid("YES"));
        assertTrue(Validator.isAnswerYesOrNoValid("NO"));

        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid("hello world!"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid("12+5"));
        assertThrows(IllegalArgumentException.class, () -> Validator.isStringValid(""));
    }

}