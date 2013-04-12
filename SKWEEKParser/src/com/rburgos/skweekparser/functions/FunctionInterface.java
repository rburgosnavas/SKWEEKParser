package com.rburgos.skweekparser.functions;

public interface FunctionInterface
{
    String eval(String x, String y) throws ArithmeticException, 
            NumberFormatException;
    double eval(double x, double y);
}
