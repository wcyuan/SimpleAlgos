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

    private void rebalance(Node<T> node)
    {
        for ( ; node != null; node = node.parent) {
            if (node.balanceFactor() == 2) {
                if (node.left.balanceFactor() == -1) {
                    rotateLeft(node.left);
                }
                node = rotateRight(node);
            }
            else if (node.balanceFactor() == -2) {
                if (node.right.balanceFactor() == 1) {
                    rotateRight(node.right);
                }
                node = rotateLeft(node);
            }
            if (node.parent == null) {
                root = node;
            }
        }
    }

    /**
     * @see algos.AltBSTree#insert(java.lang.Comparable)
     */
    @Override
    public Node<T> insert(T value)
    {
        Node<T> newnode = super.insert(value);
        rebalance(newnode);
        return newnode;
    }

    /**
     * @see algos.AltBSTree#delete(java.lang.Comparable)
     */
    @Override
    public Node<T> delete(T value)
    {
        Node<T> parent = super.delete(value);
        rebalance(parent);
        return parent;
    }

    
}
