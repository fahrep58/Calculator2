package com.example.fahrep58.calculator2;

/**
 * Created by fahrep58 on 3/2/2015.
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfixEvaluator {





        private Stack stack;
        private HashMap<String, Double> hashmap;
        private String expression, output;


        /**
         * Constructor.
         *
         * Initializes the variables.
         */
        public InfixEvaluator() {
            // TODO Auto-generated constructor stub
            hashmap = new HashMap();
            stack = new Stack();
            expression = "";
            output = "";

        }

        /**
         * getExpression returns the datamember expression.
         *
         * @return returns the expression as a string.
         */
        public String getExpression()
        {
            return expression;
        }

        /**
         * setExpression sets the datamember expression.
         *
         * @param in String that you want to set the expression as.
         */
        public void setExpression(String in)
        {
            this.expression = in;
        }

        /**
         * Accepts a string and prints it out to the command window.
         *
         * @param in : String that you wish to print
         */
        public void printOut(String in)
        {
            System.out.println(in);
        }

        /**
         * isInfix determines if the expression is an infix statement or a
         * variable assignment
         *
         * @param in String that you wish to evaluate
         * @return returns true for it being an infix and needing to be evaluated
         *           false if it is a variable assignment
         */
        public boolean isInfix(String in)
        {
            if (in.indexOf("=") == -1)
            {
                // System.out.println("is an infix");
                return true;
            }
            else
            {
                // System.out.println("is not an infix");
                return false;
            }
        }

        /**
         * removeSpaces accepts a string and has spaces removed from it
         *
         * @param in String that you wish to have spaces removed from
         * @return removes the string without any spaces
         */
        public String removeSpaces(String in)
        {
            return in.replaceAll("\\s+","");
        }

        /**
         * setVariable adds the key and value to the hashmap, the
         * value will be converted into a double
         *
         * @param key String would be the variable name
         * @param value double would be the value of the variable, which
         * will be converted to a double
         */
        public void setVariable(String key, double value)
        {
            hashmap.put(key, value);
        }

        /**
         * getVariable returns the double that was stored in the variable.
         *
         * @param key String would be the variable you want to get
         * @param value double would be the value of the variable, which
         * will be converted to a double. Returns null if the var is not valid
         */
        public double getVariable(String key)
        {
            return hashmap.get(key);
        }

        /**
         * evaluateVariable preps the string to be split into key and value,
         * then sets the key and value
         *
         * @param in String variable string you wish to split
         */
        public void evaluateVariable(String in)
        {
            in = removeSpaces(in);

            int equalpos = in.indexOf("=");
            setVariable(in.substring(0, equalpos), Double.parseDouble(in.substring(equalpos + 1, in.length())));
        }

        /**
         * convertToPostfix converts a infix expression to postfix using an
         * algorithm.
         *
         * @param in String variable string you wish to split
         */
        public void convertToPostfix(String in)
        {
            String abc = "[_A-Za-hj-z]";
            String num = "(\\d+(\\.\\d+)?)";
            int priority = 0;

            //patterns to test against

            Pattern abcPat = Pattern.compile(abc);
            Pattern numPat = Pattern.compile(num);
            Matcher abcMatch, numMatch;
            String test = "";

            in = removeSpaces(in);

            // creates stringtokenizer and keeps the delimiters as tokens

            String delim = "()+-/*i%";
            StringTokenizer st = new StringTokenizer(in, delim ,true);



            while (st.hasMoreTokens())
            {

                test = st.nextToken();

                abcMatch = abcPat.matcher(test);
                numMatch = numPat.matcher(test);

                //numbers get pushed to the output
                if (numMatch.matches())
                {
                    if (output == (""))
                    {
                        output = test;
                    }else{

                        output = output + " " + test;
                    }

                }

                // switches variable for its value
                if (abcMatch.matches())
                {
                    if (output == ("")){
                        output = "" + getVariable(test);
                    }else{
                        output = output + " " + getVariable(test);}
                    //   System.out.println("ABC Match: " + output);
                }

                if (test.equals("("))
                {
                    stack.push(test);
                    //  System.out.println("( Push " + test);
                }

                if (test.equals(")"))
                {
                    // do a try to catch a peek at an empty stack
//                    try{
                        while (!(stack.peek().equals("(")))
                        {
                            if (output == (""))
                            {
                                output = "" + stack.pop();
                            }else{

                                output = output + " " + stack.pop();
                            }
                            //     System.out.println(") Match print all up to (: " + output);
                        }
//                    } catch (Exception ex){}
                    stack.pop();
                }

                // priority is the way to check for an operator of lower value

                if (test.equals("+") || test.equals("/") || test.equals("-") || test.equals("*") || test.equals("i") || test.equals("%"))
                {
                    if (test.equals("+") || test.equals("-"))
                        priority = 1;
                    else
                        priority = 2;

                    if (priority == 1)
                    {
//                        try{
                            while (!(stack.empty()) && !(stack.peek().equals("(")) )
                            {
                                if (output == (""))
                                {
                                    output = "" + stack.pop();
                                }else{

                                    output = output + " " + stack.pop();
                                }
                                // System.out.println("Num Match prior 1: " + output);
                            }
//                        }
//                        catch (EmptyStackException ex)
//                        {
//
//                        }
                    }

                    if (priority == 2)
                    {
//                        try
//                        {
                            while (!(stack.empty()) && !(stack.peek().equals("+")) && !(stack.peek().equals("-")) && !(stack.peek().equals("(")) )
                            {
                                if (output == (""))
                                {
                                    output = "" + stack.pop();
                                }else{

                                    output = output + " " + stack.pop();
                                }
                                //   System.out.println("Num Match prior 2: " + output);
                            }
//                        }
//                        catch (Exception ex){
//                        }
                    }


                    stack.push(test);


                }


            }
//            try
//            {
                while (!(stack.empty()))
                {
                    if (output == (""))
                    {
                        output = "" + stack.pop();
                    }else{

                        output = output + " " + stack.pop();
                    }
                    // System.out.println("final output " + output);
                }
//            } catch (Exception ex){}

            // sets expression, so it is available as a datamember

            expression = output;
            output = "";
            // printOut("end of convertToPostfix " + expression);
        }

        /**
         * evaluatePostfix evaluates a postfix expression using an
         * algorithm.
         *
         * @param in String variable string you wish to split
         * @return double, the result of the evaluation
         */
        public String evaluatePostfix(String in)
        {

            // the string passed is delimited by spaces, creates a tokenizer
            String thewholething ="";
            String delim = " ";
            StringTokenizer st = new StringTokenizer(in, delim ,false);

            String test;
            double op1, op2;
            Matcher numMatch;
            String num = "(\\d+(\\.\\d+)?)";
            Pattern numPat = Pattern.compile(num);


            while (st.hasMoreTokens())
            {
                //removes any spaces that may be within the token

                test = removeSpaces(st.nextToken());
                numMatch = numPat.matcher(test);

                //pushes number

                if (numMatch.matches())
                {
                    stack.push(Double.parseDouble(test));
                }

                // does all the math depending on operator

                // division
                if (test.equals("/"))
                {
                    op2 = (double)stack.pop();
                    op1 = (double)stack.pop();

                    stack.push(op1 / op2);

                    double result = op1 / op2;
                    thewholething = thewholething + "\n" + op1 + " / " + op2 + "\n                       " + result + "\n";

                }

                if (test.equals("%"))
                {
                    op2 = (double)stack.pop();
                    op1 = (double)stack.pop();

                    stack.push(op1 % op2);

                    double result = op1 % op2;
                    thewholething = thewholething + "\n" + op1 + " % " + op2 + "\n                       " + result + "\n";

                }

                if (test.equals("+"))
                {
                    op2 = (double)stack.pop();
                    op1 = (double)stack.pop();

                    stack.push(op1 + op2);

                    double result = op1 + op2;
                    thewholething = thewholething + "\n" + op1 + " + " + op2 + "\n                       " + result + "\n";

                }
                //add
                if (test.equals("i"))
                {

                    Double d = new Double((double)stack.pop());
                    Double d2 = new Double((double)stack.pop());

                    int intop2 = d.intValue();
                    int intop1 = d2.intValue();

                    stack.push((double)(intop1 / intop2));

                    double result = (double)(intop1 / intop2);
                    thewholething = thewholething + "\n" + intop1 + " i " + intop2 + "\n                       " + result + "\n";

                }
                // multipy
                if (test.equals("*"))
                {

                    op2 = (double)stack.pop();
                    op1 = (double)stack.pop();

                    stack.push(op1 * op2);

                    double result = op1 * op2;
                    thewholething = thewholething + "\n" + op1 + " * " + op2 + "\n                       " + result + "\n";

                }

                //subtraction
                if (test.equals("-"))
                {
                    op2 = (double)stack.pop();
                    op1 = (double)stack.pop();

                    stack.push(op1 - op2);

                    double result = op1 - op2;
                    thewholething = thewholething + "\n" + op1 + " - " + op2 + "\n                       " + result + "\n";

                }
            }

            // pops final result

            double testing = (double)stack.pop();

            return thewholething;
        }

        /** main class
         *
         * provides a driver to run through it
         *
         * @param args
         */
//        public static void main(String[] args)
//        {
//            String current = "";
//            // TODO Auto-generated method stub
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("Enter an infix expression or variable assignment, e.g. A=12.  EXIT to exit");
//            InfixEvaluator infix = new InfixEvaluator();
//            current = scanner.nextLine();
//            while (current != "EXIT")
//            {
//
//
//                if (current.equals("EXIT"))
//                {
//                    System.out.println("System Exiting...");
//                    System.exit(0);
//                }
//
//                if (infix.isInfix(current))
//                {
//
//                    infix.convertToPostfix(current);
//                    System.out.println(">> " + infix.evaluatePostfix(infix.getExpression()));
//                    infix.setExpression("");
//                }
//                else
//                {
//                    infix.evaluateVariable(current);
//                }
//
//
//
//                // System.out.println("Enter an infix expression or variable assignment, e.g. A=12, EXIT to exit ");
//                current = scanner.nextLine();
//            }
//        }

    }





