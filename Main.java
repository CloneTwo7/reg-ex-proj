import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	String regex = ""; int stateNum = 0;
	if(args.length < 1) {
	    System.out.println("Please provide a valid file path");
	    return;
	}
	try {
	    File regFile = new File(args[0]);
	    Scanner reader = new Scanner(regFile);
	    regex = reader.nextLine();
	} catch (FileNotFoundException e) {
	    System.out.println("ERROR: "+args[0]+" file not found");
	    e.printStackTrace();
	}
        RegExStack stack = new RegExStack(regex.length());
        for(int i = 0; i < regex.length(); i++) {
            char symbol = regex.charAt(i);
            if(symbol == '&') {
                if(stack.top < 2) {
                    System.out.println("Please provide valid Regular Expression");
                    return;
                }
                NFA b = stack.pop();
                NFA a = stack.pop();
                a.concat(b);
                stack.push(a);
            } else if(symbol == '|'){
                if(stack.top < 2) {
                    System.out.println("Please provide valid Regular Expression");
                    return;
                }
                NFA a = stack.pop();
                NFA b = stack.pop();
                b.union(a, ++stateNum, ++stateNum);
                stack.push(b);
            } else if(symbol == '*') {
                if(stack.top < 1) {
                    System.out.println("Please provide valid Regular Expression");
                    return;
                }
                NFA a = stack.pop();
                a.kleene(++stateNum);
                stack.push(a);
            } else {
                NFA c = new NFA(++stateNum, ++stateNum, symbol);
                stack.push(c);
            }
        }
        if(stack.top != 1) {
            System.out.println("Please provide valid Regular Expression");
            return;
        }
        System.out.println("RE: "+regex);
        stack.print();
    }
}
