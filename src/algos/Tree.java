/*
 * Tree.java
 */

package algos;


/**
 * A Binary Search Tree which has a parent pointer
 */
public class Tree<T extends Comparable<T>>
{
    protected T       data   = null;
    protected Tree<T> left   = null;
    protected Tree<T> right  = null;
    protected Tree<T> parent = null;

    /**
     * Constructs an empty Tree
     */
    public Tree()
    {
    }

    /**
     * Constructs a Tree with one item
     * 
     * @param _data
     */
    public Tree(T _data)
    {
        data = _data;
    }

    Tree(T _data, Tree<T> _parent)
    {
        this(_data);
        parent = _parent;
    }

    /**
     * @param data
     */
    public void insert(T _data)
    {
        if (data == null) {
            data = _data;
        }
        else {
            if (_data.compareTo(data) <= 0) {
                if (left == null) {
                    left = new Tree<T>(_data, this);
                }
                else {
                    left.insert(_data);
                }
            }
            else {
                if (right == null) {
                    right = new Tree<T>(_data, this);
                }
                else {
                    right.insert(_data);
                }
            }
        }
    }

    /**
     * Output an in-order traversal of a tree using O(1) space.
     * 
     * @param t: a binary tree with a parent pointer
     */
    public static <T extends Comparable<T>> String inorder(Tree<T> t)
    {
        boolean fromleft = false;
        boolean fromright = false;
        StringBuilder sb = new StringBuilder();
        while (true) {
            while (!fromleft && !fromright && t.left != null) {
                t = t.left;
            }
            if (!fromright && t.data != null) {
                sb.append(t.data.toString());
            }
            if (!fromright && t.right != null) {
                t = t.right;
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
