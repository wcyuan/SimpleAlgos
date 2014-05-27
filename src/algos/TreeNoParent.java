/*
 * TreeNoParent.java
 */

package algos;


/**
 * A Binary Search Tree
 */
public class TreeNoParent<T extends Comparable<T>>
{
    protected T               data  = null;
    protected TreeNoParent<T> left  = null;
    protected TreeNoParent<T> right = null;

    /**
     * Constructs an empty TreeNoParent
     */
    public TreeNoParent()
    {
    }

    /**
     * Constructs a TreeNoParent with one item
     * 
     * @param _data
     */
    public TreeNoParent(T _data)
    {
        data = _data;
    }

    /**
     * @return the data
     */
    public T getData()
    {
        return data;
    }

    /**
     * @return the left
     */
    public TreeNoParent<T> getLeft()
    {
        return left;
    }

    /**
     * @return the right
     */
    public TreeNoParent<T> getRight()
    {
        return right;
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
                    left = new TreeNoParent<T>(_data);
                }
                else {
                    left.insert(_data);
                }
            }
            else {
                if (right == null) {
                    right = new TreeNoParent<T>(_data);
                }
                else {
                    right.insert(_data);
                }
            }
        }
    }

    /**
     * Find a value in the tree and delete it.
     * 
     * @param _data
     */
    public void delete(T value)
    {
        TreeNoParent<T> parent = null;
        TreeNoParent<T> curr = this;
        for (int cmp = value.compareTo(curr.data); cmp != 0; cmp = value.compareTo(curr.data)) {
            if (cmp < 0) {
                if (curr.left == null) {
                    return;
                }
                parent = curr;
                curr = curr.left;
            }
            else {
                if (curr.right == null) {
                    return;
                }
                parent = curr;
                curr = curr.right;
            }
        }
        curr.delete(parent);
    }

    /**
     * Delete a given node from the tree
     */
    protected void delete(TreeNoParent<T> parent)
    {
        TreeNoParent<T> node = this;
        if (node.left != null && node.right != null) {
            // Find either the successor or the predecessor.  In this case, we find the predecessor.
            parent = node;
            node = node.left;
            while (node.right != null) {
                parent = node;
                node = node.right;
            }
            data = node.data;
        }

        TreeNoParent<T> replace = null;
        // We are now at a node with zero or one child
        if (node.left == null && node.right != null) {
            replace = node.right;
        }
        else if (node.right == null && node.left != null) {
            replace = node.left;
        }
        if (parent != null) {
            if (node == parent.left) {
                parent.left = replace;
            }
            else if (node == parent.right) {
                parent.right = replace;
            }
        }
        node.data = null;
    }

    /**
     * Return the subtree whose root's data equals value
     * 
     * @param value
     * @return
     */
    public TreeNoParent<T> find(T value)
    {
        if (data == null) {
            return null;
        }
        int cmp = value.compareTo(data);
        if (cmp == 0) {
            return this;
        }
        else if (cmp < 0) {
            if (left == null) {
                return null;
            }
            return left.find(value);
        }
        else {
            if (right == null) {
                return null;
            }
            return right.find(value);
        }
    }

    /**
     * A string version of a TreeNoParent
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
