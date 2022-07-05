package com.ironhack.homework_3.menu;

import com.ironhack.homework_3.enums.Command;
import com.ironhack.homework_3.enums.ObjectType;
import com.ironhack.homework_3.enums.ReportBy;
import com.ironhack.homework_3.enums.StateObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    static Input input;

    @BeforeEach
    void setUp() {
        input = new Input(new Printer());
    }

    @Test
    void getCommandFromString() {
        assertEquals(Command.SHOW, input.getCommandFromString("SHOW"));
        assertEquals(Command.SHOW, input.getCommandFromString("show"));
        assertNull(input.getCommandFromString("abc"));
    }

    @Test
    void getObjectType_validType() {
        assertEquals(ObjectType.ACCOUNT, input.getObjectType("ACCOUNT"));
        assertEquals(ObjectType.ACCOUNT, input.getObjectType("account"));
        assertNull(input.getObjectType("acount"));

        assertEquals(ObjectType.OPPORTUNITY, input.getObjectType("Opportunities"));
        assertEquals(ObjectType.OPPORTUNITY, input.getObjectType("oPPORTunity"));
        assertNull(input.getObjectType("ObjectType.OPPORTUNITY"));
    }

    @Test
    void getReportBy_validType() {
        assertEquals(ReportBy.SALESREP, input.getReportBy("SaLESREPS"));
        assertEquals(ReportBy.SALESREP, input.getReportBy("salesrep"));
        assertNull(input.getReportBy("sales rep"));

        assertEquals(ReportBy.CITY, input.getReportBy("CITy"));
        assertEquals(ReportBy.CITY, input.getReportBy("Cities"));
        assertNull(input.getReportBy("ReportBy"));
    }

    @Test
    void getStateObject_validType() {
        assertEquals(StateObject.EMPLOYEECOUNT, input.getStateObject("employeeCount"));
        assertEquals(StateObject.EMPLOYEECOUNT, input.getStateObject("EMPLOYEEcounts"));
        assertNull(input.getReportBy("employee account"));

        assertEquals(StateObject.QUANTITY, input.getStateObject("Quantity"));
        assertEquals(StateObject.QUANTITY, input.getStateObject("quantities"));
        assertNull(input.getReportBy("stateObject"));
    }

}