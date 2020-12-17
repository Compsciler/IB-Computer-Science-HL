import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(calculate("7 11 5 13 3 17 2 + - * / ^ %"));
    }
    public static double calculate(String calculationString){
        String[] stringTokens = calculationString.split(" ");
        Stack<Object> tokens = new Stack<>();
        for (String s: stringTokens){
            try {
                tokens.push(Double.parseDouble(s));
            } catch (NumberFormatException e){
                Double operand2 = (Double)tokens.pop();
                Double operand1 = (Double)tokens.pop();
                switch (s){
                    case "+":
                        tokens.push(operand1 + operand2);
                        break;
                    case "-":
                        tokens.push(operand1 - operand2);
                        break;
                    case "*":
                        tokens.push(operand1 * operand2);
                        break;
                    case "/":
                        tokens.push(operand1 / operand2);
                        break;
                    case "^":
                        tokens.push(Math.pow(operand1, operand2));
                        break;
                    case "%":
                        tokens.push(operand1 % operand2);
                }
            }
        }
        return (Double)tokens.peek();
    }
}
