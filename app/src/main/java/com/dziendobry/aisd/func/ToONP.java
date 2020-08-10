package com.dziendobry.aisd.func;

import java.util.Stack;

public class ToONP {

    private static int plus;
    private static int minus;
    private static int mult;
    private static int div;

    public ToONP(int plus, int minus, int div, int mult){
        ToONP.plus = plus;
        ToONP.minus = minus;
        ToONP.mult = mult;
        ToONP.div = div;
    }
    static int Prec(char ch)
    {
        switch (ch)
        {
            case '+':
                return plus;
            case '-':
                return minus;
            case '/':
                return div;
            case '*':
                return mult;
        }
        return -1;
    }

    static public String infixToPostfix(String exp)
    {
        String result = new String("");

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c))
                result += c;

            else if (c == '(')
                stack.push(c);

            else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();

                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression"; // invalid expression
                else
                    stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){
                    if(stack.peek() == '('||Prec(c)==-1)
                        return "Invalid Expression";
                    result += stack.pop();
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid Expression";
            result += stack.pop();
        }
        return result;
    }
}
