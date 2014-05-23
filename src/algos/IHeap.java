/*
 * IHeap.java
 */

package algos;

/**
 * A simple Heap interface
 */
public interface IHeap<E extends Comparable<E>>
{

    /**
     * Insert a new value.
     * 
     * @param value
     */
    public abstract void insert(E value);

    /**
     * Find the minimum value (aka peek).  Returns null if the Heap is empty.
     *
     * @return the minumum value
     */
    public abstract E findMin();

    /**
     * Remove the minimum value from the Heap and return it.
     * 
     * @return the minimum value in the Heap
     */
    public abstract E extractMin();

}