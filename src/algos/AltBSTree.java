/*
 * AltBSTree.java
 */

package algos;


/**
 * An alternative BS tree with parent.  This one is a little cleaner, perhaps,
 * because it separates out the node as a separate inner class.  Then, most of
 * the operations are static methods that take the Node as an argument, which
 * makes them much more like their traditional versions.
 * 
 * This is the approach that, say, java.util.TreeMap takes.
 * 
 * The disadvantages of this are that it's harder to have a class structure with
 * one class with no parent link and a subclass that adds a parent link, because
 * you'd also have to extend the Node class.
 * 
 * Also, it doesn't support methods to get the left/right/parent
 * nodes (because they can't just return Node<T> objects, they would have to wrap
 * them in new AVLTree objects, which is a bit weird)
 */
public class AltBSTree<T extends Comparable<T>>
{
    /**
     * A node of the tree
     */
    public static class Node<T extends Comparable<T>>
    {
        T       data;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        Node(T _data, Node<T> _parent)
        {
            data = _data;
            parent = _parent;
        }

        @Override
        public String toString()
        {
            return "[" + (left == null ? "" : left) + " " + data + " "
                   + (right == null ? "" : right) + "]";
        }
    }

    Node<T> root = null;

    /**
     * Constructs an empty AltBSTree
     */
    public AltBSTree()
    {
    }

    AltBSTree(T _data)
    {
        root = new Node<T>(_data, null);
    }

    private static <T extends Comparable<T>> Node<T> static_insert(Node<T> tree, T value)
    {
        if (tree == null) {
            return new Node<T>(value, tree);
        }
        int cmp = value.compareTo(tree.data);
        if (cmp > 0) {
            tree.right = static_insert(tree.right, value);
            tree.right.parent = tree;
            return tree;
        }
        else {
            tree.left = static_insert(tree.left, value);
            tree.left.parent = tree;
            return tree;
        }
    }

    /**
     * Insert a value into the tree
     * @param value
     */
    public void insert(T value)
    {
        root = static_insert(root, value);
    }

    private static <T extends Comparable<T>> T static_find(Node<T> tree, T value)
    {
        if (tree == null) {
            return null;
        }
        int cmp = value.compareTo(tree.data);
        if (cmp == 0) {
            return value;
        }
        else if (cmp > 0) {
            return static_find(tree.right, value);
        }
        else {
            return static_find(tree.left, value);
        }
    }

    /**
     * Insert a value into the tree
     * @param value
     */
    public T find(T value)
    {
        return static_find(root, value);
    }

    /**
     * @see algos.IBSTree#toString()
     */
    @Override
    public String toString()
    {
        if (root == null) {
            return "";
        }
        return root.toString();
    }

    /**
     * Find a value in the tree and delete it.
     * 
     * @param value
     * @return true if the value was found, false otherwise
     */
    public boolean delete(T value)
    {
        if (root == null) {
            return false;
        }
        Node<T> node = root;
        Node<T> parent = null;

        for (int cmp = value.compareTo(node.data); cmp != 0; cmp = value.compareTo(node.data)) {
            if (cmp < 0) {
                if (node.left == null) {
                    return false;
                }
                parent = node;
                node = node.left;
            }
            else {
                if (node.right == null) {
                    return false;
                }
                parent = node;
                node = node.right;
            }
        }

        if (node.left != null && node.right != null) {
            parent = node;
            Node<T> pred = node.left;
            while (pred.right != null) {
                parent = pred;
                pred = pred.right;
            }
            node.data = pred.data;
            node = pred;
        }
        Node<T> replace = null;
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
            if (replace != null) {
                replace.parent = parent;
            }
        }
        return true;
    }

    private static <T extends Comparable<T>> Node<T> rotateRight(Node<T> node)
    {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return null;
        }
        Node<T> pivot = node.left;
        node.left = pivot.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        pivot.right = node;
        pivot.parent = node.parent;
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = pivot;
            }
            else if (node.parent.right == node) {
                node.parent.right = pivot;
            }
        }
        pivot.right.parent = pivot;
        node = pivot;
        return node;
    }

    /**
     * Rotate right at the root
     */
    public void rotateRight()
    {
        if (root == null) {
            return;
        }
        if (root.left == null) {
            return;
        }
        root = rotateRight(root);
    }

    private static <T extends Comparable<T>> Node<T> rotateLeft(Node<T> node)
    {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return null;
        }
        Node<T> pivot = node.right;
        node.right = pivot.left;
        if (node.right != null) {
            node.right.parent = node;
        }
        pivot.left = node;
        pivot.parent = node.parent;
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = pivot;
            }
            else if (node.parent.right == node) {
                node.parent.right = pivot;
            }
        }
        pivot.left.parent = pivot;
        node = pivot;
        return node;
    }

    /**
     * Rotate left at the root
     */
    public void rotateLeft()
    {
        if (root == null) {
            return;
        }
        if (root.right == null) {
            return;
        }
        root = rotateLeft(root);
    }
}
