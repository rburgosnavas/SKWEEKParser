package com.rburgos.skweekparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rburgos.skweekparser.functions.Functions;
import com.rburgos.skweekparser.utils.Utils;


public class Parser
{
	private static final String REGEX = 
			"((t\\b)|(x\\b)|(y\\b)|(z\\b)|" + 
			"(\\-?\\d*\\.\\d+)|(\\-?\\d+)|" + 
			"([\\+\\-\\*/\\%\\(\\)]|" + 
			"(\\|{1,2})|(\\&{1,2})|(\\<{1,2})|(\\>{1,2})|(\\^{1})))";
	
	private int t, x, y, z;
	private String expression;
	
	private Parser(ParserBuilder builder)
	{
	    this.expression = builder.expression;
	    this.t = builder.t;
	    this.x = builder.x;
	    this.y = builder.y;
	    this.z = builder.z;
	}
    
    public List<String> splitToList()
    {
        return this.splitToList(expression);
    }
	
	public List<String> splitToList(String expression)
	{
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(expression);
		ArrayList<String> symbols = new ArrayList<>();
		String current = "", previous = "";

		while(matcher.find()) 
		{
		    current = matcher.group();
		    
		    /*
		     * If the current match is a negative number, ie. -1, -123.45, 
		     * and the previous character is a not an operator, then we 
		     * interpret the current match as a joint "operator and operand" 
		     * string. Therefore we add the first substring "-" and the 
		     * remaining substring separately.
		     */
		    if (current.length() > 1 && current.charAt(0) == '-' && 
		            !Utils.isNotNumeric(previous) && !previous.equals(""))
		    {
	            symbols.add(current.substring(0, 1));
	            symbols.add(current.substring(1));
	            previous = current.substring(1);
		    }
		    else
		    {
	            symbols.add(current);
	            previous = current;
		    }
		}
		
		return symbols;
	}
	
	public List<String> toPostfix()
	{
	    return this.toPostfix(this.splitToList());
	}
	
	public List<String> toPostfix(List<String> symbols)
    {
        Stack<String> postfix = new Stack<>();
        Stack<String> operators = new Stack<>();
        for (String symbol : symbols)
        {
            if (Utils.isLowPrecedence(symbol))
            {
                pushLowPrecedence(postfix, operators, symbol);
            }
            else if (Utils.isHighPrecedence(symbol))
            {
                pushHighPrecedence(postfix, operators, symbol);
            }
            else if (Utils.isParenthesis(symbol))
            {
                pushParenthesis(postfix, operators, symbol);
            }
            else
            {
                postfix.push(symbol);
            }
        } // end for loop
        
        // push remaining elements from operator stack if it's not empty
        while (!operators.isEmpty()) 
        {
            postfix.push(operators.pop());    
        }
        
        return postfix;
    }

    private void pushParenthesis(Stack<String> postfix, 
            Stack<String> operators, String symbol)
    {
        String popped;
        if (Utils.isLeftParen(symbol))
        {
            operators.push(symbol);
        }
        else// if (Utils.isRightParen(symbol))
        {
            while (!operators.isEmpty())
            {
                popped = operators.pop();
                if (!Utils.isLeftParen(popped))
                {
                    postfix.push(popped);
                }
            }
        }
    }

    private void pushHighPrecedence(Stack<String> postfix,
            Stack<String> operators, String symbol)
    {
        /* Original
        if (operators.isEmpty())
        {
            operators.push(symbol);
        }
        else
        {
            if (Utils.isHighPrecedence(operators.peek()))
            {
                postfixStack.push(operators.pop());
                operators.push(symbol);
            }
            else
            {
                operators.push(symbol);
            }
        }
        */
        
        /* Version 2
        if (operators.isEmpty())
        {
            operators.push(symbol);
        }
        else if (Utils.isHighPrecedence(operators.peek()))
        {
            postfixStack.push(operators.pop());
            operators.push(symbol);
        }
        else
        {
            operators.push(symbol);
        }
        */
        
        if (!operators.isEmpty() && Utils.isHighPrecedence(operators.peek()))
        {
            postfix.push(operators.pop());
            operators.push(symbol);
        }
        else
        {
            operators.push(symbol);
        }
    }

    private void pushLowPrecedence(Stack<String> postfix,
            Stack<String> operators, String symbol)
    {
        /* Original
         if (operators.isEmpty())
        {
            operators.push(symbol);
        }
        else
        {
            if (Utils.isLeftParen(operators.peek()))
            {
                operators.push(symbol);
            }
            else
            {
                postfixStack.push(operators.pop());
                operators.push(symbol);                        
            }
        }
        */
        
        if (operators.isEmpty() || Utils.isLeftParen(operators.peek()))
        {
            operators.push(symbol);
        }
        else
        {
            postfix.push(operators.pop());
            operators.push(symbol);
        }
    }
	
	public int evaluateToInt()
	{
	    return (int)this.evaluate();
	}
	
	public double evaluate()
	{
	    return this.evaluate(this.toPostfix());
	}
	
	public double evaluate(List<String> postfixList)
	{
		Stack<String>calc = new Stack<>();
		for (String symbol : postfixList)
		{		    
		    if (!Utils.isParenthesis(symbol))
		    {
	            if (Functions.contains(symbol))
	            {
	                String x = calc.pop();
	                String y = calc.pop();
	                String result = Functions.getFunction(symbol).eval(x, y);
	                calc.push(result);
	            }
	            else if (symbol.equals("t"))
	            {
	                calc.push(String.valueOf(t));
	            }
	            else if (symbol.equals("x"))
	            {
	                calc.push(String.valueOf(x));
	            }
	            else if (symbol.equals("y"))
	            {
	                calc.push(String.valueOf(y));
	            }
	            else if (symbol.equals("z"))
	            {
	                calc.push(String.valueOf(z));
	            }
	            else
	            {
	                calc.push(symbol);
	            }
		    }
		}		
		double result = Double.parseDouble(calc.pop());
		return result;
	}

    public static class ParserBuilder
    {
        private String expression;
        private int t = 0, x = 0, y = 0, z = 0;
        private static Parser parser = null;
        
        public ParserBuilder(String exp)
        {
            this.expression = exp;
        }
        
        public ParserBuilder setT(int t)
        {
            this.t = t;
            return this;
        }
        
        public ParserBuilder setX(int x)
        {
            this.x = x;
            return this;
        }
        
        public ParserBuilder setY(int y)
        {
            this.y = y;
            return this;
        }
        
        public ParserBuilder setZ(int z)
        {
            this.z = z;
            return this;
        }
        
        public Parser build()
        {
            if (parser == null)
            {
                parser = new Parser(this);
            }
            return parser;
        }
    }
}
