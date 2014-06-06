package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.BigInt;

public class BitIntTest
{

    /**
     * Multiplication Tests
     */
    @Test
    public void testMultiplication()
    {
        assertEquals("0", new BigInt(0).toString());
        assertEquals("0", new BigInt(-0).toString());
        assertEquals("123", new BigInt(123).toString());
        assertEquals("-123", new BigInt(-123).toString());
        assertEquals("0", new BigInt("0").toString());
        assertEquals("-0", new BigInt("-0").toString());
        assertEquals("123", new BigInt("123").toString());
        assertEquals("-123", new BigInt("-123").toString());
        assertEquals("2460", (new BigInt(123)).mult(new BigInt(20)).toString());
        assertEquals("4920", (new BigInt(123)).mult(new BigInt(40)).toString());
        assertEquals("13800", (new BigInt(345)).mult(new BigInt(40)).toString());
        assertEquals(13800, (new BigInt(345)).mult(new BigInt(40)).toLong());
    }
}
