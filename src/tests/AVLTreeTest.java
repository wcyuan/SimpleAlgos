/*
 * AVLTreeTest.java
 */

package tests;


import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import algos.AVLTree;

/**
 *
 */
public class AVLTreeTest
{

    /**
     * Basic tests of Trees
     */
    @Test
    public void test()
    {
        AVLTree<Integer> t = new AVLTree<Integer>();
        assertEquals("", t.toString());
        t.insert(4);
        assertEquals("[ 4 ]", t.toString());
        t.insert(3);
        assertEquals("[[ 3 ] 4 ]", t.toString());
        t.insert(5);
        assertEquals("[[ 3 ] 4 [ 5 ]]", t.toString());
        t.insert(2);
        assertEquals("[[[ 2 ] 3 ] 4 [ 5 ]]", t.toString());
        assertEquals(3, (int)t.find(3));
        assertNull(t.find(20));
        assertEquals("[[[ 2 ] 3 ] 4 [ 5 ]]", t.toString());
    }

    private AVLTree<Integer> makeBalanced() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        AVLTree<Integer> t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(3);
        assertEquals("[[ 3 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(7);
        assertEquals("[[ 3 ] 5 [ 7 ]]", t.toString());
        testHeight(t);
        t.insert(4);
        assertEquals("[[ 3 [ 4 ]] 5 [ 7 ]]", t.toString());
        testHeight(t);
        t.insert(2);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 [ 7 ]]", t.toString());
        testHeight(t);
        t.insert(6);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 [[ 6 ] 7 ]]", t.toString());
        testHeight(t);
        t.insert(8);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testHeight(t);
        return t;
    }

    /**
     * Test deleting
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Test
    public void testDelete() throws NoSuchFieldException, SecurityException,
                    IllegalArgumentException, IllegalAccessException
    {
        AVLTree<Integer> t = makeBalanced();
        assertNotNull(t.delete(2));
        assertEquals("[[ 3 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        assertNotNull(t.delete(3));
        assertEquals("[[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        testHeight(t);
        t = makeBalanced();
        assertNotNull(t.delete(3));
        assertEquals("[[ 2 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        testHeight(t);
        t = makeBalanced();
        assertNotNull(t.delete(5));
        assertEquals("[[[ 2 ] 3 ] 4 [[ 6 ] 7 [ 8 ]]]", t.toString());
        assertNull(t.delete(10));
        testParentLinks(t);
        testHeight(t);
    }

    /**
     * Test rotating
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Test
    public void testRotation() throws NoSuchFieldException, SecurityException,
                    IllegalArgumentException, IllegalAccessException
    {
        AVLTree<Integer> t = makeBalanced();
        testParentLinks(t);
        testHeight(t);
        t.rotateLeft();
        testParentLinks(t);
        assertEquals("[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 [ 8 ]]", t.toString());
        testHeight(t);
        t.rotateLeft();
        assertEquals("[[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 ] 8 ]", t.toString());
        testParentLinks(t);
        testHeight(t);
        t.rotateLeft();
        assertEquals("[[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 ] 8 ]", t.toString());
        testParentLinks(t);
        testHeight(t);
        t = makeBalanced();
        t.rotateRight();
        assertEquals("[[ 2 ] 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]", t.toString());
        testParentLinks(t);
        testHeight(t);
        t.rotateRight();
        assertEquals("[ 2 [ 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]]", t.toString());
        testParentLinks(t);
        testHeight(t);
        t.rotateRight();
        assertEquals("[ 2 [ 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]]", t.toString());
        testParentLinks(t);
        testHeight(t);
    }

    /**
     * testParentLinks uses reflection to access private members in order to have
     * a more complete test without having to break encapsulation in other places.
     * 
     * @param tree
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void testParentLinks(AVLTree<T> tree)
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        Field root = tree.getClass().getSuperclass().getDeclaredField("root");
        root.setAccessible(true);
        testParentLinks((AVLTree.Node<T>)root.get(tree));
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void testParentLinks(AVLTree.Node<T> node)
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        Field left = node.getClass().getDeclaredField("left");
        left.setAccessible(true);
        Field right = node.getClass().getDeclaredField("right");
        right.setAccessible(true);
        Field parent = node.getClass().getDeclaredField("parent");
        parent.setAccessible(true);
        if (left.get(node) != null) {
            assertEquals(node, parent.get(left.get(node)));
            testParentLinks((AVLTree.Node<T>)left.get(node));
        }
        if (right.get(node) != null) {
            assertEquals(node, parent.get(right.get(node)));
            testParentLinks((AVLTree.Node<T>)right.get(node));
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void testHeight(AVLTree<T> tree)
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        Field root = tree.getClass().getSuperclass().getDeclaredField("root");
        root.setAccessible(true);
        testHeight((AVLTree.Node<T>)root.get(tree));
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void testHeight(AVLTree.Node<T> node)
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        Field height = node.getClass().getDeclaredField("height");
        height.setAccessible(true);
        Field left = node.getClass().getDeclaredField("left");
        left.setAccessible(true);
        Field right = node.getClass().getDeclaredField("right");
        right.setAccessible(true);
        int lheight = 0;
        if (left.get(node) != null) {
            lheight = (int)height.get(left.get(node));
            testHeight((AVLTree.Node<T>)left.get(node));
        }
        int rheight = 0;
        if (right.get(node) != null) {
            rheight = (int)height.get(right.get(node));
            testHeight((AVLTree.Node<T>)right.get(node));
        }
        assertEquals(Math.max(lheight, rheight) + 1, height.get(node));
    }

    /**
     * Test auto-balancing
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testBalance() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        AVLTree<Integer> t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(3);
        assertEquals("[[ 3 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(2);
        assertEquals("[[ 2 ] 3 [ 5 ]]", t.toString());
        testHeight(t);
        
        t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(3);
        assertEquals("[[ 3 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(4);
        assertEquals("[[ 3 ] 4 [ 5 ]]", t.toString());
        testHeight(t);

        t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(7);
        assertEquals("[ 5 [ 7 ]]", t.toString());
        testHeight(t);
        t.insert(8);
        assertEquals("[[ 5 ] 7 [ 8 ]]", t.toString());
        testHeight(t);

        t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(7);
        assertEquals("[ 5 [ 7 ]]", t.toString());
        testHeight(t);
        t.insert(6);
        assertEquals("[[ 5 ] 6 [ 7 ]]", t.toString());
        testHeight(t);
 
        t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(2);
        assertEquals("[[ 2 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(8);
        assertEquals("[[ 2 ] 5 [ 8 ]]", t.toString());
        testHeight(t);
        t.insert(4);
        assertEquals("[[ 2 [ 4 ]] 5 [ 8 ]]", t.toString());
        testHeight(t);
        t.insert(3);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 [ 8 ]]", t.toString());
        testHeight(t);
    
        t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(2);
        assertEquals("[[ 2 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(8);
        assertEquals("[[ 2 ] 5 [ 8 ]]", t.toString());
        testHeight(t);
        t.insert(3);
        assertEquals("[[ 2 [ 3 ]] 5 [ 8 ]]", t.toString());
        testHeight(t);
        t.insert(4);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 [ 8 ]]", t.toString());
        testHeight(t);
    
        t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(2);
        assertEquals("[[ 2 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(8);
        assertEquals("[[ 2 ] 5 [ 8 ]]", t.toString());
        testHeight(t);
        t.insert(7);
        assertEquals("[[ 2 ] 5 [[ 7 ] 8 ]]", t.toString());
        testHeight(t);
        t.insert(6);
        assertEquals("[[ 2 ] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testHeight(t);

        t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(2);
        assertEquals("[[ 2 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(8);
        assertEquals("[[ 2 ] 5 [ 8 ]]", t.toString());
        testHeight(t);
        t.insert(6);
        assertEquals("[[ 2 ] 5 [[ 6 ] 8 ]]", t.toString());
        testHeight(t);
        t.insert(7);
        assertEquals("[[ 2 ] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testHeight(t);

        t = new AVLTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(2);
        assertEquals("[[ 2 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(8);
        assertEquals("[[ 2 ] 5 [ 8 ]]", t.toString());
        testHeight(t);
        t.insert(9);
        assertEquals("[[ 2 ] 5 [ 8 [ 9 ]]]", t.toString());
        testHeight(t);
        t.insert(10);
        assertEquals("[[ 2 ] 5 [[ 8 ] 9 [ 10 ]]]", t.toString());
        testHeight(t);
    }
}

