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

    private AltBSTree<Integer> makeBalanced()
    {
        AltBSTree<Integer> t = new AltBSTree<Integer>();
        t.insert(5);
        t.insert(3);
        t.insert(2);
        t.insert(4);
        t.insert(7);
        t.insert(6);
        t.insert(8);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
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
    public void testDelete() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        AltBSTree<Integer> t = makeBalanced();
        assertEquals(true, t.delete(2));
        assertEquals("[[ 3 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        assertEquals(true, t.delete(3));
        assertEquals("[[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        t = makeBalanced();
        assertEquals(true, t.delete(3));
        assertEquals("[[ 2 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        t = makeBalanced();
        assertEquals(true, t.delete(5));
        assertEquals("[[[ 2 ] 3 ] 4 [[ 6 ] 7 [ 8 ]]]", t.toString());
        assertEquals(false, t.delete(10));
        testParentLinks(t);
    }

    /**
     * Test rotating
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Test
    public void testRotation() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        AltBSTree<Integer> t = makeBalanced();
        testParentLinks(t);
        t.rotateLeft();
        assertEquals("[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 [ 8 ]]", t.toString());
        testParentLinks(t);
        t.rotateLeft();
        assertEquals("[[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 ] 8 ]", t.toString());
        testParentLinks(t);
        t.rotateLeft();
        assertEquals("[[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 ] 8 ]", t.toString());
        testParentLinks(t);
        t = makeBalanced();
        t.rotateRight();
        assertEquals("[[ 2 ] 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]", t.toString());
        testParentLinks(t);
        t.rotateRight();
        assertEquals("[ 2 [ 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]]", t.toString());
        testParentLinks(t);
        t.rotateRight();
        assertEquals("[ 2 [ 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]]", t.toString());
        testParentLinks(t);
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
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
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

}
