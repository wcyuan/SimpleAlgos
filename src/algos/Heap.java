/*
 * Heap.java
 */

package algos;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * A simple MinHeap implementation backed by an array This Heap is a complete
 * binary tree (complete meaning that the values at one depth are all filled in
 * before moving to the next depth) with the property that a parent's value is
 * always less than its children's values.
 */
public class Heap<E extends Comparable<E>> implements IHeap<E>, Iterable<E>
{
    private final Object[] data;
    private int size = 0;

    /**
     * Constructs a Heap with the given capacity
     * 
     * @param capacity
     */
    public Heap(int capacity)
    {
        data = new Comparable[capacity];
    }

    /**
     * Constructs a Heap from a collection. This heap cannot be grown (you
     * cannot insert into it).
     * 
     * @param _data
     */
    @SuppressWarnings("unchecked")
    public Heap(Collection<? extends E> _data)
    {
        this((E[])_data.toArray(), 0);
    }

    /**
     * Constructs a Heap with the given capacity from a collection.
     * 
     * @param _data
     * @param capacity
     */
    @SuppressWarnings("unchecked")
    public Heap(Collection<? extends E> _data, int capacity)
    {
        this((E[])_data.toArray(), capacity);
    }

    /**
     * Constructs a Heap with the given capacity from an array.  This is faster
     * than inserting one element at a time into the Heap, because we wait until
     * the end to re-heapify.
     * 
     * @param _data
     * @param capacity
     */
    public Heap(E[] _data, int capacity)
    {
        capacity = Math.max(capacity, _data.length);
        data = new Comparable[capacity];
        int ii = 0;
        for (E e : _data) {
            data[ii++] = e;
        }
        size = data.length;

        for (ii = size / 2; ii >= 1; ii--) {
            bubbleDown(ii);
        }
    }

    final protected int left(int node)
    {
        return 2 * node;
    }

    final protected E lval(int node)
    {
        return getdata(left(node));
    }

    final protected int right(int node)
    {
        return 2 * node + 1;
    }

    final protected E rval(int node)
    {
        return getdata(right(node));
    }

    final protected int parent(int node)
    {
        return node / 2;
    }

    final protected E pval(int node)
    {
        return getdata(parent(node));
    }

    @SuppressWarnings("unchecked")
    final protected E getdata(int idx)
    {
        if (idx >= size) {
            return null;
        }
        return (E) data[idx];
    }

    /**
     * @return the current size of the Heap
     */
    public int size() {
        return size;
    }

    /**
     * @return the maximum capacity of the Heap
     */
    public int capacity() {
        return data.length;
    }

    /**
     * Throws an exception if we attempt to exceed the capacity.
     * 
     * @see algos.IHeap#insert(E)
     */
    @Override
    public void insert(E value)
    {
        int curr = size++;
        data[curr] = value;
        while (curr > 0 && value.compareTo(pval(curr)) < 0) {
            data[curr] = data[parent(curr)];
            data[parent(curr)] = value;
            curr = parent(curr);
        }
    }

    /**
     * @see algos.IHeap#findMin()
     */
    @Override
    public E findMin()
    {
        return getdata(0);
    }

    private void swap(int posa, int posb) {
        Object temp = data[posa];
        data[posa] = data[posb];
        data[posb] = temp;
    }

    /**
     * If the heap property has been violated by node "curr", meaning that curr
     * may be larger than one of its children, but all other nodes are smaller
     * than their children, then this function will swap the value of curr
     * down the heap until the heap property has been restored for the given
     * node and everything below it (it doesn't affect the nodes above this node)
     */
    protected void bubbleDown(int curr)
    {
        while (true) {
            int smallest = curr;
            if (left(curr) < size
                && lval(curr).compareTo(getdata(smallest)) < 0) {
                smallest = left(curr);
            }
            if (right(curr) < size
                && rval(curr).compareTo(getdata(smallest)) < 0) {
                smallest = right(curr);
            }
            if (smallest == curr) {
                return;
            }
            swap(smallest, curr);
            curr = smallest;
        }
    }

    /**
     * If the heap property has been violated by node "curr", meaning that curr
     * may be smaller than its parent, but all other nodes are larger
     * than their parent, then this function will swap the value of curr with
     * its parent up the heap until the heap property has been restored for the
     * given node and everything above it (it doesn't affect the nodes below this node)
     */
    protected void bubbleUp(int curr)
    {
        while (curr > 0 && pval(curr).compareTo(getdata(curr)) > 0) {
            int par = parent(curr);
            swap(par, curr);
            curr = par;
        }
    }

    /**
     * @see algos.IHeap#extractMin()
     */
    @Override
    public E extractMin()
    {
        E retval = getdata(0);
        if (size > 0) {
            data[0] = data[--size];
            bubbleDown(0);
        }
        return retval;
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<E> iterator()
    {
        return new Iterator<E>() {
            private int position = 0;

            @Override
            public boolean hasNext()
            {
                return position < size;
            }

            @Override
            public E next()
            {
                return getdata(position++);
            }
        };
    }

    @Override
    public String toString()
    {
        return Arrays.toString(data);
    }
}
