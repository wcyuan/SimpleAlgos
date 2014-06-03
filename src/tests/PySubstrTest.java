/*
 * BigMultTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.PySubstr;

/**
 * Test class for converting to python-like substring indices
 */
public class PySubstrTest
{

    /**
     * test functions
     */
    @Test
    public void testBasic()
    {
        assertEquals(0, PySubstr.fixidx(5, 0));
        assertEquals(1, PySubstr.fixidx(5, 1));
        assertEquals(2, PySubstr.fixidx(5, 2));
        assertEquals(3, PySubstr.fixidx(5, 3));
        assertEquals(4, PySubstr.fixidx(5, 4));
        assertEquals(5, PySubstr.fixidx(5, 5));
        assertEquals(5, PySubstr.fixidx(5, 6));
        assertEquals(4, PySubstr.fixidx(5, -1));
        assertEquals(3, PySubstr.fixidx(5, -2));
        assertEquals(2, PySubstr.fixidx(5, -3));
        assertEquals(1, PySubstr.fixidx(5, -4));
        assertEquals(0, PySubstr.fixidx(5, -5));
        assertEquals(0, PySubstr.fixidx(5, -6));
        assertEquals(0, PySubstr.fixidx(5, -7));
        assertEquals(0, PySubstr.fixidx(5, 0));

        assertEquals("", PySubstr.substr("abcdef", 0, 0));
        assertEquals("a", "abcdef".substring(0, 1));
        assertEquals("f", "abcdef".substring(5, 6));
        assertEquals("a", PySubstr.substr("abcdef", 0, 1));
        assertEquals("ab", PySubstr.substr("abcdef", 0, 2));
        assertEquals("abc", PySubstr.substr("abcdef", 0, 3));
        assertEquals("abcd", PySubstr.substr("abcdef", 0, 4));
        assertEquals("abcde", PySubstr.substr("abcdef", 0, 5));
        assertEquals("abcdef", PySubstr.substr("abcdef", 0, 6));
        assertEquals("abcdef", PySubstr.substr("abcdef", 0, 7));
        assertEquals("bcdef", PySubstr.substr("abcdef", 1, 7));
        assertEquals("cdef", PySubstr.substr("abcdef", 2, 7));
        assertEquals("def", PySubstr.substr("abcdef", 3, 7));
        assertEquals("ef", PySubstr.substr("abcdef", 4, 7));
        assertEquals("f", PySubstr.substr("abcdef", 5, 7));
        assertEquals("", PySubstr.substr("abcdef", 6, 7));
        assertEquals("d", PySubstr.substr("abcdef", 3, 4));
        assertEquals("", PySubstr.substr("abcdef", 0, -8));
        assertEquals("", PySubstr.substr("abcdef", 0, -7));
        assertEquals("", PySubstr.substr("abcdef", 0, -6));
        assertEquals("a", PySubstr.substr("abcdef", 0, -5));
        assertEquals("ab", PySubstr.substr("abcdef", 0, -4));
        assertEquals("abc", PySubstr.substr("abcdef", 0, -3));
        assertEquals("abcd", PySubstr.substr("abcdef", 0, -2));
        assertEquals("abcde", PySubstr.substr("abcdef", 0, -1));
        assertEquals("a", PySubstr.substr("abcdef", 0, 1));
        assertEquals("", PySubstr.substr("abcdef", -1, 1));
        assertEquals("", PySubstr.substr("abcdef", -2, 1));
        assertEquals("", PySubstr.substr("abcdef", -3, 1));
        assertEquals("", PySubstr.substr("abcdef", -4, 1));
        assertEquals("", PySubstr.substr("abcdef", -5, 1));
        assertEquals("a", PySubstr.substr("abcdef", -6, 1));
        assertEquals("a", PySubstr.substr("abcdef", -7, 1));
        assertEquals("a", PySubstr.substr("abcdef", -8, 1));
        assertEquals("a", PySubstr.substr("abcdef", -9, 1));
        assertEquals("", PySubstr.prefix("", -3));
        assertEquals("", PySubstr.prefix("1", -3));
        assertEquals("", PySubstr.prefix("12", -3));
        assertEquals("", PySubstr.prefix("123", -3));
        assertEquals("1", PySubstr.prefix("1234", -3));
        assertEquals("12", PySubstr.prefix("12345", -3));
        assertEquals("123", PySubstr.prefix("123456", -3));
        assertEquals("", PySubstr.suffix("", -3));
        assertEquals("1", PySubstr.suffix("1", -3));
        assertEquals("12", PySubstr.suffix("12", -3));
        assertEquals("123", PySubstr.suffix("123", -3));
        assertEquals("234", PySubstr.suffix("1234", -3));
        assertEquals("345", PySubstr.suffix("12345", -3));
    }
}
