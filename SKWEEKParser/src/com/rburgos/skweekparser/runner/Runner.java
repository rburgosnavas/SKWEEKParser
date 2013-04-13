package com.rburgos.skweekparser.runner;

import java.util.List;

import com.rburgos.skweekparser.Parser;
import com.rburgos.skweekparser.evaluator.Evaluator;
import com.rburgos.skweekparser.interpreter.Interpreter;
import com.rburgos.skweekparser.lexer.Lexer;
import com.rburgos.skweekparser.lexer.LexerBuilder;
import com.rburgos.skweekparser.symbols.Symbol;

@SuppressWarnings("unused")
public class Runner
{
    public static void main(String[] args)
	{
		String exp = "((2 * (5 + 6)) / 5) * z/t";
		int t = 3;
		int x = 10;
		int y = 5;
		int z = 25;
		
		/*
		 * Test the Parser class
		 * 
		 * The Parser class is a self-contained class which splits and converts
		 * an expression to postfix, and then calculates the result.
		 */
		System.out.println("**** Parser class test ****\n");
		System.out.println("String exp:          " + exp);
		
		long start = System.nanoTime();
		
		Parser parse = new Parser.ParserBuilder(exp).setT(t).setX(x).
		        setY(y).setZ(z).build();
        double result = parse.evaluate();
        
        long end = System.nanoTime();
        
        System.out.println("parse.evaluate():    " + result);
		
		System.out.print("parse.splitToList(): ");		
		for (String s : parse.splitToList())
		{
		    System.out.print(s + " ");
		}
		
		System.out.print("\nparse.toPostfix():   ");		
		for (String s : parse.toPostfix())
		{
		    System.out.print(s + " ");
		}
        // System.out.println("\n\n**** duration: " + (end-start) + " ****");
		
		/*
		 * Test the Lexer
		 */
        System.out.println();
		System.out.println("\n**** Lexer ****\n");
        Lexer lexer = new LexerBuilder(exp).setT(t).setX(x).setY(y).
                setZ(z).make();
		List<Symbol> symbols = lexer.tokenize();

        for (Symbol symbol : symbols)
        {
            System.out.println(symbol);
        }
        
        /*
         * Test the Evaluator
         */
        System.out.println("\n**** Evaluator ****\n");
        // Evaluator eval = new Evaluator(symbols);
        Evaluator eval = lexer.evaluator();
        List<Symbol> postfix = eval.toPostfix();
        
        for (Symbol symbol : postfix)
        {
            System.out.println(symbol);
        }
        
        /*
         * Test the Interpreter
         */
        System.out.println("\n**** Interpreter ****\n");
        result = Interpreter.calculate(postfix);
        System.out.println("Interpreter.calculate(postfix): " + result);
	}
}
