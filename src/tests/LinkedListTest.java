/*
 * LinkedListTest.java
 */

package tests;


import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import algos.LinkedList;

/**
 * Tests for Linked Lists
 */
public class LinkedListTest
{

    /**
     * Test toString, insert, and pop
     */
    @Test
    public void testBasic()
    {
        LinkedList<Integer> list = new LinkedList<Integer>();
        assertEquals("[]", list.toString());
        assertEquals(0, list.size());
        list.insert(5);
        assertEquals("[5]", list.toString());
        assertEquals(1, list.size());
        list.insert(6);
        assertEquals("[6, 5]", list.toString());
        assertEquals(2, list.size());
        list.pop();
        assertEquals("[5]", list.toString());
        assertEquals(1, list.size());
        list.pop();
        assertEquals("[]", list.toString());
        assertEquals(0, list.size());
    }

    /**
     * Test removing an element
     */
    @Test
    public void testRemove()
    {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.insert(3);
        list.insert(2);
        list.insert(1);

        Iterator<Integer> itr = list.iterator();
        assertEquals(3, list.size());
        assertEquals("[1, 2, 3]", list.toString());
        assertEquals(true, itr.hasNext());
        itr.remove();
        assertEquals(3, list.size());
        assertEquals("[1, 2, 3]", list.toString());
        assertEquals(true, itr.hasNext());

        assertEquals(1, (int)itr.next());
        itr.remove();
        assertEquals(2, list.size());
        assertEquals("[2, 3]", list.toString());
        assertEquals(true, itr.hasNext());
        itr.remove();
        assertEquals(2, list.size());
        assertEquals("[2, 3]", list.toString());
        assertEquals(true, itr.hasNext());

        assertEquals(2, (int)itr.next());
        itr.remove();
        assertEquals(1, list.size());
        assertEquals("[3]", list.toString());
        assertEquals(true, itr.hasNext());

        assertEquals(3, (int)itr.next());
        itr.remove();
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
        itr.remove();
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
        assertEquals(false, itr.hasNext());
    }
}
