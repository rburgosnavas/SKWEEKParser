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
		String exp = "(2 * (5 + 6)) / 5";
		int t = 3;
		int x = 10;
		int y = 5;
		int z = 25;
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
        // System.out.println("\n\n**** duration: " + (end-start) + " ****\n");
		System.out.println("\n\n**** Lexer, Evaluator, and Interpreter tests ****\n");
		
		/*
		 * Test the Lexer
		 */
        Lexer lexer = new LexerBuilder(exp).setT(t).setX(x).setY(y).
                setZ(z).make();
		List<Symbol> symbols = lexer.tokenize();

        for (Symbol symbol : symbols)
        {
            System.out.println(symbol);
        }

        System.out.println();
        
        /*
         * Test the Evaluator
         */
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
        result = Interpreter.calculate(postfix);
        System.out.println("\n\nInterpreter.calculate(postfix): " + result);
	}
}
