/*
 * Dijkstra.java
 */

package algos;

import algos.Graph.Edge;

/**
 * An implementation of Dijkstra's Algorithm. This implementation uses a binary
 * heap, which is not as efficient as using a fibonacci heap.
 */
public class Dijkstra
{
    private final Graph graph;
    private final int source;
    private final int[] distance;
    private final int[] previous;
    private boolean computed = false;
    private static int INFINITY = Integer.MAX_VALUE;

    /**
     * Constructs a Dijkstra object, and initializes data structures.
     * 
     * @param _graph
     * @param _source
     */
    public Dijkstra(Graph _graph, int _source)
    {
        graph = _graph;
        source = _source;
        if (source < 0 || source > graph.getNumVertices()) {
            throw new IllegalArgumentException("Invalid source " + source
                + " is not between 0 and " + graph.getNumVertices());
        }
        distance = new int[graph.getNumVertices()];
        previous = new int[graph.getNumVertices()];
    }

    /**
     * Runs Dijkstra's algorithm and populates the distance and previous arrays.
     */
    private void compute()
    {
        PriorityQueue<Integer, Integer> pq =
            new PriorityQueue<Integer, Integer>(distance.length);
        for (int ii = 0; ii < distance.length; ii++) {
            if (ii == source) {
                distance[ii] = 0;
            }
            else {
                distance[ii] = INFINITY;
            }
            pq.insert(ii, distance[ii]);
            previous[ii] = -1;
        }
        while (pq.size() > 0) {
            int vertex = pq.findMinKey();
            pq.extractMin();
            if (distance[vertex] == INFINITY) {
                break;
            }
            for (Edge edge : graph.getNeighbors(vertex)) {
                int neighbor = edge.getTarget();
                int alt = distance[vertex] + edge.getWeight();
                if (alt < distance[neighbor]) {
                    distance[neighbor] = alt;
                    previous[neighbor] = vertex;
                    pq.decreaseKey(neighbor, alt);
                }
            }
        }
        for (PQNode<Integer, Integer> node : pq) {
            distance[node.getKey()] = node.getValue();
        }
        computed = true;
    }

    /**
     * Get the source vertex
     * 
     * @return the source vertex
     */
    public int getSource()
    {
        return source;
    }

    /**
     * Get the distance from the source to the given vertex.
     * 
     * @param vertex
     * @return the distance from source to vertex
     */
    public int getDistance(int vertex)
    {
        if (!computed) {
            compute();
        }
        return distance[vertex];
    }

    /**
     * Get the previous vertex on the path from the source to the given vertex.
     * 
     * @param vertex
     * @return the previous vertex on the path from the source to the given
     *         vertex
     */
    public int getPrevious(int vertex)
    {
        if (!computed) {
            compute();
        }
        return previous[vertex];
    }
}
