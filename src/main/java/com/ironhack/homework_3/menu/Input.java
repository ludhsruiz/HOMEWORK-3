package com.ironhack.homework_3.menu;

import com.ironhack.homework_3.enums.*;
import com.ironhack.homework_3.style.Style;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;


@Component
public class Input {

    private Scanner scanner = new Scanner(System.in);
    private Printer printer;

    public Input(Printer printer) {
        this.printer = printer;
    }

    public String getString() {
        System.out.print(">");
        return scanner.nextLine();
    }

    public int getIntegerHigherThanZero() {
        int intValue = -1;
        do {
            System.out.println(Style.LIGHT_GRAY + "Please input Integer higher than 0" + Style.DEFAULT);
            String input = scanner.next();
            try {
                intValue = Integer.parseInt(input);
            } catch (NumberFormatException exp) {
                System.out.println("Must be Integer higher than 0");
            }
        } while (intValue <= 0);

        scanner.nextLine();
        return intValue;
    }

    // Method to call when a yes or no question is encountered
    public String getYesOrNo(){
        String answer = "";
        boolean error = false;

        do {
            String input = scanner.next();

            try {
                answer = input.toUpperCase();
                error = Validator.isAnswerYesOrNoValid(answer);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid response.");
            }

        } while (!error);

        scanner.nextLine();
        return answer;
    }

    // Scanner closure, useful for exiting the application
    public void close() {
        scanner.close();
    }

    // Method to convert case insensitive string to Command Enum
    public Command getCommandFromString(String string) {
        string = string.toUpperCase();
        for (Command command : Command.values()) {
            if (string.equals(command.getExpectedInput())) {
                return command;
            }
        }
        return null;
    }

    // Method to convert case insensitive string (accepted in singular or plural form) to ObjectTypes Enum
    public ObjectType getObjectType(String string) {
        string = string.toUpperCase();
        for (ObjectType objectType : ObjectType.values()) {
            if (string.equals(objectType.getSingularForm())) {
                return objectType;
            } else if (string.equals(objectType.getPluralForm())) {
                return objectType;
            }
        }
        return null;
    }

    // Method to convert case insensitive string (accepted in singular or plural form) to ReportBy Enum
    public ReportBy getReportBy(String string) {
        string = string.toUpperCase();
        for (ReportBy reportBy : ReportBy.values()) {
            if (string.equals(reportBy.getSingularForm())) {
                return reportBy;
            } else if (string.equals(reportBy.getPluralForm())) {
                return reportBy;
            }
        }
        return null;
    }

    // Method to convert case insensitive string (accepted in singular or plural form) to StateObject Enum
    public StateObject getStateObject(String string) {
        string = string.toUpperCase();
        for (StateObject stateObject : StateObject.values()) {
            if (string.equals(stateObject.getSingularForm())) {
                return stateObject;
            } else if(string.equals(stateObject.getPluralForm())) {
                return stateObject;
            }
        }
        return null;
    }

    // Method to convert case insensitive string (accepted in singular or plural form) to ReportTarget Enum
    public ReportTarget getReportTarget(String string) {
        string = string.toUpperCase();
        for (ReportTarget reportTarget : ReportTarget.values()) {
            if (string.equals(reportTarget.getSingularForm())) {
                return reportTarget;
            } else if (string.equals(reportTarget.getPluralForm())) {
                return reportTarget;
            }
        }
        return null;
    }

    public Industry chooseIndustry() {
        System.out.println(
                "1 - Produce\n" +
                        "2 - E-Commerce\n" +
                        "3 - Manufacturing\n" +
                        "4 - Medical\n" +
                        "5 - Other");
        int input;
        do {
            input = getIntegerHigherThanZero();
        } while (input > 5);
        switch (input) {
            case 1:
                return Industry.PRODUCE;
            case 2:
                return Industry.ECOMMERCE;
            case 3:
                return Industry.MANUFACTURING;
            case 4:
                return Industry.MEDICAL;
            default:
                return Industry.OTHER;
        }
    }

    public Product chooseProduct() {
        System.out.println("\nType of Product:\n" +
                "1 - HYBRID\n" +
                "2 - FLATBED\n" +
                "3 - BOX");
        int input;
        do {
            input = getIntegerHigherThanZero();
        } while (input > 3);
        switch (input) {
            case 1:
                return Product.HYBRID;
            case 2:
                return Product.FLATBED;
            default:
                return Product.BOX;
        }
    }
}

