/*
 * LinkedListTest.java
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

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.LinkedList;

/**
 * Tests for Linked Lists
 */
public class LinkedListTest {

	/**
	 * Test toString, insert, and pop
	 */
	@Test
	public void test() {
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

}
