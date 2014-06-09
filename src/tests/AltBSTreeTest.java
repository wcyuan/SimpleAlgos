/*
 * AltBSTreeTest.java
 */

package tests;


import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import algos.AltBSTree;

/**
 *
 */
public class AltBSTreeTest
{

    /**
     * Basic tests of Trees
     */
    @Test
    public void test()
    {
        AltBSTree<Integer> t = new AltBSTree<Integer>();
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

    private AltBSTree<Integer> makeBalanced() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        AltBSTree<Integer> t = new AltBSTree<Integer>();
        t.insert(5);
        assertEquals("[ 5 ]", t.toString());
        testHeight(t);
        t.insert(3);
        assertEquals("[[ 3 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(2);
        assertEquals("[[[ 2 ] 3 ] 5 ]", t.toString());
        testHeight(t);
        t.insert(4);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 ]", t.toString());
        testHeight(t);
        t.insert(7);
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
        AltBSTree<Integer> t = makeBalanced();
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
        AltBSTree<Integer> t = makeBalanced();
        testParentLinks(t);
        testHeight(t);
        t.rotateLeft();
        assertEquals("[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 [ 8 ]]", t.toString());
        testParentLinks(t);
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
     * Test BFS
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Test
    public void testBFS() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        AltBSTree<Integer> t = makeBalanced();
        assertEquals("5, 3, 7, 2, 4, 6, 8", t.BFS());
    }

    /**
     * Test constructing the tree from an array
     * 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Test
    public void testFromArray() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        Integer[] arr = {2, 3, 4, 5, 6, 7, 8};
        AltBSTree<Integer> t = new AltBSTree<Integer>(arr);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        testHeight(t);
        assertTrue(t.isBST());
        Integer[] arr2 = {5, 1, 6, 8, 2, 3, 9};
        t = new AltBSTree<Integer>(arr2);
        assertEquals("[[[ 5 ] 1 [ 6 ]] 8 [[ 2 ] 3 [ 9 ]]]", t.toString());
        testParentLinks(t);
        testHeight(t);
        assertFalse(t.isBST());
    }

    /**
     * Test finding the Kth value
     * 
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testFindKthValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        AltBSTree<Integer> t = makeBalanced();
        assertNull(t.findKthValue(0));
        assertEquals(2, (int)t.findKthValue(1));
        assertEquals(3, (int)t.findKthValue(2));
        assertEquals(4, (int)t.findKthValue(3));
        assertEquals(5, (int)t.findKthValue(4));
        assertEquals(6, (int)t.findKthValue(5));
        assertEquals(7, (int)t.findKthValue(6));
        assertEquals(8, (int)t.findKthValue(7));
        assertNull(t.findKthValue(8));
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
    private <T extends Comparable<T>> void testParentLinks(AltBSTree<T> tree)
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        Field root = tree.getClass().getDeclaredField("root");
        root.setAccessible(true);
        testParentLinks((AltBSTree.Node<T>)root.get(tree));
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void testParentLinks(AltBSTree.Node<T> node)
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
            testParentLinks((AltBSTree.Node<T>)left.get(node));
        }
        if (right.get(node) != null) {
            assertEquals(node, parent.get(right.get(node)));
            testParentLinks((AltBSTree.Node<T>)right.get(node));
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void testHeight(AltBSTree<T> tree)
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        Field root = tree.getClass().getDeclaredField("root");
        root.setAccessible(true);
        testHeight((AltBSTree.Node<T>)root.get(tree));
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void testHeight(AltBSTree.Node<T> node)
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
            testHeight((AltBSTree.Node<T>)left.get(node));
        }
        int rheight = 0;
        if (right.get(node) != null) {
            rheight = (int)height.get(right.get(node));
            testHeight((AltBSTree.Node<T>)right.get(node));
        }
        assertEquals("testing " + node, Math.max(lheight, rheight) + 1, height.get(node));
    }
}
