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
public class BSTree<T extends Comparable<T>> implements IBSTree<T>
{
    protected T          data  = null;
    protected IBSTree<T> left  = null;
    protected IBSTree<T> right = null;

    /**
     * Constructs an empty TreeNoParent
     */
    public BSTree()
    {
    }

    /**
     * Constructs a TreeNoParent with one item
     * 
     * @param _data
     */
    public BSTree(T _data)
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

    /**
     * Get the left subnode as a BSTree<T>
     */
    private BSTree<T> getTypedLeft()
    {
        return (BSTree<T>)left;
    }

    /**
     * Get the right subnode as a BSTree<T>
     */
    private BSTree<T> getTypedRight()
    {
        return (BSTree<T>)right;
    }

    protected IBSTree<T> createNode(T _data)
    {
        return new BSTree<T>(_data);
    }

    /**
     * @see algos.IBSTree#height()
     */
    @Override
    public int height()
    {
        if (data == null) {
            return 0;
        }
        int lh = (left == null) ? 0 : left.height();
        int rh = (right == null) ? 0 : right.height();
        return Math.max(lh, rh) + 1;
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
                    left = (BSTree<T>)createNode(_data);
                    return left;
                }
                else {
                    return left.insert(_data);
                }
            }
            else {
                if (right == null) {
                    right = (BSTree<T>)createNode(_data);
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
        BSTree<T> parent = null;
        BSTree<T> curr = this;
        for (int cmp = value.compareTo(curr.data); cmp != 0; cmp = value.compareTo(curr.data)) {
            if (cmp < 0) {
                if (curr.left == null) {
                    return null;
                }
                parent = curr;
                curr = curr.getTypedLeft();
            }
            else {
                if (curr.right == null) {
                    return null;
                }
                parent = curr;
                curr = curr.getTypedRight();
            }
        }
        return curr.delete(parent);
    }

    /**
     * Delete a given node from the tree
     */
    protected IBSTree<T> delete(BSTree<T> parent)
    {
        BSTree<T> node = this;
        if (node.left != null && node.right != null) {
            // Find either the successor or the predecessor.  In this case, we find the predecessor.
            parent = node;
            node = node.getTypedLeft();
            while (node.right != null) {
                parent = node;
                node = node.getTypedRight();
            }
            data = node.data;
        }

        BSTree<T> replace = null;
        // We are now at a node with zero or one child
        if (node.left == null && node.right != null) {
            replace = node.getTypedRight();
        }
        else if (node.right == null && node.left != null) {
            replace = node.getTypedLeft();
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

    /**
     * @see algos.IBSTree#rotateRight()
     */
    @Override
    public IBSTree<T> rotateRight()
    {
        if (left == null) {
            return this;
        }
        BSTree<T> pivot = getTypedLeft();
        left = pivot.getRight();
        pivot.right = this;
        return pivot;
    }

    /**
     * @see algos.IBSTree#rotateLeft()
     */
    @Override
    public IBSTree<T> rotateLeft()
    {
        if (right == null) {
            return this;
        }
        BSTree<T> pivot = getTypedRight();
        right = pivot.getLeft();
        pivot.left = this;
        return pivot;
    }
}
