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
            int tmp = arr[start];
            int last = start;
            int next = (start + r) % arr.length;
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
