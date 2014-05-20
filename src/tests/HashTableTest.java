/*
 * HashTableTest.java
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algos.HashTable;

/**
 *
 */
public class HashTableTest {

        /**
         * Basic tests
         */
        @Test
        public void test() {
                HashTable<String, Integer> ht = new HashTable<String, Integer>(5);
                assertEquals("{}", ht.toString());
                assertEquals(null, ht.get("Goodbye"));
                ht.set("Hello", 4);
                assertEquals(4, (int)ht.get("Hello"));
                assertEquals(null, ht.get("Goodbye"));
                assertEquals("{(Hello, 4)}", ht.toString());
                ht.set("Goodbye", 5);
                assertEquals(4, (int)ht.get("Hello"));
                assertEquals(5, (int)ht.get("Goodbye"));
                assertEquals(null, ht.get("asdf"));
                assertEquals("{(Hello, 4), (Goodbye, 5)}", ht.toString());
                ht.set("Hello", 2);
                assertEquals(2, (int)ht.get("Hello"));
                assertEquals(5, (int)ht.get("Goodbye"));
                assertEquals(null, ht.get("asdf"));
                assertEquals("{(Hello, 2), (Goodbye, 5)}", ht.toString());
                ht.delete("Hello");
                assertEquals(null, ht.get("Hello"));
                assertEquals(5, (int)ht.get("Goodbye"));
                assertEquals("{(Goodbye, 5)}", ht.toString());
        }
}
