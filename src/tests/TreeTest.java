/*
 * TreeTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.Tree;

/**
 *
 */
public class TreeTest
{

    /**
     * Basic tests of Trees
     */
    @Test
    public void test()
    {
        Tree<Integer> t = new Tree<Integer>();
        assertEquals("", t.toString());
        assertEquals("", Tree.inorder(t));
        t.insert(4);
        assertEquals("[ 4 ]", t.toString());
        assertEquals("4", Tree.inorder(t));
        t.insert(3);
        assertEquals("[[ 3 ] 4 ]", t.toString());
        assertEquals("34", Tree.inorder(t));
        t.insert(5);
        assertEquals("[[ 3 ] 4 [ 5 ]]", t.toString());
        assertEquals("345", Tree.inorder(t));
        t.insert(2);
        assertEquals("[[[ 2 ] 3 ] 4 [ 5 ]]", t.toString());
        assertEquals("2345", Tree.inorder(t));
    }

}
