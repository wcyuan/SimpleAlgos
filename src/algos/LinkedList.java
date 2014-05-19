/*
 * LinkedList.java
 *
 * $Rev::                                            $:  Revision of last commit
 * $Author::                                         $:  Author of last commit
 * $Date::                                           $:  Date of last commit
 *
 * $HeadURL: $
 */

/*
 * Copyright (c) 2011 D. E. Shaw & Co., L.P. All rights reserved.
 *
 * This software is the confidential and proprietary information of D. E.
 * Shaw & Co., L.P. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of the license agreement you entered into with D. E. Shaw &
 * Co., L.P.
 */

package algos;

import java.util.Iterator;

/**
 * A simple linked list implementation
 */
public class LinkedList<T> implements Iterable<T> {
	private static class Node<T> {
		private T data;
		private Node<T> next;
		Node() {
			data = null;
			next = null;
		}
		Node(T _data, Node<T> _next) {
			this();
			setData(_data);
			setNext(_next);
		}
		public void setData(T _data) {
			data = _data;
		}
		public void setNext(Node<T> _next) {
			next = _next;
		}
		public T getData() {
			return data;
		}
		public Node<T> getNext() {
			return next;
		}
	}

	private Node<T> head = null;
	int len = 0;

	/**
	 * Insert an element at the head of the list.
	 */
	public void insert(T _data) {
		head = new Node<T>(_data, head);
		len++;
	}

	/**
	 * Remove the first element from the list and return it.
	 * Returns null if the list is empty.
	 */
	public T pop() {
		if (head == null) {
			return null;
		}
		T retval = head.getData();
		head = head.getNext();
		len--;
		return retval;
	}

	/**
	 * @return the size of the list
	 */
	public int size() {
		return len;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		boolean isFirst = true;
		for (T data : this) {
			if (isFirst) {
				isFirst = false;
			} else {
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
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Node<T> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T currdata = current.getData();
				current = current.getNext();
				return currdata;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
