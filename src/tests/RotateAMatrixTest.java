/*
 * RotateAMatrixTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.Matrix;

/**
 *
 */
public class RotateAMatrixTest
{

    /**
     * Basic tests
     */
    @Test
    public void test()
    {
        Integer[][] bef1 = {{}};
        testOne(bef1, "");

        Integer[][] bef2 = {{1}};
        testOne(bef2, "1");

        Integer[][] bef3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        testOne(bef3, "7, 4, 1\n8, 5, 2\n9, 6, 3");

        Integer[][] bef4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        testOne(bef4, "13, 9, 5, 1\n14, 10, 6, 2\n15, 11, 7, 3\n16, 12, 8, 4");
    }

    private void testOne(Integer[][] arr, String expected) {
        Matrix<Integer> mat = new Matrix<Integer>(arr);
        mat.rotate();
        assertEquals(expected, mat.toString());
    }
}
