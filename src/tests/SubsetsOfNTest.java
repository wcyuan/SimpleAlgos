/*
 * SubsetsOfNTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.SubsetsOfN;

/**
 * Tests for the SubsetsOfN test
 */
public class SubsetsOfNTest
{

    /**
     * Basic tests
     */
    @Test
    public void testBasic()
    {
        assertEquals("[]", SubsetsOfN.printSubsets(0, 0));
        assertEquals("[]", SubsetsOfN.printSubsets(1, 0));
        assertEquals("[]", SubsetsOfN.printSubsets(0, 1));
        assertEquals("[]", SubsetsOfN.printSubsets(0, 2));
        assertEquals("[1]", SubsetsOfN.printSubsets(1, 1));
        assertEquals("[2][1]", SubsetsOfN.printSubsets(2, 1));
        assertEquals("[3][2][1]", SubsetsOfN.printSubsets(3, 1));
        assertEquals("[4][3][2][1]", SubsetsOfN.printSubsets(4, 1));
        assertEquals("[2, 1]", SubsetsOfN.printSubsets(2, 2));
        assertEquals("[3, 2, 1]", SubsetsOfN.printSubsets(3, 3));
        assertEquals("[4, 3, 2, 1]", SubsetsOfN.printSubsets(4, 4));
        assertEquals("[3, 2][3, 1][2, 1]", SubsetsOfN.printSubsets(3, 2));
        assertEquals("[4, 3][4, 2][4, 1][3, 2][3, 1][2, 1]", SubsetsOfN.printSubsets(4, 2));
        assertEquals("[4, 3, 2][4, 3, 1][4, 2, 1][3, 2, 1]", SubsetsOfN.printSubsets(4, 3));
        assertEquals("[1]", SubsetsOfN.printSubsets(1, 2));
        assertEquals("[1]", SubsetsOfN.printSubsets(1, 3));
        assertEquals("[2, 1]", SubsetsOfN.printSubsets(2, 3));
        assertEquals("[6, 5, 4, 3, 2, 1]", SubsetsOfN.printSubsets(6, 10));
    }

}
