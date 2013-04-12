package com.rburgos.skweekparser.evaluator;

import java.util.List;
import java.util.Stack;

import com.rburgos.skweekparser.symbols.Symbol;
import com.rburgos.skweekparser.utils.Utils;

public class Evaluator
{
    List<Symbol> symbols = null;
    
    public Evaluator(List<Symbol> symbols)
    {
        this.symbols = symbols;
    }
    
    public List<Symbol> toPostfix()
    {
        Stack<Symbol> postfix = new Stack<>();
        Stack<Symbol> operators = new Stack<>();
        
        for (Symbol symbol : symbols)
        {
            if (Utils.isLowPrecedence(symbol.getSymbol()))
            {
                pushLowPrecedence(postfix, operators, symbol);
            }
            else if (Utils.isHighPrecedence(symbol.getSymbol()))
            {
                pushHighPrecedence(postfix, operators, symbol);
            }
            else if (Utils.isParenthesis(symbol.getSymbol()))
            {
                processParenthesis(postfix, operators, symbol);
            }
            else
            {
                postfix.push(symbol);
            }
        }
        
        while (!operators.isEmpty())
        {
            postfix.push(operators.pop());
        }
        
        return postfix;
    }

    private void processParenthesis(Stack<Symbol> postfix, 
            Stack<Symbol> operators, Symbol symbol)
    {
        Symbol popped;
        if (Utils.isLeftParen(symbol.getSymbol()))
        {
            operators.push(symbol);
        }
        else// if (Utils.isRightParen(symbol))
        {
            while (!operators.isEmpty())
            {
                popped = operators.pop();
                if (!Utils.isLeftParen(popped.getSymbol()))
                {
                    postfix.push(popped);
                }
            }
        }
    }

    private void pushHighPrecedence(Stack<Symbol> postfix,
            Stack<Symbol> operators, Symbol symbol)
    {
        if (!operators.isEmpty() && 
                Utils.isHighPrecedence(operators.peek().getSymbol()))
        {
            postfix.push(operators.pop());
            operators.push(symbol);
        }
        else
        {
            operators.push(symbol);
        }
    }

    private void pushLowPrecedence(Stack<Symbol> postfix,
            Stack<Symbol> operators, Symbol symbol)
    {
        if (operators.isEmpty() || 
                Utils.isLeftParen(operators.peek().getSymbol()))
        {
            operators.push(symbol);
        }
        else
        {
            postfix.push(operators.pop());
            operators.push(symbol);
        }
    }
}
