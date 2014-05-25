/*
 * BinarySearch.java
 */

package algos;

/**
 *
 */
public class BinarySearch
{
    /**
     * If we can't find the value, we return -1
     * @param value
     * @param sorted
     * @return
     */
    public static <T extends Comparable<T>>int search(T value, T[] sorted) {
        int start = 0;
        int end = sorted.length-1;
        while (start <= end) {
            // average this way (instead of "(start + end) / 2") to avoid overflow
            int mid = start + (end - start) / 2;
            int cmp = value.compareTo(sorted[mid]);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return -1;
    }
}
