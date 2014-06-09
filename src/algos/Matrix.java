/*
 * RotateAMatrix.java
 */

package algos;


import static org.junit.Assert.assertEquals;

/**
 * Represents a 2-d array
 */
public class Matrix<T>
{
    T[][] mat;

    /**
     * Constructs Matrix
     * @param _mat
     */
    public Matrix(T[][] _mat)
    {
        mat = _mat;
    }

    /**
     * Rotate a square matrix 90 degrees clockwise.
     * Just assumes that the matrix is square, doesn't check.
     * Note, this is the only method that requires that the matrix is square,
     * the others work for any 2-d array.
     * 
     * @param mat
     */
    public void rotate()
    {
        int rmid = mat.length / 2;
        int cmid = mat.length / 2;
        if (mat.length % 2 != 0) {
            rmid++;
        }
        for (int row = 0; row < rmid; row++) {
            for (int col = 0; col < cmid; col++) {
                T tmp = mat[row][col];
                mat[row][col] = mat[mat.length - col - 1][row];
                mat[mat.length - col - 1][row] = mat[mat.length - row - 1][mat.length - col - 1];
                mat[mat.length - row - 1][mat.length - col - 1] = mat[col][mat.length - row - 1];
                mat[col][mat.length - row - 1] = tmp;
            }
        }
    }

    /**
     * Writes a Matrix to a string
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < mat.length; row++) {
            if (row != 0) {
                sb.append("\n");
            }
            for (int col = 0; col < mat[row].length; col++) {
                if (col != 0) {
                    sb.append(", ");
                }
                sb.append(mat[row][col].toString());
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Matrix)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Matrix<T> other = (Matrix<T>)o;
        if (mat.length != other.mat.length) {
            return false;
        }
        for (int ii = 0; ii < mat.length; ii++) {
            if (mat[ii].length != other.mat[ii].length) {
                return false;
            }
            for (int jj = 0; jj < mat[ii].length; jj++) {
                if (!mat[ii][jj].equals(other.mat[ii][jj])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return a copy of this Matrix;
     * @return
     */
    @SuppressWarnings("unchecked")
    public Matrix<T> copy()
    {
        Object[][] ret = new Object[mat.length][mat.length];
        for (int ii = 0; ii < mat.length; ii++) {
            for (int jj = 0; jj < mat[ii].length; jj++) {
                ret[ii][jj] = mat[ii][jj];
            }
        }
        return new Matrix<T>((T[][])ret);
    }

    /**
     * Given a matrix, print it clockwise from the first element to the very inner element
     * 
     * This is surprisingly hard to get right...
     * 
     * @return
     */
    public String printSpiral()
    {
        StringBuilder sb = new StringBuilder();
        int rmid = mat.length / 2;
        if (mat.length % 2 != 0) {
            rmid++;
        }
        for (int xx = 0; xx < rmid; xx++) {
            for (int col = xx; col < mat[0].length - xx; col++) {
                if (col != 0) {
                    sb.append(", ");
                }
                sb.append(mat[xx][col].toString());
            }
            for (int row = xx + 1; row < mat.length - xx - 1; row++) {
                sb.append(", ");
                sb.append(mat[row][mat[0].length - xx - 1].toString());
            }
            if (mat.length - xx - 1 > xx) {
                for (int col = mat[0].length - xx - 1; col >= xx; col--) {
                    sb.append(", ");
                    sb.append(mat[mat.length - xx - 1][col].toString());
                }
            }
            if (mat[0].length - xx - 1 > xx) {
                for (int row = mat.length - xx - 2; row > xx; row--) {
                    sb.append(", ");
                    sb.append(mat[row][xx].toString());
                }
            }
        }
        return sb.toString();
    }
}
