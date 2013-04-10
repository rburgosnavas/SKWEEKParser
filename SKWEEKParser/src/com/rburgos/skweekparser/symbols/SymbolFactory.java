package com.rburgos.skweekparser.symbols;

public class SymbolFactory
{
    public final static int ZERO_VALUE = 0;
    
    private SymbolFactory() { }
    
    public static SymbolInterface getSymbol(String token, int value)
    {
        SymbolInterface symbol = null;
        
        if (isNum(token))
        {
            symbol = new Num(token);
        }
        else if (isOp(token))
        {
            symbol = new Op(token);
        }
        else if (isPar(token))
        {
            symbol = new Par(token);
        }
        else if (isVar(token))
        {
            symbol = new Var(token, value);
        }
        return symbol;
    }

    private static boolean isPar(String token)
    {
        return token.equals("(") || token.equals(")");
    }
    
    private static boolean isNum(String token)
    {
        return token.matches("\\-?\\d+\\.?\\d*");
    }
    
    private static boolean isOp(String token)
    {
        return token.equals("+") || token.equals("-") || token.equals("*") || 
               token.equals("/") || token.equals("^") || token.equals("<<") || 
               token.equals(">>") || token.equals("|") || token.equals("&") || 
               token.equals("%");
    }
    
    private static boolean isVar(String token)
    {
        return token.equals("t") || token.equals("x") || 
               token.equals("y") || token.equals("z");
    }
}
