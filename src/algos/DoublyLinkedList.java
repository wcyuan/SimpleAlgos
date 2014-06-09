/*
 * DoublyLinkedList.java
 */

package algos;


import java.util.Iterator;

/**
 * A DoublyLinkedList
 */
public class DoublyLinkedList<T> implements Iterable<T>
{

    /**
     * A single node of the list
     */
    public static class Node<T>
    {
        private T       data;
        private Node<T> next;
        private Node<T> prev;

        Node()
        {
            data = null;
            next = null;
            prev = null;
        }

        Node(T _data, Node<T> _next, Node<T> _prev)
        {
            this();
            setData(_data);
            setNext(_next);
            setPrev(_prev);
        }

        private void setData(T _data)
        {
            data = _data;
        }

        private void setNext(Node<T> _next)
        {
            next = _next;
        }

        private void setPrev(Node<T> _prev)
        {
            prev = _prev;
        }

        private T getData()
        {
            return data;
        }

        private Node<T> getNext()
        {
            return next;
        }

        private Node<T> getPrev()
        {
            return prev;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    int             len  = 0;

    /**
     * Insert an element at the head of the list.
     */
    public void insertHead(T _data)
    {
        head = new Node<T>(_data, head, null);
        if (head.getNext() != null) {
            head.getNext().setPrev(head);
        }
        if (tail == null) {
            tail = head;
        }
        len++;
    }

    /**
     * Insert an element at the tail of the list.
     */
    public void insertTail(T _data)
    {
        if (tail == null) {
            insertHead(_data);
            return;
        }

        tail.setNext(new Node<T>(_data, null, tail));
        tail = tail.getNext();
        len++;
    }

    /**
     * Remove the first element from the list and return it. Returns null if the
     * list is empty.
     */
    public T pop()
    {
        if (head == null) {
            return null;
        }
        T retval = head.getData();
        head = head.getNext();
        if (head != null) {
            head.setPrev(null);
        }
        else {
            tail = null;
        }
        len--;
        return retval;
    }

    /**
     * Remove the last element from the list and return it. Returns null if the
     * list is empty.
     */
    public T popLast()
    {
        if (tail == null) {
            return null;
        }
        T retval = tail.getData();
        tail = tail.getPrev();
        if (tail != null) {
            tail.setNext(null);
        }
        else {
            head = null;
        }
        len--;
        return retval;
    }

    /**
     * @return the size of the list
     */
    public int size()
    {
        return len;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        boolean isFirst = true;
        for (T data : this) {
            if (isFirst) {
                isFirst = false;
            }
            else {
                sb.append(", ");
            }
            sb.append(data);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<T>()
        {

            private Node<T> current  = head;
            private Node<T> prev = null;

            @Override
            public boolean hasNext()
            {
                return current != null;
            }

            @Override
            public T next()
            {
                prev = current;
                if (current != null) {
                    current = current.getNext();
                }
                if (prev != null) {
                    return prev.getData();
                }
                else {
                    return null;
                }
            }

            // Returns the element that was last returned by next()
            @Override
            public void remove()
            {
                if (prev == null) {
                    // next hasn't been called, or the list is empty. Nothing to
                    // remove.
                    return;
                }
                if (prev.getNext() != null) {
                    prev.getNext().setPrev(prev.getPrev());
                }
                if (prev.getPrev() != null) {
                    prev.getPrev().setNext(prev.getNext());
                }
                if (head == prev) {
                    head = prev.getNext();
                }
                if (tail == prev) {
                    tail = prev.getPrev();
                }
                prev = prev.getPrev();
                len--;
            }
        };
    }
}
