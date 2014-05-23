/*
 * DijkstraTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.Dijkstra;
import algos.Graph;

/**
 * Tests for Dijkstra
 */
public class DijkstraTest
{

    /**
     * Basic tests
     */
    @Test
    public void test()
    {
        Graph graph = new Graph(6);
        graph.addBidirectionalEdge(0, 1, 7);
        graph.addBidirectionalEdge(0, 2, 9);
        graph.addBidirectionalEdge(0, 5, 14);
        graph.addBidirectionalEdge(1, 2, 10);
        graph.addBidirectionalEdge(1, 3, 15);
        graph.addBidirectionalEdge(2, 3, 11);
        graph.addBidirectionalEdge(2, 5, 2);
        graph.addBidirectionalEdge(3, 4, 6);
        graph.addBidirectionalEdge(4, 5, 9);

        Dijkstra dij = new Dijkstra(graph, 0);
        assertEquals(0, dij.getDistance(0));
        assertEquals(7, dij.getDistance(1));
        assertEquals(9, dij.getDistance(2));
        assertEquals(20, dij.getDistance(3));
        assertEquals(20, dij.getDistance(4));
        assertEquals(11, dij.getDistance(5));

        assertEquals(-1, dij.getPrevious(0));
        assertEquals(0, dij.getPrevious(1));
        assertEquals(0, dij.getPrevious(2));
        assertEquals(2, dij.getPrevious(3));
        assertEquals(5, dij.getPrevious(4));
        assertEquals(2, dij.getPrevious(5));
}

}
