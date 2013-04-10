package com.rburgos.skweekparser.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rburgos.skweekparser.symbols.AbstractSymbol;
import com.rburgos.skweekparser.symbols.Num;

public class SkweekTester
{
    // SkweekNum num = new SkweekNum("265");
    
    @Test
    public void testNumericTrue()
    {
        /*assertTrue(num.isNumeric());

        num.setSymbol("0");
        assertTrue(num.isNumeric());
        
        num.setSymbol("10");
        assertTrue(num.isNumeric());
        
        num.setSymbol("0012");
        assertTrue(num.isNumeric());
        
        num.setSymbol("2658.02154");
        assertTrue(num.isNumeric());
        
        num.setSymbol("-0.25");
        assertTrue(num.isNumeric());*/
    }
    
    @Test
    public void testNumericFalse()
    {
        /*num.setSymbol("aaaaa");
        assertFalse(num.isNumeric());

        num.setSymbol("a");
        assertFalse(num.isNumeric());

        num.setSymbol("a.polp");
        assertFalse(num.isNumeric());

        num.setSymbol("a.021");
        assertFalse(num.isNumeric());

        num.setSymbol("2.apoel");
        assertFalse(num.isNumeric());

        num.setSymbol("-985.hell");
        assertFalse(num.isNumeric());

        num.setSymbol("23.158.12354");
        assertFalse(num.isNumeric());

        num.setSymbol("--888");
        assertFalse(num.isNumeric());

        num.setSymbol("-negative.123456");
        assertFalse(num.isNumeric());

        num.setSymbol("- 8");
        assertFalse(num.isNumeric());

        num.setSymbol("-2w3wow");
        assertFalse(num.isNumeric());

        num.setSymbol("+++++");
        assertFalse(num.isNumeric());

        num.setSymbol("mzidhfdz74&(^%E");
        assertFalse(num.isNumeric());*/
    }

}
