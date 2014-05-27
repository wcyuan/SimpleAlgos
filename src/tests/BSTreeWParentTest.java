/*
 * TreeTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.BSTreeWParent;
import algos.IBSTree;

/**
 * Test for binary search trees with parents
 */
public class BSTreeWParentTest
{

    /**
     * Basic tests of Trees
     */
    @Test
    public void test()
    {
        BSTreeWParent<Integer> t = new BSTreeWParent<Integer>();
        assertEquals("", t.toString());
        assertEquals("", BSTreeWParent.inorder(t));
        testParentLinks(t);
        t.insert(4);
        assertEquals("[ 4 ]", t.toString());
        assertEquals("4", BSTreeWParent.inorder(t));
        testParentLinks(t);
        t.insert(3);
        assertEquals("[[ 3 ] 4 ]", t.toString());
        assertEquals("34", BSTreeWParent.inorder(t));
        testParentLinks(t);
        t.insert(5);
        assertEquals("[[ 3 ] 4 [ 5 ]]", t.toString());
        assertEquals("345", BSTreeWParent.inorder(t));
        testParentLinks(t);
        t.insert(2);
        assertEquals("[[[ 2 ] 3 ] 4 [ 5 ]]", t.toString());
        assertEquals("2345", BSTreeWParent.inorder(t));
        assertEquals("[[ 2 ] 3 ]", t.find(3).toString());
        testParentLinks(t);
        assertNull(t.find(20));
        assertNull(t.findWParent(2).predecessor());
        assertEquals(2, (int)t.findWParent(3).predecessor().getData());
        assertEquals(3, (int)t.findWParent(4).predecessor().getData());
        assertEquals(4, (int)t.findWParent(5).predecessor().getData());
        assertEquals("[[[ 2 ] 3 ] 4 [ 5 ]]", t.toString());
        
    }

    private BSTreeWParent<Integer> makeBalanced() {
        BSTreeWParent<Integer> t = new BSTreeWParent<Integer>();
        t.insert(5);
        t.insert(3);
        t.insert(2);
        t.insert(4);
        t.insert(7);
        t.insert(6);
        t.insert(8);
        assertEquals("[[[ 2 ] 3 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        return t;
    }

    private void testParentLinks(BSTreeWParent<Integer> tree) {
        if (tree.getLeft() != null) {
            assertEquals(tree, tree.getLeftWParent().getParent());
            testParentLinks(tree.getLeftWParent());
        }
        if (tree.getRight() != null) {
            assertEquals(tree, tree.getRightWParent().getParent());
            testParentLinks(tree.getRightWParent());
        }
    }

    /**
     * Test deleting
     */
    @Test
    public void testDelete()
    {
        BSTreeWParent<Integer> t = makeBalanced();
        t.delete(2);
        assertEquals("[[ 3 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        t.delete(3);
        assertEquals("[[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        t = makeBalanced();
        t.delete(3);
        assertEquals("[[ 2 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
        t = makeBalanced();
        t.delete(5);
        assertEquals("[[[ 2 ] 3 ] 4 [[ 6 ] 7 [ 8 ]]]", t.toString());
        testParentLinks(t);
    }

    /**
     * Test rotating
     */
    @Test
    public void testRotation()
    {
        BSTreeWParent<Integer> t = makeBalanced();
        t = t.rotateLeftWParent();
        assertEquals("[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 [ 8 ]]", t.toString());
        testParentLinks(t);
        t = t.rotateLeftWParent();
        assertEquals("[[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 ] 8 ]", t.toString());
        testParentLinks(t);
        t = t.rotateLeftWParent();
        assertEquals("[[[[[ 2 ] 3 [ 4 ]] 5 [ 6 ]] 7 ] 8 ]", t.toString());
        t = makeBalanced();
        t = t.rotateRightWParent();
        assertEquals("[[ 2 ] 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]", t.toString());
        testParentLinks(t);
        t = t.rotateRightWParent();
        assertEquals("[ 2 [ 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]]", t.toString());
        testParentLinks(t);
        t = t.rotateRightWParent();
        assertEquals("[ 2 [ 3 [[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]]]", t.toString());
        testParentLinks(t);
    }
}
