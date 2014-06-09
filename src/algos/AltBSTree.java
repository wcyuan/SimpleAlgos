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

        private int getLeftHeight()
        {
            return left == null ? 0 : left.height;
        }

        private int getRightHeight()
        {
            return right == null ? 0 : right.height;
        }

        /**
         * Update this node's height based on the heights of the left and right subtrees.
         * This doesn't recompute the heights of the subtrees, it is assumed that
         * the heights of the subtrees are correct already.
         * @return
         */
        public int updateHeight()
        {
            int newheight = Math.max(getLeftHeight(), getRightHeight()) + 1;
            if (height != newheight) {
                height = newheight;
                if (parent != null) {
                    parent.updateHeight();
                }
            }
            return height;
        }

        /**
         * Set the left branch of this node.  Also updates height and parent.
         *
         * @param _left
         * @return
         */
        public Node<T> setLeft(Node<T> _left)
        {
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
        public Node<T> setRight(Node<T> _right)
        {
            right = _right;
            if (right != null) {
                right.parent = this;
            }
            updateHeight();
            return right;
        }

        /**
         * Compute the balance factor = left height - right height
         * @return
         */
        public int balanceFactor()
        {
            return getLeftHeight() - getRightHeight();
        }
    }

    protected Node<T> root = null;

    /**
     * Constructs an empty AltBSTree
     */
    public AltBSTree()
    {
    }

    /**
     * Constructs a balanced AltBSTree from an array, which is assumed to be
     * sorted.  This can also be used to create invalid binary-search trees
     */
    public AltBSTree(T[] arr)
    {
        root = fromArr(arr, 0, arr.length-1);
    }

    private Node<T> fromArr(T[] arr, int start, int end)
    {
        if (end < start) {
            return null;
        }
        int mid = start + (end - start) / 2;
        Node<T> node = new Node<T>(arr[mid]);
        node.setLeft(fromArr(arr, start, mid-1));
        node.setRight(fromArr(arr, mid+1, end));
        return node;
    }

    AltBSTree(T _data)
    {
        root = new Node<T>(_data);
    }

    /**
     * Insert a value into the tree
     * @param value
     */
    public Node<T> insert(T value)
    {
        if (root == null) {
            root = new Node<T>(value);
            return root;
        }
        Node<T> tree = root;
        while (true) {
            int cmp = value.compareTo(tree.data);
            if (cmp > 0) {
                if (tree.right == null) {
                    tree.setRight(new Node<T>(value));
                    return tree.right;
                }
                else {
                    tree = tree.right;
                }
            }
            else {
                if (tree.left == null) {
                    tree.setLeft(new Node<T>(value));
                    return tree.left;
                }
                else {
                    tree = tree.left;
                }
            }
        }
    }

    /**
     * Find a value in the tree
     * 
     * @param value
     */
    public T find(T value)
    {
        Node<T> node = findNode(value);
        if (node == null) {
            return null;
        }
        else {
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
    public Node<T> delete(T value)
    {
        // Find the node to be removed
        if (root == null) {
            return null;
        }
        Node<T> node = findNode(value);
        if (node == null) {
            return null;
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
        return parent;
    }

    protected static <T extends Comparable<T>> Node<T> rotateRight(Node<T> node)
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
            }
            else {
                parent.setRight(pivot);
            }
        }
        else {
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

    protected static <T extends Comparable<T>> Node<T> rotateLeft(Node<T> node)
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
            }
            else {
                parent.setRight(pivot);
            }
        }
        else {
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

    /**
     * Return a breadth-first search
     * @return
     */
    public String BFS()
    {
        StringBuilder sb = new StringBuilder();
        DoublyLinkedList<Node<T>> list = new DoublyLinkedList<Node<T>>();
        if (root != null) {
            list.insertHead(root);
        }
        while (list.size() > 0) {
            Node<T> node = list.pop();
            if (node != root) {
                sb.append(", ");
            }
            sb.append(node.data);
            if (node.left != null) {
                list.insertTail(node.left);
            }
            if (node.right != null) {
                list.insertTail(node.right);
            }
        }
        return sb.toString();
    }

    /**
     * True iff the Binary Search Tree is well-formed (each nodes' value is
     * larger than everything on its left and smaller than everything on its
     * right)
     * @return
     */
    public boolean isBST()
    {
        return isBST(root);
    }

    private boolean isBST(Node<T> node)
    {
        if (node == null) {
            return true;
        }
        if (node.left != null) {
            if (!isBSTMax(node.left, node.data)) {
                return false;
            }
        }
        if (node.right != null) {
            if (!isBSTMin(node.right, node.data)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBSTMin(Node<T> node, T min)
    {
        if (node.data.compareTo(min) < 0) {
            return false;
        }
        if (node.left != null) {
            if (!isBSTMinMax(node.left, min, node.data)) {
                return false;
            }
        }
        if (node.right != null) {
            if (!isBSTMin(node.right, node.data)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBSTMax(Node<T> node, T max)
    {
        if (node.data.compareTo(max) > 0) {
            return false;
        }
        if (node.left != null) {
            if (!isBSTMax(node.left, node.data)) {
                return false;
            }
        }
        if (node.right != null) {
            if (!isBSTMinMax(node.right, node.data, max)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBSTMinMax(Node<T> node, T min, T max)
    {
        if (node.data.compareTo(min) < 0) {
            return false;
        }
        if (node.data.compareTo(max) > 0) {
            return false;
        }
        if (node.left != null) {
            if (!isBSTMinMax(node.left, min, node.data)) {
                return false;
            }
        }
        if (node.right != null) {
            if (!isBSTMinMax(node.right, node.data, max)) {
                return false;
            }
        }
        return true;
    }
}
