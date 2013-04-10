package com.rburgos.skweekparser;

import java.util.HashMap;
import java.util.Map;


public enum Functions implements FunctionInterface
{
    ADD("+")
    {
        @Override
        public String eval(String x, String y)
        {
            return String.valueOf((Double.parseDouble(x) + 
                    Double.parseDouble(y)));
        }
    },
    SUB("-")
    {
        @Override
        public String eval(String x, String y)
        {
            return String.valueOf((Double.parseDouble(y) - 
                    Double.parseDouble(x)));
        }
    },
    MULT("*")
    {
        @Override
        public String eval(String x, String y)
        {
            return String.valueOf((Double.parseDouble(x) * 
                    Double.parseDouble(y)));
        }
    },
    DIV("/")
    {
        @Override
        public String eval(String x, String y)
        {
            return String.valueOf((Double.parseDouble(y) / 
                    Double.parseDouble(x)));
        }
    },
    MOD("%")
    {
        @Override
        public String eval(String x, String y)
        {
            return String.valueOf((Double.parseDouble(y) % 
                    Double.parseDouble(x)));
        }
    },
    POW("^")
    {
        @Override
        public String eval(String x, String y)
        {
            return String.valueOf(Math.pow(Double.parseDouble(y), 
                    Double.parseDouble(x)));
        }
    }, 
    LSHIFT("<<")
    {
        @Override
        public String eval(String x, String y)
        {
            double a = Double.parseDouble(x);
            double b = Double.parseDouble(y);
            return String.valueOf(((int)b << (int)a));
        }
    },
    RSHIFT(">>")
    {
        @Override
        public String eval(String x, String y)
        {
            double a = Double.parseDouble(x);
            double b = Double.parseDouble(y);
            return String.valueOf(((int)b >> (int)a));
        }
    },
    OR("|")
    {
        @Override
        public String eval(String x, String y)
        {
            double a = Double.parseDouble(x);
            double b = Double.parseDouble(y);
            return String.valueOf(((int)b | (int)a));
        }
    },
    AND("&")
    {
        @Override
        public String eval(String x, String y)
        {
            double a = Double.parseDouble(x);
            double b = Double.parseDouble(y);
            return String.valueOf(((int)b & (int)a));
        }
    };
    
    private String operator;
    private static final Map<String, Functions> opMap = new HashMap<>();
            
    static
    {
        for (Functions f : values())
        {
            opMap.put(f.toString(), f);
        }
    }
    
    private Functions(String operator)
    {
        this.operator = operator;
    }

    @Override
    public String toString()
    {
        return this.operator;
    }
    
    public static Functions getFunction(String op)
    {
        return opMap.get(op);
    }
    
    public static boolean contains(String op)
    {
        return opMap.containsKey(op);
    }
}
