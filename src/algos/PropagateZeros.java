/*
 * PropagateZeros.java
 */

package algos;

/**
 * Given a matrix of numbers in which some may be zero. If a cell contains a
 * zero, set all the cells in the corresponding column and row to zero
 */
public class PropagateZeros
{
    /**
     * Given a matrix of numbers in which some may be zero. If a cell contains a
     * zero, set all the cells in the corresponding column and row to zero
     * 
     * @param arr
     */
    public static void propagateZeros(int[][] arr)
    {
        int width = arr.length;
        int height = width == 0 ? 0 : arr[0].length;
        int[] rowsToZero = new int[width];
        int[] colsToZero = new int[height];
        for (int ii = 0; ii < width; ii++) {
            for (int jj = 0; jj < height; jj++) {
                if (arr[ii][jj] == 0) {
                    rowsToZero[ii] = 1;
                    colsToZero[jj] = 1;
                }
            }
        }
        for (int ii = 0; ii < width; ii++) {
            for (int jj = 0; jj < height; jj++) {
                if (rowsToZero[ii] == 1 || colsToZero[jj] == 1) {
                    arr[ii][jj] = 0;
                }
            }
        }
    }
}
