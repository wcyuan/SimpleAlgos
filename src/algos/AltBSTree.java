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
        int     height = 1;

        Node(T _data)
        {
            data = _data;
        }

        @Override
        public String toString()
        {
            return "[" + (left == null ? "" : left) + " " + data + " "
                   + (right == null ? "" : right) + "]";
        }

        /**
         * Update this node's height based on the heights of the left and right subtrees.
         * This doesn't recompute the heights of the subtrees, it is assumed that
         * the heights of the subtrees are correct already.
         * @return
         */
        public int updateHeight() {
            int leftHeight = left == null ? 0 : left.height;
            int rightHeight = right == null ? 0 : right.height;
            height = Math.max(leftHeight, rightHeight) + 1;
            return height;
        }

        /**
         * Set the left branch of this node.  Also updates height and parent.
         *
         * @param _left
         * @return
         */
        public Node<T> setLeft(Node<T> _left) {
            left = _left;
            if (left != null) {
                left.parent = this;
            }
            updateHeight();
            return left;
        }

        /**
         * Set the right branch of this node.  Also updates height and parent.
         *
         * @param _right
         * @return
         */
        public Node<T> setRight(Node<T> _right) {
            right = _right;
            if (right != null) {
                right.parent = this;
            }
            updateHeight();
            return right;
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
        root = new Node<T>(_data);
    }

    /**
     * Insert a value into the tree
     * @param value
     */
    public void insert(T value)
    {
        if (root == null) {
            root = new Node<T>(value);
            return;
        }
        Node<T> tree = root;
        while (true) {
            int cmp = value.compareTo(tree.data);
            if (cmp > 0) {
                if (tree.right == null) {
                    tree.setRight(new Node<T>(value));
                    break;
                } else {
                    tree = tree.right;
                }
            } else {
                if (tree.left == null) {
                    tree.setLeft(new Node<T>(value));
                    break;
                } else {
                    tree = tree.left;
                }
            }
        }
    }

    /**
     * Find a value in the tree
     * @param value
     */
    public T find(T value)
    {
        Node<T> node = findNode(value);
        if (node == null) {
            return null;
        } else {
            return node.data;
        }
    }

    private Node<T> findNode(T value)
    {
        Node<T> tree = root;
        while (true) {
            if (tree == null) {
                return null;
            }
            int cmp = value.compareTo(tree.data);
            if (cmp == 0) {
                return tree;
            }
            else if (cmp > 0) {
                tree = tree.right;
            }
            else {
                tree = tree.left;
            }
        }
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
        // Find the node to be removed
        if (root == null) {
            return false;
        }
        Node<T> node = findNode(value);
        if (node == null) {
            return false;
        }
        Node<T> parent = node.parent;

        // If the node has both subtrees, replace the value with the value of
        // the predecessor, then remove the predecessor
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

        // We are now at a node with zero or one child
        Node<T> replace = null;
        if (node.left == null && node.right != null) {
            replace = node.right;
        }
        else if (node.right == null && node.left != null) {
            replace = node.left;
        }
        if (parent != null) {
            if (node == parent.left) {
                parent.setLeft(replace);
            }
            else if (node == parent.right) {
                parent.setRight(replace);
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
        Node<T> parent = node.parent;
        boolean isLeft = (parent != null && parent.left == node);
        Node<T> pivot = node.left;
        node.setLeft(pivot.right);
        pivot.setRight(node);

        pivot.parent = node.parent;
        if (parent != null) {
            if (isLeft) {
                parent.setLeft(pivot);
            } else {
                parent.setRight(pivot);
            }
        } else {
            pivot.parent = parent;
        }
        return pivot;
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
        Node<T> parent = node.parent;
        boolean isLeft = (parent != null && parent.left == node);
        Node<T> pivot = node.right;
        node.setRight(pivot.left);
        pivot.setLeft(node);
        if (parent != null) {
            if (isLeft) {
                parent.setLeft(pivot);
            } else {
                parent.setRight(pivot);
            }
        } else {
            pivot.parent = parent;
        }
        return pivot;
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
