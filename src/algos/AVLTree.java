/*
 * AVLTree.java
 */

package algos;

/**
 *
 */
public class AVLTree<T extends Comparable<T>> extends AltBSTree<T>
{

    /**
     * Constructs AVLTree
     */
    public AVLTree()
    {
    }

    /**
     * Constructs AVLTree
     * @param _data
     */
    public AVLTree(T _data)
    {
        super(_data);
    }

    /**
     * @see algos.AltBSTree#insert(java.lang.Comparable)
     */
    @Override
    public void insert(T value)
    {
        super.insert(value);
    }

    /**
     * @see algos.AltBSTree#delete(java.lang.Comparable)
     */
    @Override
    public boolean delete(T value)
    {
        return super.delete(value);
    }

    
}
