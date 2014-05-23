/*
 * PQNode.java
 */

package algos;

/**
 * A PriorityQueue Node is a single key-value pair
 */
public class PQNode<K, V extends Comparable<V>> implements
    Comparable<PQNode<K, V>>
{

    private final K key;
    private V value;

    /**
     * Constructs Node
     * 
     * @param key
     * @param value
     */
    public PQNode(K _key, V _value)
    {
        super();
        key = _key;
        value = _value;
    }

    /**
     * @return the value
     */
    public V getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(V _value)
    {
        value = _value;
    }

    /**
     * @return the key
     */
    public K getKey()
    {
        return key;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(PQNode<K, V> o)
    {
        return value.compareTo(o.getValue());
    }
}
