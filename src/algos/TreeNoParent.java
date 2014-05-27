/*
 * TreeNoParent.java
 */

package algos;


/**
 * A Binary Search Tree
 * 
 * One design decision we have to make when building a binary search tree
 * is how to represent a null tree.  Traditionally, you would define a node
 * to be something that has tree members: data, left, and right.  Then a
 * binary search tree is not a node, but a pointer to a node.  An empty tree
 * is a null pointer.
 * 
 * However, this means that operations like insert aren't members of the node,
 * since a null pointer can't have members.  Instead, they take the node as an
 * argument, which is not very object-oriented-like.  
 * 
 * Our solution is that when the data is null, that represents an empty tree.
 * 
 * Another possible solution would be to create two separate implementations
 * of IBSTree, one which is a "null tree" and one which is a "non-null tree".
 * They would have the same operations, but, for example, find on the null tree
 * would always return null.  The null tree would have no data members.
 * 
 * The problem with the implementation with two classes is, how do you insert?
 * When you insert data into a null tree, it becomes a non-null tree, and Java
 * has no mechanism for having something change its type.
 * 
 * One very elegant solution to that problem would be to do things functionally
 * and have insert return a new non-null tree.  However, note that a true functional
 * solution would make all methods non-destructive, so insert and delete on a
 * non-null tree would essentially return new copies of the tree, modified accordingly.
 * That's a great solution, but it's garbagy.  Also, I've decided that the
 * purpose of this exercise is not to implement the functional versions of things
 * but to implement something closer to the traditional algorithms.
 */
public class TreeNoParent<T extends Comparable<T>> implements IBSTree<T>
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
     * @see algos.IBSTree#getData()
     */
    @Override
    public T getData()
    {
        return data;
    }

    /**
     * @see algos.IBSTree#getLeft()
     */
    @Override
    public IBSTree<T> getLeft()
    {
        return left;
    }

    /**
     * @see algos.IBSTree#getRight()
     */
    @Override
    public IBSTree<T> getRight()
    {
        return right;
    }

    protected IBSTree<T> createNode(T _data)
    {
        return new TreeNoParent<T>(_data);
    }
    
    /**
     * @see algos.IBSTree#insert(T)
     */
    @Override
    public IBSTree<T> insert(T _data)
    {
        if (data == null) {
            data = _data;
            return this;
        }
        else {
            if (_data.compareTo(data) <= 0) {
                if (left == null) {
                    left = (TreeNoParent<T>)createNode(_data);
                    return left;
                }
                else {
                    return left.insert(_data);
                }
            }
            else {
                if (right == null) {
                    right = (TreeNoParent<T>)createNode(_data);
                    return right;
                }
                else {
                    return right.insert(_data);
                }
            }
        }
    }

    /**
     * @see algos.IBSTree#delete(T)
     */
    @Override
    public IBSTree<T> delete(T value)
    {
        TreeNoParent<T> parent = null;
        TreeNoParent<T> curr = this;
        for (int cmp = value.compareTo(curr.data); cmp != 0; cmp = value.compareTo(curr.data)) {
            if (cmp < 0) {
                if (curr.left == null) {
                    return null;
                }
                parent = curr;
                curr = curr.left;
            }
            else {
                if (curr.right == null) {
                    return null;
                }
                parent = curr;
                curr = curr.right;
            }
        }
        return curr.delete(parent);
    }

    /**
     * Delete a given node from the tree
     */
    protected IBSTree<T> delete(TreeNoParent<T> parent)
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
        return node;
    }

    /**
     * @see algos.IBSTree#find(T)
     */
    @Override
    public IBSTree<T> find(T value)
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
     * @see algos.IBSTree#toString()
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
