package com.company;

public class Main {
    public static void main(String[] args) {
        String regex = "aa&"; int stateNum = 0;
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
