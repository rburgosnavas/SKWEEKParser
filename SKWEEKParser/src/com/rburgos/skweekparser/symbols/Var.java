package com.rburgos.skweekparser.symbols;

public class Var extends GenericSymbol
{
    int value;
    
    public Var(String token, int value)
    {
        super(token);
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return super.toString() + " = " + value;
    }
}
