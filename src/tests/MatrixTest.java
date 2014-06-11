/*
 * RotateAMatrixTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.Matrix;

/**
 * Test for Matrices
 */
public class MatrixTest
{

    /**
     * Test rotation
     */
    @Test
    public void testRotate()
    {
        Integer[][] bef1 = {{}};
        testOneRotate(bef1, "");

        Integer[][] bef2 = {{1}};
        testOneRotate(bef2, "1");

        Integer[][] bef3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        testOneRotate(bef3, "7, 4, 1\n8, 5, 2\n9, 6, 3");

        Integer[][] bef4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        testOneRotate(bef4, "13, 9, 5, 1\n14, 10, 6, 2\n15, 11, 7, 3\n16, 12, 8, 4");
    }

    private void testOneRotate(Integer[][] arr, String expected) {
        Matrix<Integer> mat = new Matrix<Integer>(arr);
        mat.rotate();
        assertEquals(expected, mat.toString());
    }

    /**
     * Test printing in a spiral
     */
    @Test
    public void testSpiral()
    {
        Integer[][] bef1 = {{}};
        testOneSpiral(bef1, "");

        Integer[][] bef2 = {{1}};
        testOneSpiral(bef2, "1");

        Integer[][] bef3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        testOneSpiral(bef3, "1, 2, 3, 6, 9, 8, 7, 4, 5");

        Integer[][] bef4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        testOneSpiral(bef4, "1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10");

        Integer[][] bef5 = {{1, 2}};
        testOneSpiral(bef5, "1, 2");
        Integer[][] bef6 = {{1, 2, 3}};
        testOneSpiral(bef6, "1, 2, 3");
        Integer[][] bef7 = {{1}, {2}};
        testOneSpiral(bef7, "1, 2");
        Integer[][] bef8 = {{1}, {2}, {3}};
        testOneSpiral(bef8, "1, 2, 3");

        Integer[][] bef9 = {{1, 2}, {3, 4}, {5, 6}};
        testOneSpiral(bef9, "1, 2, 4, 6, 5, 3");
        Integer[][] bef10 = {{1, 2, 3}, {4, 5, 6}};
        testOneSpiral(bef10, "1, 2, 3, 6, 5, 4");
    }

    private void testOneSpiral(Integer[][] arr, String expected) {
        Matrix<Integer> mat = new Matrix<Integer>(arr);
        assertEquals(expected, mat.printSpiral());
    }
}
