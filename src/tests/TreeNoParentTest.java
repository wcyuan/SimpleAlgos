/*
 * TreeNoParentTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * TreeTest.java
 */


import algos.TreeNoParent;

/**
 *
 */
public class TreeNoParentTest
{

    /**
     * Basic tests of Trees
     */
    @Test
    public void test()
    {
        TreeNoParent<Integer> t = new TreeNoParent<Integer>();
        assertEquals("", t.toString());
        t.insert(4);
        assertEquals("[ 4 ]", t.toString());
        t.insert(3);
        assertEquals("[[ 3 ] 4 ]", t.toString());
        t.insert(5);
        assertEquals("[[ 3 ] 4 [ 5 ]]", t.toString());
        t.insert(2);
        assertEquals("[[[ 2 ] 3 ] 4 [ 5 ]]", t.toString());
        assertEquals("[[ 2 ] 3 ]", t.find(3).toString());
        assertNull(t.find(20));
        assertEquals("[[[ 2 ] 3 ] 4 [ 5 ]]", t.toString());
        
    }

    private TreeNoParent<Integer> makeBalanced() {
        TreeNoParent<Integer> t = new TreeNoParent<Integer>();
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
     */
    @Test
    public void testDelete()
    {
        TreeNoParent<Integer> t = makeBalanced();
        t.delete(2);
        assertEquals("[[ 3 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        t.delete(3);
        assertEquals("[[ 4 ] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        t = makeBalanced();
        t.delete(3);
        assertEquals("[[ 2 [ 4 ]] 5 [[ 6 ] 7 [ 8 ]]]", t.toString());
        t = makeBalanced();
        t.delete(5);
        assertEquals("[[[ 2 ] 3 ] 4 [[ 6 ] 7 [ 8 ]]]", t.toString());
    }

}
