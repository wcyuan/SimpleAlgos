/*
 * HashTable.java
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
 * A simple Hash Table implementation.
 * 
 * When there are collisions, chain using a linked list.
 */
public class HashTable<K, V> implements Iterable<K> {

	private static class Element<K, V> {
		private final K key;
		private V value;

		public Element(K _key, V _value) {
			key = _key;
			value = _value;
		}

		/**
		 * @return the key
		 */
		public K getKey() {
			return key;
		}

		/**
		 * @return the value
		 */
		public V getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(V _value) {
			this.value = _value;
		}
	}

	private static class ElementList<K, V> implements Iterable<Element<K, V>> {
		private final LinkedList<Element<K, V>> elements = new LinkedList<Element<K, V>>();

		public void setValue(K key, V value) {
			for (Element<K, V> e : elements) {
				if (e.getKey().equals(key)) {
					e.setValue(value);
					return;
				}
			}
			elements.insert(new Element<K, V>(key, value));
		}

		public V getValue(K key, V def) {
			for (Element<K, V> e : elements) {
				if (e.getKey().equals(key)) {
					return e.getValue();
				}
			}
			return def;
		}

		public boolean hasKey(K key) {
			for (Element<K, V> e : elements) {
				if (e.getKey().equals(key)) {
					return true;
				}
			}
			return false;
		}

		public void removeKey(K key) {
			Iterator<Element<K, V>> itr = elements.iterator();
			while (itr.hasNext()) {
				if (itr.next().getKey().equals(key)) {
					itr.remove();
					return;
				}
			}
		}

		/**
		 * @see java.lang.Iterable#iterator()
		 */
		@Override
		public Iterator<Element<K, V>> iterator() {
			return elements.iterator();
		}

		public int size() {
			return elements.size();
		}
	}

	private final ElementList<K, V>[] data;

	/**
	 * Constructs HashTable
	 * 
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		data = new ElementList[size];
		for (int ii = 0; ii < size; ii++) {
			data[ii] = new ElementList<K, V>();
		}
	}

	private int hash(K key) {
		return key.hashCode() % data.length;
	}

	/**
	 * Set a value
	 * 
	 * @param key
	 * @param value
	 */
	public void set(K key, V value) {
		data[hash(key)].setValue(key, value);
	}

	/**
	 * Get the value for a key. If there is no matching element, return the
	 * default value that was passed in.
	 * 
	 * @param key
	 * @param def
	 * @return
	 */
	public V get(K key, V def) {
		return data[hash(key)].getValue(key, def);
	}

	/**
	 * Get the value for a key. If there is no matching element, return null.
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		return get(key, null);
	}

	/**
	 * Returns true if there is a matching element for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasKey(K key) {
		return data[hash(key)].hasKey(key);
	}

	/**
	 * Removes the element matching the given key. Has no effect if there is no
	 * matching element.
	 * 
	 * @param key
	 */
	public void delete(K key) {
		data[hash(key)].removeKey(key);
	}

	/**
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<K> iterator() {
		return new Iterator<K>() {

			// Invariants:
			//
			// If (itr == null or !itr.hasNext()) and bucket >= data.length,
			// that means we've reached the end of the iteration.
			//
			// If itr == null and bucket < data.length, that means we haven't
			// started the iteration.
			//
			// Otherwise, itr should not be null and bucket should be between 0
			// and data.length
			private int bucket = 0;
			private Iterator<Element<K, V>> itr = null;
			private Iterator<Element<K, V>> previtr = null;

			private boolean findNextBucket() {
				for (; bucket < data.length; bucket++) {
					if (data[bucket].size() > 0) {
						itr = data[bucket].iterator();
						return true;
					}
				}
				itr = null;
				return false;
			}
			
			@Override
			public boolean hasNext() {
				if (itr != null) {
					return itr.hasNext();
				}
				return findNextBucket();
			}

			@Override
			public K next() {
				if (itr == null) {
					// If the iterator is null, it's possible we just haven't
					// started the iteration. We can start it by calling
					// hasNext(). If hasNext() returns true, then itr is
					// definitely not null;
					if (!hasNext()) {
						return null;
					}
				}
				Element<K, V> elt = itr.next();
				previtr = itr;
				if (!itr.hasNext()) {
					bucket++;
					findNextBucket();
				}
				if (elt == null) {
					return null;
				}
				return elt.getKey();
			}

			// Returns the element that was last returned by next()
			@Override
			public void remove() {
				if (previtr != null) {
					previtr.remove();
				}
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		boolean isFirst = true;
		for (K key : this) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(", ");
			}
			sb.append("(");
			sb.append(key);
			sb.append(", ");
			sb.append(get(key));
			sb.append(")");
		}
		sb.append("}");
		return sb.toString();
	}
}
