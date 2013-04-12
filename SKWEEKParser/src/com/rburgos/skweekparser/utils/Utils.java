package com.rburgos.skweekparser.utils;

public class Utils
{
    private Utils() {}
    
    public static boolean isLowPrecedence(String symbol)
    {
        return symbol.equals("+") || symbol.equals("-");
    }

    public static boolean isHighPrecedence(String symbol)
    {
        return symbol.equals("*") || symbol.equals("/") || 
               symbol.equals("^") || symbol.equals("<<") || 
               symbol.equals(">>") || symbol.equals("|") || 
               symbol.equals("&") || symbol.equals("%");
    }

    public static boolean isLeftParen(String symbol)
    {
        return symbol.equals("(");
    }

    public static boolean isRightParen(String symbol)
    {
        return symbol.equals(")");
    }

    public static boolean isParenthesis(String symbol)
    {
        return isLeftParen(symbol) || isRightParen(symbol);
    }

    public static boolean isOperator(String symbol)
    {
        return isLowPrecedence(symbol) || isHighPrecedence(symbol);
    }
    
    public static boolean isNumeric(String symbol)
    {
        return symbol.matches("\\-?\\d+\\.?\\d*");
    }

    public static boolean isNotNumeric(String symbol)
    {
        return isOperator(symbol) || isParenthesis(symbol);
    }

    public static boolean isT(String symbol)
    {
        return symbol.equals("t");
    }
    
    public static boolean isX(String symbol)
    {
        return symbol.equals("x");
    }
    
    public static boolean isY(String symbol)
    {
        return symbol.equals("y");
    }
    
    public static boolean isZ(String symbol)
    {
        return symbol.equals("z");
    }
    
    public static boolean isVariable(String symbol)
    {
        return  isT(symbol) || isX(symbol) || isY(symbol) || isZ(symbol);
    }
}
