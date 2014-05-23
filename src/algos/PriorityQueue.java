/*
 * PriorityQueue.java
 */

package algos;

/**
 * A PriorityQueue has a key and a value, and uses a heap to easily find the key
 * with the lowest value. The way I've implemented the Heap and PriorityQueue,
 * the main difference is that the Heap works on any comparable objects, and the
 * Priority explicitly knows about keys and values.
 */
public class PriorityQueue<K, V extends Comparable<V>> extends Heap<PQNode<K, V>>
{
    /**
     * Constructs a PriorityQueue with the given capacity
     */
    public PriorityQueue(int capacity)
    {
        super(capacity);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static PQNode[] makeArray(Object[] keys, Comparable[] values) {
        if (keys.length != values.length) {
            throw new IllegalArgumentException("Keys (" + keys.length
                + ") and values (" + values.length + ") have different length");
        }
        PQNode[] arr = new PQNode[keys.length];
        for (int ii = 0; ii < keys.length; ii++) {
            arr[ii] = new PQNode(keys[ii], values[ii]);
        }
        return arr;
    }

    /**
     * Constructs a PriorityQueue with the given keys, values, and capacity.
     * If the key and value arrays don't have the same length, we throw an error
     * 
     * @param keys - an array of keys
     * @param values - an array of values
     * @throws IllegalArgumentException if keys and values have different length
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(K[] keys, V[] values, int capacity)
    {
        super((PQNode<K, V>[])makeArray(keys, values), capacity);
    }

    /**
     * Constructs a PriorityQueue with the given keys/values.  If the key and
     * value arrays don't have the same length, we throw an error.  The capacity
     * of the PriorityQueue will be the same as the length of the given arrays
     * you won't be allowed to insert into the queue until you've removed
     * elements.
     * 
     * @param keys - an array of keys
     * @param values - an array of values
     * @throws IllegalArgumentException if keys and values have different length
     */
    public PriorityQueue(K[] keys, V[] values)
    {
        this(keys, values, 0);
    }

    /**
     * Insert a new node into the queue
     * 
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        insert(new PQNode<K, V>(key, value));
    }
    
    /**
     * Find the key with the minimum value.
     * 
     * @return key with minimum value
     */
    public K findMinKey() {
        return findMin().getKey();
    }

    /**
     * Find the minimum value.
     * 
     * @return minimum value
     */
    public V findMinValue() {
        return findMin().getValue();
    }

    /**
     * Decrease the value of a particular key, and reheapify.
     * 
     * @param key
     * @param value
     */
    public void decreaseKey(K key, V value) {
        for (int ii = 0; ii < size(); ii++) {
            PQNode<K, V> node = getdata(ii);
            if (node.getKey() == key) {
                int cmp = value.compareTo(node.getValue());
                if (cmp < 0) {
                    node.setValue(value);
                    bubbleUp(ii);
                } else if (cmp > 0) {
                    node.setValue(value);
                    bubbleDown(ii);
                }
                return;
            }
        }
    }
}
