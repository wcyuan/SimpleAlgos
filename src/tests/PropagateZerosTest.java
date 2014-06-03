/*
 * PropagateZerosTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static algos.PropagateZeros.propagateZeros;

/**
 * Test the PropagateZeros class
 */
public class PropagateZerosTest
{

    /**
     * Test when there should be no change
     */
    @Test
    public void testNoChange()
    {
        int[][] orig = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        propagateZeros(arr);
        assertMatrixEquals(orig, arr);
    }

    /**
     * Test corner
     */
    @Test
    public void testCorner()
    {
        int[][] expected = {{0, 0, 0}, {0, 5, 6}, {0, 8, 9}};
        int[][] arr = {{0, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        propagateZeros(arr);
        assertMatrixEquals(expected, arr);
    }

    /**
     * Test two zeros in a row
     */
    @Test
    public void testTwoZerosRow()
    {
        int[][] expected = {{0, 0, 0}, {0, 5, 0}, {0, 8, 0}};
        int[][] arr = {{0, 2, 0}, {4, 5, 6}, {7, 8, 9}};
        propagateZeros(arr);
        assertMatrixEquals(expected, arr);
    }

    /**
     * Test two zeros diagonal from each other
     */
    @Test
    public void testTwoZerosDiag()
    {
        int[][] expected = {{0, 0, 0}, {0, 5, 0}, {0, 0, 0}};
        int[][] arr = {{0, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        propagateZeros(arr);
        assertMatrixEquals(expected, arr);
    }

    /**
     * Test everything being zero
     */
    @Test
    public void testAllZeros()
    {
        int[][] expected = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] arr = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        propagateZeros(arr);
        assertMatrixEquals(expected, arr);
    }

    private void assertMatrixEquals(int[][] expected, int [][] actual) {
        for (int ii = 0; ii < expected.length; ii++) {
            assert(ii >= actual.length);
            assertArrayEquals(expected[ii], actual[ii]);
        }
    }
}
