/*
 * DoublyLinkedListTest.java
 */

package tests;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Iterator;

import org.junit.Test;

import algos.DoublyLinkedList;
import algos.LinkedList;

/**
 *
 */
public class DoublyLinkedListTest
{

    /**
     * Test toString, insert, and pop
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Test
    public void testBasic() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        assertEquals("[]", list.toString());
        assertEquals(0, list.size());
        testLinks(list);
        list.insertTail(5);
        assertEquals("[5]", list.toString());
        assertEquals(1, list.size());
        testLinks(list);
        list.insertTail(6);
        assertEquals("[5, 6]", list.toString());
        assertEquals(2, list.size());
        testLinks(list);
        list.popLast();
        assertEquals("[5]", list.toString());
        assertEquals(1, list.size());
        testLinks(list);
        list.popLast();
        assertEquals("[]", list.toString());
        assertEquals(0, list.size());
        testLinks(list);
    }

    /**
     * Test removing an element
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Test
    public void testRemove() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        testLinks(list);
        list.insertTail(2);
        testLinks(list);
        list.insertHead(1);
        testLinks(list);
        list.insertTail(3);
        testLinks(list);
        list.insertTail(4);
        testLinks(list);

        Iterator<Integer> itr = list.iterator();
        assertEquals(4, list.size());
        assertEquals("[1, 2, 3, 4]", list.toString());
        assertEquals(true, itr.hasNext());
        itr.remove();
        assertEquals(4, list.size());
        assertEquals("[1, 2, 3, 4]", list.toString());
        assertEquals(true, itr.hasNext());
        testLinks(list);

        assertEquals(1, (int)itr.next());
        itr.remove();
        assertEquals(3, list.size());
        assertEquals("[2, 3, 4]", list.toString());
        assertEquals(true, itr.hasNext());
        testLinks(list);
        itr.remove();
        assertEquals(3, list.size());
        assertEquals("[2, 3, 4]", list.toString());
        assertEquals(true, itr.hasNext());
        testLinks(list);

        assertEquals(2, (int)itr.next());
        itr.remove();
        assertEquals(2, list.size());
        assertEquals("[3, 4]", list.toString());
        assertEquals(true, itr.hasNext());
        testLinks(list);

        assertEquals(3, (int)itr.next());
        itr.remove();
        assertEquals(1, list.size());
        assertEquals("[4]", list.toString());
        assertEquals(true, itr.hasNext());
        testLinks(list);

        assertEquals(4, (int)itr.next());
        itr.remove();
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
        testLinks(list);
        itr.remove();
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
        assertEquals(false, itr.hasNext());
        testLinks(list);
    }

    @SuppressWarnings("unchecked")
    private <T> void testLinks(DoublyLinkedList<T> list)
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException,
                    IllegalAccessException
    {
        Field head = list.getClass().getDeclaredField("head");
        head.setAccessible(true);
        Field tail = list.getClass().getDeclaredField("tail");
        tail.setAccessible(true);
        DoublyLinkedList.Node<T> node = (DoublyLinkedList.Node<T>)head.get(list);
        DoublyLinkedList.Node<T> tailnode = (DoublyLinkedList.Node<T>)tail.get(list);
        if (node == null) {
            assertNull(tailnode);
            return;
        }
        Field next = node.getClass().getDeclaredField("next");
        next.setAccessible(true);
        Field prev = node.getClass().getDeclaredField("prev");
        prev.setAccessible(true);
        assertNull(prev.get(node));
        assertNull(next.get(tailnode));

        DoublyLinkedList.Node<T> prevnode = null;
        while (node != null) {
            if (next.get(node) != null) {
                assertEquals(node, prev.get(next.get(node)));
            }
            prevnode = node;
            node = (DoublyLinkedList.Node<T>)next.get(node);
        }
        assertEquals(prevnode, tailnode);
    }

    /**
     * test reverse
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Test
    public void testReverse() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        list.insertHead(3);
        list.insertHead(2);
        list.insertHead(1);
        list.reverse();
        testLinks(list);
        assertEquals("[3, 2, 1]", list.toString());
        DoublyLinkedList<Integer> rev = list.reversed();
        testLinks(list);
        testLinks(rev);
        assertEquals("[3, 2, 1]", list.toString());
        assertEquals("[1, 2, 3]", rev.toString());
    }
}
