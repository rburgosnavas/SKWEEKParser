package com.rburgos.skweekparser.interpreter;

import java.util.List;
import java.util.Stack;

import com.rburgos.skweekparser.functions.Functions;
import com.rburgos.skweekparser.symbols.Num;
import com.rburgos.skweekparser.symbols.Par;
import com.rburgos.skweekparser.symbols.Symbol;
import com.rburgos.skweekparser.symbols.Var;


public class Interpreter
{
    private Interpreter() {}
    
    public static double calculate(List<Symbol> postfix)
    {
        Stack<Double> calc = new Stack<>();
        double x, y, result;
        for (Symbol symbol : postfix)
        {
            if (!(symbol instanceof Par))
            {
                if (Functions.contains(symbol.getSymbol()))
                {
                    x = calc.pop().doubleValue();
                    y = calc.pop().doubleValue();
                    result = Functions.getFunction(symbol.getSymbol()).eval(x, y);
                    calc.push(result);
                }
                else if (symbol instanceof Var)
                {
                    calc.push(((Var) symbol).getDoubleValue());
                }
                else
                {
                    calc.push(((Num) symbol).asDouble());
                }
            }
        }
        return calc.pop();
    }
}
