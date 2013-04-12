package com.rburgos.skweekparser.symbols;

import com.rburgos.skweekparser.utils.Utils;


public class SymbolFactory
{
    public final static int ZERO_VALUE = 0;
    
    private SymbolFactory() { }
    
    public static Symbol getSymbol(String token)
    {
        Symbol symbol = null;
        
        if (Utils.isNumeric(token))
        {
            symbol = new Num(token);
        }
        else if (Utils.isOperator(token))
        {
            symbol = new Op(token);
        }
        else if (Utils.isParenthesis(token))
        {
            symbol = new Par(token);
        }
        return symbol;
    }
    
    public static Symbol getSymbol(String token, int value)
    {
        Symbol symbol = null;
        
        if (Utils.isVariable(token))
        {
            symbol = new Var(token, value);
        }
        return symbol;
    }
}
