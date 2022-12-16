package calculator;

import java.io.Console;

public class Main {

    public static void main(String[] args) {

        //Initialising calculator
        System.out.println("Welcome.");
        Float finalValue = 0f;
        Float value1 = 0f;
        Float value2 = 0f;
    
        Console cons = System.console();
        Boolean stop = false;

        while (!stop) {

            //Get user input
            String userInput = cons.readLine(">").trim();
            String[] inputArray = userInput.split(" ");

            
            if (inputArray[0].equals("exit")) {
                System.out.println("Bye bye.");
                break;
            }   
            else if (inputArray.length != 3) {
                System.out.println("You have entered an invalid command.");
                continue;
            }
            
            //Assigning action / values to variables
            if (inputArray[0].equals("$last")) {
                value1 = finalValue;
            }
            else {
                try {
                    value1 = Float.parseFloat(inputArray[0]);
                }
                catch(Exception e) {
                    System.out.println("Error calculating.");
                }
            }

            String operator = inputArray[1];

            if (inputArray[2].equals("$last")) {
                value2 = finalValue;
            }
            else {
                try {
                    value2 = Float.parseFloat(inputArray[2]);
                }
                catch(Exception e) {
                    System.out.println("Error calculating.");
                }
            }

            //Execute command based on detected operator
            switch (operator) {
    
                case "+":
                finalValue = value1 + value2;
                System.out.printf("%.2f\n", finalValue);
                break;

                case "-":
                finalValue = value1 - value2;
                System.out.printf("%.2f\n", finalValue);
                break;

                case "/":
                finalValue = value1 / value2;
                System.out.printf("%.2f\n", finalValue);
                break;
                
                case "*":
                finalValue = value1 * value2;
                System.out.printf("%.2f\n", finalValue);
                break;
            
            }
            
        }

    } 

}