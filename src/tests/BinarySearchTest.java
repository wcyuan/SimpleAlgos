/*
 * BinarySearchTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.BinarySearch;

/**
 *
 */
public class BinarySearchTest
{

    /**
     * Tests for Binary Search
     */
    @Test
    public void test()
    {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(-1, BinarySearch.search(0, arr));
        assertEquals(0, BinarySearch.search(1, arr));
        assertEquals(1, BinarySearch.search(2, arr));
        assertEquals(2, BinarySearch.search(3, arr));
        assertEquals(3, BinarySearch.search(4, arr));
        assertEquals(4, BinarySearch.search(5, arr));
        assertEquals(5, BinarySearch.search(6, arr));
        assertEquals(6, BinarySearch.search(7, arr));
        assertEquals(7, BinarySearch.search(8, arr));
        assertEquals(8, BinarySearch.search(9, arr));
        Integer[] emp = {};
        assertEquals(-1, BinarySearch.search(1, emp));
    }

}
