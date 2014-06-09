/*
 * SubsetsOfN.java
 */

package algos;

import java.util.Arrays;

/**
 * You are given a set of numbers 0 - n. Given a k, print all subsets of size k. Give the time complexity of the algorithm
 */
public class SubsetsOfN
{

    /**
     * { C(N, K) } = {"N" + X for X in C(N-1, K-1) } + { C(N-1, K) }
     * 
     * @param n
     * @param k
     */
    public static String printSubsets(int n, int k)
    {
        k = Math.min(k, n);
        return helper(n, k, new int[k], 0, new StringBuilder()).toString();
    }

    // A recursive solution
    private static StringBuilder helper(int n, int k, int[] set, int ii, StringBuilder sb) {
        if (k <= 0) {
            return sb.append(Arrays.toString(set));
        }
        set[ii] = n;
        sb = helper(n-1, k-1, set, ii+1, sb);
        if (n > k) {
            sb = helper(n-1, k, set, ii, sb);
        }
        return sb;
    }
}
