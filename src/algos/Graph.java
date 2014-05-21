/*
 * Graph.java
 */

package algos;


import java.util.Iterator;

/**
 * A representation of a directed graph as an adjacency list.
 * Vertices are numbered from 0 to V-1.
 * Edges are represented as an array (one entry for each vertex) of singly
 * linked lists (one element in the list for each edge out of that vertex).
 * Edges may have weights.
 */
public class Graph implements Iterable<Integer>
{
    private static class Edge
    {
        public final int target;
        public int       weight;

        public Edge(int _target, int _weight)
        {
            target = _target;
            weight = _weight;
        }

        @Override
        public String toString()
        {
            return "" + target + " (" + weight + ")";
        }
    }

    private final LinkedList<Edge>[] myEdges;
    private int                      myNumVertices;

    /**
     * Constructs Graph
     * @param numVertices
     * @param maxNumVertices
     */
    @SuppressWarnings("unchecked")
    public Graph(int numVertices, int maxNumVertices)
    {
        myEdges = new LinkedList[Math.max(numVertices, maxNumVertices)];
        for (int ii = 0; ii < myEdges.length; ii++) {
            myEdges[ii] = new LinkedList<Edge>();
        }
        myNumVertices = numVertices;
    }

    /**
     * Constructs a Graph where you start out with the maximum number of vertices.
     * 
     * @param numVertices
     */
    public Graph(int numVertices)
    {
        this(numVertices, numVertices);
    }

    /**
     * Return the number of vertices in the graph.
     */
    public int getNumVertices()
    {
        return myNumVertices;
    }

    /**
     * Set the number of vertices
     * @param numVertices
     */
    public void setNumVertices(int numVertices)
    {
        if (numVertices > myEdges.length) {
            throw new IllegalArgumentException("Max vertex for this graph is " + myEdges.length
                                               + ", can't hold " + numVertices + " vertices.");
        }
        // Remove edges out of the removed nodes.
        for (int ii = numVertices; ii < myNumVertices; ii++) {
            while (myEdges[ii].size() > 0) {
                myEdges[ii].pop();
            }
        }
        // Remove edges going to the removed nodes.
        for (int ii = 0; ii < numVertices; ii++) {
            Iterator<Edge> itr = myEdges[ii].iterator();
            while (itr.hasNext()) {
                if (itr.next().target >= numVertices) {
                    itr.remove();
                }
            }
        }
        myNumVertices = numVertices;
    }

    /**
     * Add a directed edge to the graph with the given weight.  If the edge already
     * exists, we just update the weight.  Throws an IllegalArgumentException 
     * if the vertices are invalid.
     *
     * @param start
     * @param end
     * @param weight
     */
    public void addEdge(int start, int end, int weight)
    {
        if (start < 0 || start >= myNumVertices || end < 0 || end >= myNumVertices) {
            throw new IllegalArgumentException(
                "Illegal vertex for this graph, must be between 0 and " + myEdges.length
                                + ".  Start: " + start + " End: " + end);
        }
        for (Edge e : myEdges[start]) {
            if (e.target == end) {
                e.weight = weight;
                return;
            }
        }
        myEdges[start].insert(new Edge(end, weight));
    }

    /**
     * Add an undirected edge to the graph -- equivalent to adding two directional
     * edges, one in each direction.
     * 
     * @param start
     * @param end
     * @param weight
     */
    public void addBidirectionalEdge(int start, int end, int weight)
    {
        addEdge(start, end, weight);
        addEdge(end, start, weight);
    }

    /**
     * Remove an edge.  Has no effect if the edge doesn't already exist.  Throws
     * an IllegalArgumentException if the vertices are invalid.
     * 
     * @param start
     * @param end
     */
    public void removeEdge(int start, int end)
    {
        if (start < 0 || start >= myNumVertices || end < 0 || end >= myNumVertices) {
            throw new IllegalArgumentException(
                "Illegal vertex for this graph, must be between 0 and " + myEdges.length
                                + ".  Start: " + start + " End: " + end);
        }
        Iterator<Edge> itr = myEdges[start].iterator();
        while (itr.hasNext()) {
            if (itr.next().target == end) {
                itr.remove();
                return;
            }
        }
    }

    /**
     * Get the weight for an edge.  Returns 0 if the edge doesn't exist.  Throws
     * an IllegalArgumentException if the vertices are invalid.
     * @param start
     * @param end
     * @return
     */
    public int getWeight(int start, int end)
    {
        if (start < 0 || start >= myNumVertices || end < 0 || end >= myNumVertices) {
            throw new IllegalArgumentException(
                "Illegal vertex for this graph, must be between 0 and " + myNumVertices
                                + ".  Start: " + start + " End: " + end);
        }
        for (Edge e : myEdges[start]) {
            if (e.target == end) {
                return e.weight;
            }
        }
        return 0;
    }

    /**
     * Get a list of edges leaving the given node.
     *
     * @param start
     * @return
     */
    public Iterable<Edge> getNeighbors(final int start)
    {
        if (start < 0 || start >= myNumVertices) {
            throw new IllegalArgumentException(
                "Illegal vertex for this graph, must be between 0 and " + myNumVertices + ", not: "
                                + start);
        }

        return new Iterable<Edge>() {
            @Override
            public Iterator<Edge> iterator()
            {
                return myEdges[start].iterator();
            }
        };
    }

    /**
     * Iterate over all vertices.
     * This is shorthand for "for (int ii = 0; ii < size(); ii++)"
     */
    @Override
    public Iterator<Integer> iterator()
    {
        return new Iterator<Integer>()
        {
            private int ii = 0;

            @Override
            public boolean hasNext()
            {
                return ii < myNumVertices;
            }

            @Override
            public Integer next()
            {
                return ii++;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Write the graph to a string.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("{\n");
        for (int vertex : this) {
            sb.append(" ");
            sb.append(vertex);
            sb.append(": ");
            sb.append(myEdges[vertex]);
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
