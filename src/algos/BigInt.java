package algos;

/**
 * A class for representing arbitrary size integers
 */
public class BigInt
{
    // We reverse the digits when we read them, so the 0th index is the ones
    // place.
    private int[] digits;

    // Sign should be either 1 or -1
    private boolean isNegative;

    /**
     * Construct a BigInt from a string
     * 
     * @param num
     */
    public BigInt(String num) {
        if (num.startsWith("-")) {
            isNegative = true;
            num = num.substring(1);
        }
        int len = num.length();
        digits = new int[len];
        for (int ii = 0; ii < len; ii++) {
            digits[ii] = Integer
                    .parseInt(num.substring(len - ii - 1, len - ii));
        }
    }

    /**
     * Construct a BigInt from an int.
     * 
     * @param num
     */
    public BigInt(int num) {
        if (num < 0) {
            isNegative = true;
            num *= -1;
        }
        if (num == 0) {
            digits = new int[1];
            digits[0] = 0;
            return;
        }
        digits = new int[(int) Math.log10(num) + 1];
        int ii = 0;
        while (num > 0) {
            digits[ii++] = num % 10;
            num /= 10;
        }
    }

    /**
     * Construct a BitInt from an array of integers and a sign. We don't do any
     * error checking (like confirming that each element of the array is between
     * 0 and 9).
     * 
     * @param digits
     * @param isNegative
     */
    private BigInt(int[] _digits, boolean _isNegative) {
        this.isNegative = _isNegative;
        this.digits = _digits;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(digits.length + 1);
        if (isNegative) {
            sb.append("-");
        }
        boolean first = false;
        for (int ii = digits.length - 1; ii >= 0; ii--) {
            // Strip leading zeros, unless zero is the only digit.
            if (digits[ii] == 0 && !first && ii != 0) {
                continue;
            }
            sb.append(Integer.toString(digits[ii]));
            first = true;
        }
        return sb.toString();
    }

    /**
     * Returns the sign, 1 for positive or -1 for negative.
     * 
     * @return
     */
    public int sign()
    {
        return isNegative ? -1 : 1;
    }

    /**
     * Convert to a long.  Of course, this might overflow...
     * 
     * @return
     */
    public long toLong()
    {
        long ret = 0;
        long pow = 1;
        for (int ii = 0; ii < digits.length; ii++) {
            ret += digits[ii] * pow;
            pow *= 10;
        }
        return ret * sign();
    }

    /**
     * Multiply two BigInts together
     * @param other
     * @return
     */
    public BigInt mult(BigInt other)
    {
        int[] newdigits = new int[this.digits.length + other.digits.length];
        boolean sign = this.isNegative ^ other.isNegative;
        for (int ii = 0; ii < digits.length; ii++) {
            int carry = 0;
            for (int jj = 0; jj < other.digits.length || carry > 0; jj++) {
                int otherDigit = jj >= other.digits.length ? 0
                        : other.digits[jj];
                int val = this.digits[ii] * otherDigit + carry;
                newdigits[ii + jj] += val % 10;
                carry = val / 10;
            }
        }
        return new BigInt(newdigits, sign);
    }
}
