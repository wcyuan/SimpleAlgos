/*
 * GraphTest.java
 */

package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import algos.Graph;

/**
 *
 */
public class GraphTest
{

    /**
     * Basic test of Graphs
     */
    @Test
    public void test()
    {
        Graph g = new Graph(0, 4);
        assertEquals("{\n}", g.toString());
        assertEquals(0, g.getNumVertices());
        g.setNumVertices(1);
        assertEquals("{\n 0: []\n}", g.toString());
        assertEquals(1, g.getNumVertices());
        g.setNumVertices(2);
        assertEquals("{\n 0: []\n 1: []\n}", g.toString());
        assertEquals(2, g.getNumVertices());
        g.addEdge(0, 1, 1);
        assertEquals("{\n 0: [1 (1)]\n 1: []\n}", g.toString());
        assertEquals(2, g.getNumVertices());
        assertEquals(1, g.getWeight(0, 1));
        g.addEdge(0, 1, 2);
        assertEquals("{\n 0: [1 (2)]\n 1: []\n}", g.toString());
        assertEquals(2, g.getNumVertices());
        assertEquals(2, g.getWeight(0, 1));
        g.removeEdge(0, 1);
        assertEquals("{\n 0: []\n 1: []\n}", g.toString());
        assertEquals(2, g.getNumVertices());
        g.addEdge(0, 1, 3);
        assertEquals("{\n 0: [1 (3)]\n 1: []\n}", g.toString());
        assertEquals(2, g.getNumVertices());
        g.setNumVertices(1);
        assertEquals("{\n 0: []\n}", g.toString());
        assertEquals(1, g.getNumVertices());
    }
}
