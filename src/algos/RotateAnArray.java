/*
 * RotateAnArray.java
 */

package algos;

/**
 *
 */
public class RotateAnArray
{
    /**
     * Implement a function rotateArray(vector<int> arr, int r) which rotates
     * the array by r places. Eg 1 2 3 4 5 on being rotated by 2 gives 3 4 5 1 2
     * 
     * This is constant space, O(n) time (does not depend on r)
     * 
     * @param arr
     * @param r
     */
    public static void rotateArray(int[] arr, int r) {
        boolean stop = false;
        if (arr.length == 0) {
            return;
        }
        r = r % arr.length;
        for (int start = 0; start < Math.min(arr.length, r); start++) {
            // Inside this loop, we do:
            // A[start] = A[start+r]
            // A[start+r] = A[start+r+r]
            // A[start+r+r] = A[start+r+r+r]
            // A[start+r+r+r] = A[start+r+r+r+r]
            // ...
            // mod'ing by the array length
            // until we get back to start.
            //
            // If r and the array length are relatively prime (largest common
            // factor is 1), then we are done after one loop of this.
            // Otherwise, we have to do the same thing for start++;
            // When can we finish?  When we've rotated r-1, we should be done.
            // That might happen before start = r-1.
            int tmp = arr[start];
            int last = start;
            int next = (start + r) % arr.length;
            // If we've rotated the r-1 element, then we've rotated everything.
            if (next == (r-1) % arr.length) {
                stop = true;
            }
            while (next != start) {
                arr[last] = arr[next];
                last = next;
                next = (last + r) % arr.length;
                if (next == (r-1) % arr.length) {
                    stop = true;
                }
            }
            arr[last] = tmp;
            if (stop) {
                break;
            }
        }
    }
}
