package com.rburgos.skweekparser.runner;

import java.util.ArrayList;
import java.util.List;

import com.rburgos.skweekparser.Parser;
import com.rburgos.skweekparser.symbols.SymbolFactory;
import com.rburgos.skweekparser.symbols.SymbolInterface;

@SuppressWarnings("unused")
public class Runner
{
    public static void main(String[] args)
	{
		String exp = "4/0";
		System.out.println("String exp:          " + exp);
		
		long start = System.nanoTime();
		
		Parser parse = new Parser.ParserBuilder(exp).build();
        double result = parse.evaluateToInt();
        
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
        System.out.println("\n\n**** duration: " + (end-start) + " ****");
		
		System.out.println();
		
        List<SymbolInterface> symbols = new ArrayList<>();
        symbols.add(SymbolFactory.getSymbol("2", SymbolFactory.ZERO_VALUE));
        symbols.add(SymbolFactory.getSymbol("2", SymbolFactory.ZERO_VALUE));
        symbols.add(SymbolFactory.getSymbol("+", SymbolFactory.ZERO_VALUE));
        symbols.add(SymbolFactory.getSymbol("*", SymbolFactory.ZERO_VALUE));

        for (SymbolInterface symbol : symbols)
        {
            System.out.println(symbol);
        }
        
        System.out.println(symbols.get(0).equals(symbols.get(1)));
	}
}
