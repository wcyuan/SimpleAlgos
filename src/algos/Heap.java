/*
 * Heap.java
 */

package algos;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * A simple MinHeap implementation backed by an array
 * 
 * This Heap is a complete binary tree (complete meaning that the values at one
 * depth are all filled in before moving to the next depth) with the property
 * that a parent's value is always less than its children's values.
 */
public class Heap<E extends Comparable<E>> implements IHeap<E>
{
    private final Object[] data;
    private int size = 0;

    Heap(int capacity) {
        data = new Comparable[capacity];
    }
    Heap(Collection<? extends E>_data) {
        data = _data.toArray();
        size = data.length;
    }

    private int left(int node) {
        return 2 * node;
    }

    private E lval(int node) {
        return getdata(left(node));
    }
    
    private int right(int node) {
        return 2 * node + 1;
    }

    private E rval(int node) {
        return getdata(right(node));
    }

    private int parent(int node) {
        return node / 2;
    }

    private E pval(int node) {
        return getdata(parent(node));
    }

    @SuppressWarnings("unchecked")
    private E getdata(int idx) {
        if (idx >= data.length) {
            return null;
        }
        return (E)data[idx];
    }
    /**
     * @see algos.IHeap#insert(E)
     */
    @Override
    public void insert(E value) {
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
    public E findMin() {
        return getdata(0);
    }

    /**
     * If the heap property has been violated by node "curr", meaning that curr
     * may be larger than one of its children, but all other nodes are smaller
     * than their children, then this function will swap the value of curr
     * around until the heap property has been restored.  
     */
    private void heapify(int curr) {
        while (true) {
            int smallest = curr;
            if (left(curr) < size && lval(curr).compareTo(getdata(smallest)) < 0) {
                smallest = left(curr);
            }
            if (right(curr) < size && rval(curr).compareTo(getdata(smallest)) < 0) {
                smallest = right(curr);
            }
            if (smallest == curr) {
                return;
            }
            Object temp = data[smallest];
            data[smallest] = getdata(curr);
            data[curr] = temp;
            curr = smallest;
        }
    }

    /**
     * @see algos.IHeap#extractMin()
     */
    @Override
    public E extractMin() {
        E retval = getdata(0);
        data[0] = data[--size];
        heapify(0);
        return retval;
    }
}
