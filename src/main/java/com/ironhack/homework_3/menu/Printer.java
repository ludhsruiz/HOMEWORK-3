package com.ironhack.homework_3.menu;

import com.ironhack.homework_3.style.Style;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Component
public class Printer {

        public void print(String text) {
            System.out.println(text);
        }



        public void printTypoInfo(String wrongWord) {
            System.out.println("Incorrect command. Possible typo in the word \"" + wrongWord + "\". Please try again.");
        }


    public void pleaseWait() {

        try {

            Thread.sleep(600);
            System.out.print(Style.LIGHT_GRAY + "Please, wait.");
            Thread.sleep(800);

            int i = 0;
            while (i < 2){
                System.out.print(".");
                Thread.sleep(800);
                i++;
            }

            System.out.println(Style.DEFAULT + "");
            Thread.sleep(600);

        } catch (InterruptedException e) {
        }


    }



        public void welcomeMessage() {
            print("\n\n============================================================");
            print("\n\n========== WELCOME TO OUR AWESOME CRM!=========================");
            print("\n\nTo start using our CRM, or if you don't know what you are doing here:");
            print("\n\nType \"HELP\" for instructions.__ . Next type \"POPULATE\" to fill the database");

        }

        public void helpPage(){
            System.out.println("=================================================");
            System.out.println("            CRM Help page");
            System.out.println("=================================================");
            System.out.println("\n              Possible Commands:\n " );
            System.out.println(Style.LIGHT_PURPLE + " NEW <Object type>                            " + Style.LIGHT_GRAY + "Creates a new Object");
            System.out.println(Style.LIGHT_PURPLE + " SHOW <Object type>                           " + Style.LIGHT_GRAY + "Lists all Objects of the selected type");
            System.out.println(Style.LIGHT_PURPLE + " LOOKUP <Object type> <Id Number>             " + Style.LIGHT_GRAY + "Display the selected Object type with the indicated Id Number");
            System.out.println(Style.LIGHT_PURPLE + " CONVERT <LEAD Id number>                     " + Style.LIGHT_GRAY + "Converts the selected LEAD in CONTACT, OPPORTUNITY and ACCOUNT");
            System.out.println(Style.LIGHT_PURPLE + " REPORT <ReportTarget> BY <ReportByObject>    " + Style.LIGHT_GRAY + "Provides the requested report information");
            System.out.println(Style.LIGHT_PURPLE + " CLOSE-WON <OPPORTUNITY Id Number>            " + Style.LIGHT_GRAY + "Changes the selected OPPORTUNITY status to CLOSE-WON");
            System.out.println(Style.LIGHT_PURPLE + " CLOSE-LOST <OPPORTUNITY Id Number>           " + Style.LIGHT_GRAY + "Changes the selected OPPORTUNITY status to CLOSE-LOST");
            System.out.println(Style.LIGHT_PURPLE + " OPEN <OPPORTUNITY Id Number>                 " + Style.LIGHT_GRAY + "Changes the selected OPPORTUNITY status to OPEN");
            System.out.println(Style.LIGHT_PURPLE + " MEAN/MAX/MIN EMPLOYEECOUNT            " + Style.LIGHT_GRAY + "Displays the stats related to the number of employees of all the registered companies");
            System.out.println(Style.LIGHT_PURPLE + " MEAN/MAX/MIN QUANTITY                 " + Style.LIGHT_GRAY + "Displays the stats related to the quantity of products ordered");
            System.out.println(Style.LIGHT_PURPLE + " MEAN/MAX/MIN OPPORTUNITY              " + Style.LIGHT_GRAY + "Displays the stats related to the number of Opportunities associated with an Account");
            System.out.println(Style.LIGHT_PURPLE + " POPULATE                                     " + Style.LIGHT_GRAY + "Populate the database with some sample data");
            System.out.println(Style.LIGHT_PURPLE + " HELP                                         " + Style.LIGHT_GRAY + "Displays this help info");
            System.out.println(Style.LIGHT_PURPLE + " EXIT                                         " + Style.LIGHT_GRAY + "Terminates the cleanCRM program" + Style.DEFAULT);

            System.out.println("\n Object Types Available ->" + Style.LIGHT_PURPLE + "                    LEAD / CONTACT / OPPORTUNITY / ACCOUNT / SALESREP");
            System.out.println(Style.DEFAULT + " Report Targets Available ->" + Style.LIGHT_PURPLE + "                  LEAD /  OPPORTUNITY / CLOSED-WON / CLOSED-LOST / OPEN");
            System.out.println(Style.DEFAULT + " Report By Objects Available ->" + Style.LIGHT_PURPLE + "               SALESREP /  PRODUCT / COUNTRY / CITY / INDUSTRY\n");
            System.out.println(Style.LIGHT_GRAY + "*All Object types accepted in plural (e.g. OPPORTUNITY and OPPORTUNITIES are both accepted forms)");
            System.out.println("*All commands are case-insensitive\n" + Style.DEFAULT);
        }

    public void printReport(String[][] requestResult) {
        for (String[] a : requestResult) {
            System.out.println(Arrays.toString(a));
        }
    }



}
