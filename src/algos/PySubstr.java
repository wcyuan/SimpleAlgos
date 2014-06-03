/*
 * PySubstr.java
 */

package algos;

/**
 * A class that allows for python-like substring indices
 */
public class PySubstr
{

    /**
     * Handle negative indices for strings
     * A negative index starts from the end of the string (-1 is the last character)
     * After that, indices that go past the end of the string are capped to the end of the string
     * 
     * @param len
     * @param idx
     * @return
     */
    public static int fixidx(int len, int idx)
    {
        if (idx < 0) {
            idx = len + idx;
        }
        if (idx < 0) {
            idx = 0;
        }
        if (idx >= len) {
            idx = len;
        }
        return idx;
    }

    /**
     * Take a substring, allowing negative indices
     *
     * @param str
     * @param begin
     * @return
     */
    public static String substr(String str, int begin, int end)
    {
        if (str.length() == 0) {
            return "";
        }
        begin = fixidx(str.length(), begin);
        end = fixidx(str.length(), end);
        if (begin >= end) {
            return "";
        }
        return str.substring(begin, end);
    }

    /**
     * Take a suffix, allowing negative indices
     * 
     * @param str
     * @param begin
     * @return
     */
    public static String suffix(String str, int begin)
    {
        return substr(str, begin, str.length());
    }

    /**
     * Take a prefix, allowing negative indices
     * 
     * @param str
     * @param begin
     * @return
     */
    public static String prefix(String str, int end)
    {
        return substr(str, 0, end);
    }

    /**
     * Parse a string as an int.  The empty string defaults to 0.
     * @param str
     * @param def
     * @return
     */
    public static int parse(String str, int def)
    {
        if (str.length() == 0) {
            return def;
        }
        return Integer.parseInt(str);
    }

    /**
     * get the sign from a string
     */
    public static int sign(String str)
    {
        if (str.indexOf(0) == '-') {
            return -1;
        } else {
            return 1;
        }
    }
}
