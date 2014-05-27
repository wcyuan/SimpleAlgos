/*
 * Tree.java
 */

package algos;


/**
 * A Binary Search Tree which has a parent pointer
 */
public class BSTreeWParent<T extends Comparable<T>> extends BSTree<T>
{
    protected BSTreeWParent<T> parent = null;

    /**
     * Constructs an empty Tree
     */
    public BSTreeWParent()
    {
    }

    /**
     * Constructs a Tree with one item
     * 
     * @param _data
     */
    public BSTreeWParent(T _data)
    {
        data = _data;
    }

    BSTreeWParent(T _data, BSTreeWParent<T> _parent)
    {
        this(_data);
        parent = _parent;
    }

    /**
     * @return the parent
     */
    public BSTreeWParent<T> getParent()
    {
        return parent;
    }

    /**
     * @return left as a Tree with a parent
     */
    public BSTreeWParent<T> getLeftWParent()
    {
        return (BSTreeWParent<T>)left;
    }

    /**
     * @return right as a Tree with a parent
     */
    public BSTreeWParent<T> getRightWParent()
    {
        return (BSTreeWParent<T>)right;
    }

    /**
     * @see algos.BSTree#createNode(java.lang.Comparable)
     */
    @Override
    protected IBSTree<T> createNode(T _data)
    {
        return new BSTreeWParent<T>(_data, this);
    }

    /**
     * Find as a tree with a parent.
     * @param _data
     * @return
     */
    public BSTreeWParent<T> findWParent(T _data)
    {
        return (BSTreeWParent<T>)find(_data);
    }

    /**
     * @see algos.BSTree#delete(algos.BSTree)
     */
    @Override
    protected IBSTree<T> delete(BSTree<T> _parent)
    {
        BSTreeWParent<T> node = (BSTreeWParent<T>)super.delete(_parent);
        BSTreeWParent<T> replace = null;
        // The deleted node had zero or one child.  If there was a child,
        // we replaced the deleted node with that child.  We need to
        // fix the child's parent pointer to point to the node's parent.
        if (node.getLeft() == null && node.getRight() != null) {
            replace = (BSTreeWParent<T>)node.getRight();
        }
        else if (node.getRight() == null && node.getLeft() != null) {
            replace = (BSTreeWParent<T>)node.getLeft();
        }
        if (replace != null) {
            replace.parent = node.parent;
        }
        return node;
    }

    /**
     * Return the value in the tree before this node's value.
     * @return
     */
    public BSTreeWParent<T> predecessor()
    {
        if (left == null) {
            BSTreeWParent<T> curr = this;
            while (curr.parent != null && parent.right != curr) {
                curr = curr.parent;
            }
            return curr.parent;
        }
        BSTreeWParent<T> pred = getLeftWParent();
        while (pred != null && pred.right != null) {
            pred = pred.getRightWParent();
        }
        return pred;
    }

    /**
     * Output an in-order traversal of a tree using O(1) space.
     * 
     * @param t: a binary tree with a parent pointer
     */
    public static <T extends Comparable<T>> String inorder(BSTreeWParent<T> t)
    {
        boolean fromleft = false;
        boolean fromright = false;
        StringBuilder sb = new StringBuilder();
        while (true) {
            while (!fromleft && !fromright && t.left != null) {
                t = t.getLeftWParent();
            }
            if (!fromright && t.data != null) {
                sb.append(t.data.toString());
            }
            if (!fromright && t.right != null) {
                t = t.getRightWParent();
                fromleft = false;
                fromright = false;
            }
            else if (t.parent == null) {
                break;
            }
            else {
                if (t == t.parent.left) {
                    fromleft = true;
                    fromright = false;
                }
                else {
                    fromleft = false;
                    fromright = true;
                }
                t = t.parent;
            }
        }
        return sb.toString();
    }

    /**
     * A string version of a Tree
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        if (data == null) {
            return "";
        }
        return "[" + (left == null ? "" : left) + " " + data + " " + (right == null ? "" : right)
               + "]";
    }
}
