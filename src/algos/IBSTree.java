/*
 * BSTree.java
 */

package algos;


/**
 *
 */
public interface IBSTree<T extends Comparable<T>>
{

    /**
     * @return the data
     */
    public abstract T getData();

    /**
     * @return the left
     */
    public abstract IBSTree<T> getLeft();

    /**
     * @return the right
     */
    public abstract IBSTree<T> getRight();

    /**
     * @param data
     */
    public abstract IBSTree<T> insert(T _data);

    /**
     * Find a value in the tree and delete it.
     * 
     * @param _data
     */
    public abstract IBSTree<T> delete(T value);

    /**
     * Return the subtree whose root's data equals value
     * Returns null if the value isn't found in the tree.
     * 
     * @param value
     * @return
     */
    public abstract IBSTree<T> find(T value);

    /**
     * Rotate the tree right (clockwise).  The left node
     * becomes the new root of the tree.  If the left node is null,
     * this method has no effect
     * 
     * @returns the root of the new tree
     */
    IBSTree<T> rotateRight();

    /**
     * Rotate the tree left (counter clockwise).  The right node becomes
     * the new root of the tree.  If the rght node is null, this method
     * has no effect
     * 
     * @returns the root of the new tree
     */
    IBSTree<T> rotateLeft();
}
