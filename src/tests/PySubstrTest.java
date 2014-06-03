/*
 * BigMultTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static algos.PySubstr.fixidx;
import static algos.PySubstr.substr;
import static algos.PySubstr.prefix;
import static algos.PySubstr.suffix;

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
        assertEquals(0, fixidx(5, 0));
        assertEquals(1, fixidx(5, 1));
        assertEquals(2, fixidx(5, 2));
        assertEquals(3, fixidx(5, 3));
        assertEquals(4, fixidx(5, 4));
        assertEquals(5, fixidx(5, 5));
        assertEquals(5, fixidx(5, 6));
        assertEquals(4, fixidx(5, -1));
        assertEquals(3, fixidx(5, -2));
        assertEquals(2, fixidx(5, -3));
        assertEquals(1, fixidx(5, -4));
        assertEquals(0, fixidx(5, -5));
        assertEquals(0, fixidx(5, -6));
        assertEquals(0, fixidx(5, -7));
        assertEquals(0, fixidx(5, 0));

        assertEquals("", substr("abcdef", 0, 0));
        assertEquals("a", "abcdef".substring(0, 1));
        assertEquals("f", "abcdef".substring(5, 6));
        assertEquals("a", substr("abcdef", 0, 1));
        assertEquals("ab", substr("abcdef", 0, 2));
        assertEquals("abc", substr("abcdef", 0, 3));
        assertEquals("abcd", substr("abcdef", 0, 4));
        assertEquals("abcde", substr("abcdef", 0, 5));
        assertEquals("abcdef", substr("abcdef", 0, 6));
        assertEquals("abcdef", substr("abcdef", 0, 7));
        assertEquals("bcdef", substr("abcdef", 1, 7));
        assertEquals("cdef", substr("abcdef", 2, 7));
        assertEquals("def", substr("abcdef", 3, 7));
        assertEquals("ef", substr("abcdef", 4, 7));
        assertEquals("f", substr("abcdef", 5, 7));
        assertEquals("", substr("abcdef", 6, 7));
        assertEquals("d", substr("abcdef", 3, 4));
        assertEquals("", substr("abcdef", 0, -8));
        assertEquals("", substr("abcdef", 0, -7));
        assertEquals("", substr("abcdef", 0, -6));
        assertEquals("a", substr("abcdef", 0, -5));
        assertEquals("ab", substr("abcdef", 0, -4));
        assertEquals("abc", substr("abcdef", 0, -3));
        assertEquals("abcd", substr("abcdef", 0, -2));
        assertEquals("abcde", substr("abcdef", 0, -1));
        assertEquals("a", substr("abcdef", 0, 1));
        assertEquals("", substr("abcdef", -1, 1));
        assertEquals("", substr("abcdef", -2, 1));
        assertEquals("", substr("abcdef", -3, 1));
        assertEquals("", substr("abcdef", -4, 1));
        assertEquals("", substr("abcdef", -5, 1));
        assertEquals("a", substr("abcdef", -6, 1));
        assertEquals("a", substr("abcdef", -7, 1));
        assertEquals("a", substr("abcdef", -8, 1));
        assertEquals("a", substr("abcdef", -9, 1));
        assertEquals("", prefix("", -3));
        assertEquals("", prefix("1", -3));
        assertEquals("", prefix("12", -3));
        assertEquals("", prefix("123", -3));
        assertEquals("1", prefix("1234", -3));
        assertEquals("12", prefix("12345", -3));
        assertEquals("123", prefix("123456", -3));
        assertEquals("", suffix("", -3));
        assertEquals("1", suffix("1", -3));
        assertEquals("12", suffix("12", -3));
        assertEquals("123", suffix("123", -3));
        assertEquals("234", suffix("1234", -3));
        assertEquals("345", suffix("12345", -3));
    }
}
