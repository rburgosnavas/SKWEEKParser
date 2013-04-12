package com.rburgos.skweekparser.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rburgos.skweekparser.evaluator.Evaluator;
import com.rburgos.skweekparser.symbols.SymbolFactory;
import com.rburgos.skweekparser.symbols.Symbol;
import com.rburgos.skweekparser.utils.Utils;

public class Lexer
{
    private int t, x, y, z;
    private String expression;
    private static final String REGEX = 
            "((t\\b)|(x\\b)|(y\\b)|(z\\b)|" + 
            "(\\-?\\d*\\.\\d+)|(\\-?\\d+)|" + 
            "([\\+\\-\\*/\\%\\(\\)]|" + 
            "(\\|{1,2})|(\\&{1,2})|(\\<{1,2})|(\\>{1,2})|(\\^{1})))";
    
    public Lexer(LexerBuilder sb)
    {
        this.expression = sb.getExpression();
        this.t = sb.getT();
        this.x = sb.getX();
        this.y = sb.getY();
        this.z = sb.getZ();
    }
    
    public Evaluator evaluator()
    {
        return new Evaluator(this.tokenize());
    }
    
    public List<Symbol> tokenize()
    {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(this.expression);
        List<Symbol> symbols = new ArrayList<>();
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
                symbols.add(SymbolFactory.getSymbol(current.substring(0, 1)));
                symbols.add(SymbolFactory.getSymbol(current.substring(1)));
                
                previous = current.substring(1);
            }
            else if (Utils.isVariable(current))
            {
                if (Utils.isT(current))
                {
                    symbols.add(SymbolFactory.getSymbol(current, this.t));
                }
                else if (Utils.isX(current))
                {
                    symbols.add(SymbolFactory.getSymbol(current, this.x));
                }
                else if (Utils.isY(current))
                {
                    symbols.add(SymbolFactory.getSymbol(current, this.y));
                }
                else if (Utils.isZ(current))
                {
                    symbols.add(SymbolFactory.getSymbol(current, this.z));
                }
                previous = current;
            }
            else
            {
                symbols.add(SymbolFactory.getSymbol(current));
                previous = current;
            }
        }
        
        return symbols;
    }
}
