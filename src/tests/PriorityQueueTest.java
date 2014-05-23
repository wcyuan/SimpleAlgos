/*
 * PriorityQueueTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.PriorityQueue;

/**
 * Tests for PriorityQueues
 */
public class PriorityQueueTest
{
    /**
     * Basic tests
     */
    @Test
    public void test()
    {
        PriorityQueue<String, Integer> pq =
            new PriorityQueue<String, Integer>(2);
        pq.insert("A", 4);
        pq.insert("B", 2);
        assertEquals("[(B, 2), (A, 4)]", pq.toString());
        assertEquals(2, (int) pq.findMinValue());
        assertEquals("B", pq.findMinKey());
    }

    /**
     * Test construction from arrays
     */
    @Test
    public void testFromArrays()
    {
        String[] keys = { "A", "B", "C", "D" };
        Integer[] values = { 4, 2, 14, 5 };
        PriorityQueue<String, Integer> pq =
            new PriorityQueue<String, Integer>(keys, values);
        assertEquals("[(B, 2), (A, 4), (C, 14), (D, 5)]", pq.toString());
        assertEquals(2, (int) pq.findMinValue());
        assertEquals("B", pq.findMinKey());
    }
}
