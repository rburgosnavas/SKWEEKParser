package com.rburgos.skweekparser;

public class SymbolUtils
{
    private SymbolUtils() {}
    
    static boolean isLowPrecedence(String symbol)
    {
        return symbol.equals("+") || symbol.equals("-");
    }

    static boolean isHighPrecedence(String symbol)
    {
        return symbol.equals("*") || symbol.equals("/") || 
               symbol.equals("^") || symbol.equals("<<") || 
               symbol.equals(">>") || symbol.equals("|") || 
               symbol.equals("&") || symbol.equals("%");
    }

    static boolean isLeftParen(String symbol)
    {
        return symbol.equals("(");
    }

    static boolean isRightParen(String symbol)
    {
        return symbol.equals(")");
    }

    static boolean isParenthesis(String symbol)
    {
        return isLeftParen(symbol) || isRightParen(symbol);
    }

    static boolean isOperator(String symbol)
    {
        return isLowPrecedence(symbol) || isHighPrecedence(symbol);
    }

    static boolean isNotNumeric(String symbol)
    {
        return isOperator(symbol) || isParenthesis(symbol);
    }

    static boolean isT(String symbol)
    {
        return symbol.equals("t");
    }
    
    static boolean isX(String symbol)
    {
        return symbol.equals("x");
    }
    
    static boolean isY(String symbol)
    {
        return symbol.equals("y");
    }
    
    static boolean isZ(String symbol)
    {
        return symbol.equals("z");
    }
    
    static boolean isVariable(String symbol)
    {
        return  isT(symbol) || isX(symbol) || isY(symbol) || isZ(symbol);
    }
}
