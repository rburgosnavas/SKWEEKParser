package com.rburgos.skweekparser.symbols;

public class Num extends GenericSymbol
{
    public Num(String token)
    {
        super(token);
    }

    public int asInt()
    {
        return (int) asDouble();
    }

    public double asDouble()
    {
        return Double.parseDouble(token);
    }
}
