package com.rburgos.skweekparser.lexer;

public class LexerBuilder
{
    private String expression;
    private int t = 0, x = 0, y = 0, z = 0;
    private static Lexer symbolizer = null;
    
    public LexerBuilder(String expression)
    {
        this.setExpression(expression);
    }

    public String getExpression()
    {
        return expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public int getT()
    {
        return t;
    }
    
    public LexerBuilder setT(int t)
    {
        this.t = t;
        return this;
    }

    public int getX()
    {
        return x;
    }
    
    public LexerBuilder setX(int x)
    {
        this.x = x;
        return this;
    }

    public int getY()
    {
        return y;
    }
    
    public LexerBuilder setY(int y)
    {
        this.y = y;
        return this;
    }

    public int getZ()
    {
        return z;
    }
    
    public LexerBuilder setZ(int z)
    {
        this.z = z;
        return this;
    }
    
    public Lexer make()
    {
        if (symbolizer == null)
        {
            symbolizer = new Lexer(this);
        }
        return symbolizer;
    }
}
