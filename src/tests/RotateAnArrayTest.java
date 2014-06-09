/*
 * RotateAnArrayTest.java
 */

package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import algos.RotateAnArray;

/**
 * Tests of array rotation
 */
public class RotateAnArrayTest
{

    /**
     * Basic tests
     */
    @Test
    public void test()
    {
        int[] arr1 = {};
        testOneRotate(arr1, 0, "[]");
        testOneRotate(arr1, 2, "[]");
        int[] arr2 = {1};
        testOneRotate(arr2, 0, "[1]");
        testOneRotate(arr2, 1, "[1]");
        testOneRotate(arr2, 2, "[1]");
        int[] arr3 = {1, 2};
        testOneRotate(arr3, 0, "[1, 2]");
        testOneRotate(arr3, 1, "[2, 1]");
        testOneRotate(arr3, 2, "[2, 1]");
        int[] arr4 = {1, 2, 3};
        testOneRotate(arr4, 0, "[1, 2, 3]");
        testOneRotate(arr4, 1, "[2, 3, 1]");
        testOneRotate(arr4, 2, "[1, 2, 3]");
        testOneRotate(arr4, 2, "[3, 1, 2]");
        testOneRotate(arr4, 3, "[3, 1, 2]");
        testOneRotate(arr4, 4, "[1, 2, 3]");
        int[] arr5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        testOneRotate(arr5, 3, "[4, 5, 6, 7, 8, 9, 10, 1, 2, 3]");
        testOneRotate(arr5, 2, "[6, 7, 8, 9, 10, 1, 2, 3, 4, 5]");
        testOneRotate(arr5, 4, "[10, 1, 2, 3, 4, 5, 6, 7, 8, 9]");
        testOneRotate(arr5, 5, "[5, 6, 7, 8, 9, 10, 1, 2, 3, 4]");
    }

    private void testOneRotate(int[] arr, int r, String expected) {
        RotateAnArray.rotateArray(arr, r);
        assertEquals(expected, Arrays.toString(arr));
    }
}
