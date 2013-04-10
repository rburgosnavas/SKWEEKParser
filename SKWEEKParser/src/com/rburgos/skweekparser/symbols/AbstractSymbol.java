package com.rburgos.skweekparser.symbols;

public abstract class AbstractSymbol implements SymbolInterface
{
    String token;
    
    public AbstractSymbol(String token)
    {
        this.token = token;
    }
    
    @Override
    public String getSymbol()
    {
        return this.token;
    }

    @Override
    public void setSymbol(String token)
    {
        this.token = token;
    }

    public boolean isOpenParenthesis()
    {
        return token.equals("(");
    }
    
    public boolean isCloseParenthesis()
    {
        return token.equals(")");
    }
    
    public boolean isNumeric()
    {
        return token.matches("\\-?\\d+\\.?\\d*");
    }
    
    public boolean isOperator()
    {
        return token.equals("+") || token.equals("-") || token.equals("*") || 
               token.equals("/") || token.equals("^") || token.equals("<<") || 
               token.equals(">>") || token.equals("|") || token.equals("&") || 
               token.equals("%");
    }

    public boolean isT()
    {
        return token.equals("t");
    }
    
    public boolean isX()
    {
        return token.equals("x");
    }
    
    public boolean isY()
    {
        return token.equals("y");
    }
    
    public boolean isZ()
    {
        return token.equals("z");
    }
    
    public boolean isVariable()
    {
        return  isX() || isY() || isZ();
    }
}
