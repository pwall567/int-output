/*
 * @(#) IntOutput.java
 *
 * int-output  Integer output functions
 * Copyright (c) 2021, 2022, 2023 Peter Wall
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.pwall.util;

import java.io.IOException;
import java.util.function.IntConsumer;

/**
 * A set of static functions used in the conversion of integer values to string representations.
 *
 * @author  Peter Wall
 */
public class IntOutput {

    public static final char[] digits = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static final char[] tensDigits = {
            '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
            '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
            '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
            '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
            '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
            '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
            '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
            '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
            '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
            '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'
    };

    public static final char[] digitsHex = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    public static final char[] digitsHexLC = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static final String MIN_INTEGER_DIGITS = "2147483648";
    public static final String MIN_LONG_DIGITS = "9223372036854775808";

    /**
     * Append an {@code int} left-trimmed to an {@link Appendable}.  This method outputs the digits left to right,
     * avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendInt(Appendable a, int i) throws IOException {
        if (i < 0) {
            a.append('-');
            if (i == Integer.MIN_VALUE)
                a.append(MIN_INTEGER_DIGITS);
            else
                appendPositiveInt(a, -i);
        }
        else
            appendPositiveInt(a, i);
    }

    /**
     * Append a positive {@code int} left-trimmed to an {@link Appendable}.  This method outputs the digits left to
     * right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveInt(Appendable a, int i) throws IOException {
        if (i >= 100) {
            int n = i / 100;
            appendPositiveInt(a, n);
            append2Digits(a, i - n * 100);
        }
        else if (i >= 10)
            append2Digits(a, i);
        else
            a.append(digits[i]);
    }

    /**
     * Append an unsigned {@code int} left-trimmed to an {@link Appendable}.  This method outputs the digits left to
     * right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendUnsignedInt(Appendable a, int i) throws IOException {
        if (i >= 0)
            appendPositiveInt(a, i);
        else {
            int n = (i >>> 1) / 50;
            appendPositiveInt(a, n);
            append2Digits(a, i - n * 100);
        }
    }

    /**
     * Append an {@code int} left-trimmed to an {@link Appendable}, using a scale parameter to indicate the number of
     * decimal places.  This method outputs the digits left to right, avoiding the need to allocate a separate object to
     * hold the string form.
     * <br>
     * Negative scale values (indicating that decimal point is to the right of the last digit) are ignored.  It is left
     * to the user to decide whether to output additional zeros following the number, or to add an exponent suffix.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendIntScaled(Appendable a, int i, int scale, char separator) throws IOException {
        if (i < 0) {
            a.append('-');
            if (i == Integer.MIN_VALUE)
                appendStringScaled(a, MIN_INTEGER_DIGITS, scale, separator);
            else
                appendPositiveIntScaled(a, -i, scale, separator);
        }
        else
            appendPositiveIntScaled(a, i, scale, separator);
    }

    /**
     * Append a given string (representing a decimal value) to an {@link Appendable}, using a scale parameter to
     * indicate the number of decimal places.

     * @param   a           the {@link Appendable}
     * @param   string      the string of digits
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @throws  IOException if thrown by the {@link Appendable}
     */
    private static void appendStringScaled(Appendable a, String string, int scale, char separator) throws IOException {
        if (scale <= 0)
            a.append(string);
        else {
            int length = string.length();
            if (scale >= length) {
                a.append('0');
                a.append(separator);
                for (int i = scale; i > length; i--)
                    a.append('0');
                a.append(string);
            }
            else {
                int insertionPoint = length - scale;
                for (int i = 0; i < length; i++) {
                    if (i == insertionPoint)
                        a.append(separator);
                    a.append(string.charAt(i));
                }
            }
        }
    }

    /**
     * Append a positive {@code int} left-trimmed to an {@link Appendable}, using a scale parameter to indicate the
     * number of decimal places.  This method outputs the digits left to right, avoiding the need to allocate a separate
     * object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveIntScaled(Appendable a, int i, int scale, char separator) throws IOException {
        if (scale > 2) {
            int n = i / 100;
            appendPositiveIntScaled(a, n, scale - 2, separator);
            append2Digits(a, i - n * 100);
        }
        else if (scale == 2) {
            int n = i / 100;
            appendPositiveInt(a, n);
            a.append(separator);
            append2Digits(a, i - n * 100);
        }
        else if (scale == 1) {
            int n = i / 10;
            appendPositiveInt(a, n);
            a.append(separator);
            a.append(digits[i - n * 10]);
        }
        else if (i >= 100) {
            int n = i / 100;
            appendPositiveInt(a, n);
            append2Digits(a, i - n * 100);
        }
        else if (i >= 10)
            append2Digits(a, i);
        else
            a.append(digits[i]);
    }

    /**
     * Append a {@code long} left-trimmed to an {@link Appendable}.  This method outputs the digits left to right,
     * avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code long}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendLong(Appendable a, long n) throws IOException {
        if (n < 0) {
            a.append('-');
            if (n == Long.MIN_VALUE)
                a.append(MIN_LONG_DIGITS);
            else
                appendPositiveLong(a, -n);
        }
        else
            appendPositiveLong(a, n);
    }

    /**
     * Append a positive {@code long} left-trimmed to an {@link Appendable}.  This method outputs the digits left to
     * right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code long}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveLong(Appendable a, long n) throws IOException {
        if (n >= 100) {
            long m = n / 100;
            appendPositiveLong(a, m);
            append2Digits(a, (int)(n - m * 100));
        }
        else {
            int i = (int)n;
            if (i >= 10)
                a.append(tensDigits[i]);
            a.append(digits[i]);
        }
    }

    /**
     * Append an unsigned {@code long} left-trimmed to an {@link Appendable}.  This method outputs the digits left to
     * right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code long}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendUnsignedLong(Appendable a, long n) throws IOException {
        if (n >= 0)
            appendPositiveLong(a, n);
        else {
            long m = (n >>> 1) / 50;
            appendPositiveLong(a, m);
            append2Digits(a, (int)(n - m * 100));
        }
    }

    /**
     * Append an {@code int} left-trimmed to an {@link Appendable}, using a scale parameter to indicate the number of
     * decimal places.  This method outputs the digits left to right, avoiding the need to allocate a separate object to
     * hold the string form.
     * <br>
     * Negative scale values (indicating that decimal point is to the right of the last digit) are ignored.  It is left
     * to the user to decide whether to output additional zeros following the number, or to add an exponent suffix.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code long}
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendLongScaled(Appendable a, long n, int scale, char separator) throws IOException {
        if (n < 0) {
            a.append('-');
            if (n == Long.MIN_VALUE)
                appendStringScaled(a, MIN_LONG_DIGITS, scale, separator);
            else
                appendPositiveLongScaled(a, -n, scale, separator);
        }
        else
            appendPositiveLongScaled(a, n, scale, separator);
    }

    /**
     * Append a positive {@code long} left-trimmed to an {@link Appendable}, using a scale parameter to indicate the
     * number of decimal places.  This method outputs the digits left to right, avoiding the need to allocate a separate
     * object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code int}
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveLongScaled(Appendable a, long n, int scale, char separator) throws IOException {
        if (scale > 2) {
            long m = n / 100;
            appendPositiveLongScaled(a, m, scale - 2, separator);
            append2Digits(a, (int)(n - m * 100));
        }
        else if (scale == 2) {
            long m = n / 100;
            appendPositiveLong(a, m);
            a.append(separator);
            append2Digits(a, (int)(n - m * 100));
        }
        else if (scale == 1) {
            long m = n / 10;
            appendPositiveLong(a, m);
            a.append(separator);
            a.append(digits[(int)(n - m * 10)]);
        }
        else if (n >= 100) {
            long m = n / 100;
            appendPositiveLong(a, m);
            append2Digits(a, (int)(n - m * 100));
        }
        else {
            int i = (int)n;
            if (i >= 10)
                a.append(tensDigits[i]);
            a.append(digits[i]);
        }
    }

    /**
     * Append an {@code int} to an {@link Appendable} as two decimal digits.  There is often a requirement to output a
     * number as 2 digits, for example the cents value in dollars and cents, or hours, minutes and seconds in a time
     * string.  Note that there is no range check on the input value; to use this method in cases where the value is not
     * guaranteed to be in the range 00-99, use:
     * <pre>
     *     IntOutput.append2Digits(a, Math.abs(i) % 100);
     * </pre>
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append2Digits(Appendable a, int i) throws IOException {
        a.append(tensDigits[i]);
        a.append(digits[i]);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as three decimal digits.  There is less frequently a requirement
     * to output a number as 3 digits, for example the milliseconds in a time string.  Note that there is no range check
     * on the input value; to use this method in cases where the value is not guaranteed to be in the range 000-999,
     * use:
     * <pre>
     *     IntOutput.append3Digits(a, Math.abs(i) % 1000);
     * </pre>
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append3Digits(Appendable a, int i) throws IOException {
        int n = i / 100;
        a.append(digits[n]);
        append2Digits(a, i - n * 100);
    }

    /**
     * Append an {@code int} left-trimmed to an {@link Appendable} with digits grouped in 3s and separated by the
     * specified grouping character.
     *
     * @param   a               the {@link Appendable}
     * @param   i               the {@code int}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @throws IOException if thrown by the {@link Appendable}
     */
    public static void appendIntGrouped(Appendable a, int i, char groupingChar) throws IOException {
        if (i < 0) {
            a.append('-');
            if (i == Integer.MIN_VALUE) {
                a.append(MIN_INTEGER_DIGITS.charAt(0));
                a.append(groupingChar);
                a.append(MIN_INTEGER_DIGITS, 1, 4);
                a.append(groupingChar);
                a.append(MIN_INTEGER_DIGITS, 4, 7);
                a.append(groupingChar);
                a.append(MIN_INTEGER_DIGITS, 7, 10);
            }
            else
                appendPositiveIntGrouped(a, -i, groupingChar);
        }
        else
            appendPositiveIntGrouped(a, i, groupingChar);
    }

    /**
     * Append a positive {@code int} left-trimmed to an {@link Appendable} with digits grouped in 3s and separated by
     * the specified grouping character.
     *
     * @param   a               the {@link Appendable}
     * @param   i               the {@code int}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @throws IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveIntGrouped(Appendable a, int i, char groupingChar) throws IOException {
        if (i >= 100) {
            int n = i / 100;
            appendPositiveIntGrouped1(a, n, groupingChar);
            append2Digits(a, i - n * 100);
        }
        else if (i >= 10)
            append2Digits(a, i);
        else
            a.append(digits[i]);
    }

    private static void appendPositiveIntGrouped1(Appendable a, int i, char groupingChar) throws IOException {
        if (i >= 100) {
            int n = i / 100;
            appendPositiveIntGrouped2(a, n, groupingChar);
            i -= n * 100;
            a.append(tensDigits[i]);
            a.append(groupingChar);
            a.append(digits[i]);
        }
        else if (i >= 10) {
            a.append(tensDigits[i]);
            a.append(groupingChar);
            a.append(digits[i]);
        }
        else
            a.append(digits[i]);
    }

    private static void appendPositiveIntGrouped2(Appendable a, int i, char groupingChar) throws IOException {
        if (i >= 100) {
            int n = i / 100;
            appendPositiveIntGrouped(a, n, groupingChar);
            a.append(groupingChar);
            append2Digits(a, i - n * 100);
        }
        else if (i >= 10)
            append2Digits(a, i);
        else
            a.append(digits[i]);
    }

    /**
     * Append a {@code long} left-trimmed to an {@link Appendable} with digits grouped in 3s and separated by the
     * specified grouping character.
     *
     * @param   a               the {@link Appendable}
     * @param   n               the {@code long}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @throws IOException if thrown by the {@link Appendable}
     */
    public static void appendLongGrouped(Appendable a, long n, char groupingChar) throws IOException {
        if (n < 0) {
            a.append('-');
            if (n == Long.MIN_VALUE) {
                a.append(MIN_LONG_DIGITS.charAt(0));
                a.append(groupingChar);
                a.append(MIN_LONG_DIGITS, 1, 4);
                a.append(groupingChar);
                a.append(MIN_LONG_DIGITS, 4, 7);
                a.append(groupingChar);
                a.append(MIN_LONG_DIGITS, 7, 10);
                a.append(groupingChar);
                a.append(MIN_LONG_DIGITS, 10, 13);
                a.append(groupingChar);
                a.append(MIN_LONG_DIGITS, 13, 16);
                a.append(groupingChar);
                a.append(MIN_LONG_DIGITS, 16, 19);
            }
            else
                appendPositiveLongGrouped(a, -n, groupingChar);
        }
        else
            appendPositiveLongGrouped(a, n, groupingChar);
    }

    /**
     * Append a positive {@code long} left-trimmed to an {@link Appendable} with digits grouped in 3s and separated by
     * the specified grouping character.
     *
     * @param   a               the {@link Appendable}
     * @param   n               the {@code long}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveLongGrouped(Appendable a, long n, char groupingChar) throws IOException {
        if (n >= 100) {
            long m = n / 100;
            appendPositiveLongGrouped1(a, m, groupingChar);
            append2Digits(a, (int)(n - m * 100));
        }
        else {
            int i = (int)n;
            if (i >= 10)
                a.append(tensDigits[i]);
            a.append(digits[i]);
        }
    }

    private static void appendPositiveLongGrouped1(Appendable a, long n, char groupingChar) throws IOException {
        if (n >= 100) {
            long m = n / 100;
            appendPositiveLongGrouped2(a, m, groupingChar);
            int i = (int)(n - m * 100);
            a.append(tensDigits[i]);
            a.append(groupingChar);
            a.append(digits[i]);
        }
        else {
            int i = (int)n;
            if (i >= 10) {
                a.append(tensDigits[i]);
                a.append(groupingChar);
            }
            a.append(digits[i]);
        }
    }

    private static void appendPositiveLongGrouped2(Appendable a, long n, char groupingChar) throws IOException {
        if (n >= 100) {
            long m = n / 100;
            appendPositiveLongGrouped(a, m, groupingChar);
            a.append(groupingChar);
            append2Digits(a, (int)(n - m * 100));
        }
        else {
            int i = (int)n;
            if (i >= 10)
                a.append(tensDigits[i]);
            a.append(digits[i]);
        }
    }

    /**
     * Append an {@code int} left-trimmed to an {@link Appendable} in hexadecimal.  This method outputs the digits left
     * to right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendIntHex(Appendable a, int i) throws IOException {
        if ((i & ~0xFFFF) != 0) {
            append16BitsHex(a, i >>> 16);
            append4Hex(a, i);
        }
        else
            append16BitsHex(a, i);
    }

    private static void append8BitsHex(Appendable a, int i) throws IOException {
        if ((i & ~0xF) != 0)
            a.append(digitsHex[i >>> 4]);
        a.append(digitsHex[i & 0xF]);
    }

    private static void append16BitsHex(Appendable a, int i) throws IOException {
        if ((i & ~0xFF) != 0) {
            append8BitsHex(a, i >>> 8);
            append2Hex(a, i);
        }
        else
            append8BitsHex(a, i);
    }

    /**
     * Append an {@code int} left-trimmed to an {@link Appendable} in hexadecimal, using lower-case for the alphabetic
     * characters.  This method outputs the digits left to right, avoiding the need to allocate a separate object to
     * hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendIntHexLC(Appendable a, int i) throws IOException {
        if ((i & ~0xFFFF) != 0) {
            append16BitsHexLC(a, i >>> 16);
            append4HexLC(a, i);
        }
        else
            append16BitsHexLC(a, i);
    }

    private static void append8BitsHexLC(Appendable a, int i) throws IOException {
        if ((i & ~0xF) != 0)
            a.append(digitsHexLC[i >>> 4]);
        a.append(digitsHexLC[i & 0xF]);
    }

    private static void append16BitsHexLC(Appendable a, int i) throws IOException {
        if ((i & ~0xFF) != 0) {
            append8BitsHexLC(a, i >>> 8);
            append2HexLC(a, i);
        }
        else
            append8BitsHexLC(a, i);
    }

    /**
     * Append a {@code long} left-trimmed to an {@link Appendable} in hexadecimal.  This method outputs the digits left
     * to right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code long}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendLongHex(Appendable a, long n) throws IOException {
        int hi = (int)(n >>> 32);
        int lo = (int)n;
        if (hi != 0) {
            appendIntHex(a, hi);
            append8Hex(a, lo);
        }
        else
            appendIntHex(a, lo);
    }

    /**
     * Append a {@code long} left-trimmed to an {@link Appendable} in hexadecimal, using lower-case for the alphabetic
     * characters.  This method outputs the digits left to right, avoiding the need to allocate a separate object to
     * hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code long}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendLongHexLC(Appendable a, long n) throws IOException {
        int hi = (int)(n >>> 32);
        int lo = (int)n;
        if (hi != 0) {
            appendIntHexLC(a, hi);
            append8HexLC(a, lo);
        }
        else
            appendIntHexLC(a, lo);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as eight hexadecimal digits.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append8Hex(Appendable a, int i) throws IOException {
        append4Hex(a, i >>> 16);
        append4Hex(a, i);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as eight hexadecimal digits, using lower-case for the alphabetic
     * characters.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append8HexLC(Appendable a, int i) throws IOException {
        append4HexLC(a, i >>> 16);
        append4HexLC(a, i);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as four hexadecimal digits.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append4Hex(Appendable a, int i) throws IOException {
        append2Hex(a, i >>> 8);
        append2Hex(a, i);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as four hexadecimal digits, using lower-case for the alphabetic
     * characters.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append4HexLC(Appendable a, int i) throws IOException {
        append2HexLC(a, i >>> 8);
        append2HexLC(a, i);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as two hexadecimal digits.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append2Hex(Appendable a, int i) throws IOException {
        a.append(digitsHex[(i >> 4) & 0xF]);
        a.append(digitsHex[i & 0xF]);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as two hexadecimal digits, using lower-case for the alphabetic
     * characters.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append2HexLC(Appendable a, int i) throws IOException {
        a.append(digitsHexLC[(i >> 4) & 0xF]);
        a.append(digitsHexLC[i & 0xF]);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as a single hexadecimal digit.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append1Hex(Appendable a, int i) throws IOException {
        a.append(digitsHex[i & 0xF]);
    }

    /**
     * Append an {@code int} to an {@link Appendable} as a single hexadecimal digit, using lower-case for the alphabetic
     * characters.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append1HexLC(Appendable a, int i) throws IOException {
        a.append(digitsHexLC[i & 0xF]);
    }

    /**
     * Output an {@code int} left-trimmed using an {@link IntConsumer}.  This method outputs the digits left to right,
     * avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputInt(int i, IntConsumer consumer) {
        if (i < 0) {
            consumer.accept('-');
            if (i == Integer.MIN_VALUE)
                outputString(MIN_INTEGER_DIGITS, consumer);
            else
                outputPositiveInt(-i, consumer);
        }
        else
            outputPositiveInt(i, consumer);
    }

    /**
     * Output a positive {@code int} left-trimmed using an {@link IntConsumer}.  This method outputs the digits left to
     * right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputPositiveInt(int i, IntConsumer consumer) {
        if (i >= 100) {
            int n = i / 100;
            outputPositiveInt(n, consumer);
            output2Digits(i - n * 100, consumer);
        }
        else if (i >= 10)
            output2Digits(i, consumer);
        else
            consumer.accept(digits[i]);
    }

    /**
     * Output an unsigned {@code int} left-trimmed using an {@link IntConsumer}.  This method outputs the digits left to
     * right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputUnsignedInt(int i, IntConsumer consumer) {
        if (i >= 0)
            outputPositiveInt(i, consumer);
        else {
            int n = (i >>> 1) / 50;
            outputPositiveInt(n, consumer);
            output2Digits(i - n * 100, consumer);
        }
    }

    /**
     * Output an {@code int} left-trimmed using an {@link IntConsumer}, using a scale parameter to indicate the number
     * of decimal places.  This method outputs the digits left to right, avoiding the need to allocate a separate object
     * to hold the string form.
     *
     * @param   i           the {@code int}
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputIntScaled(int i, int scale, char separator, IntConsumer consumer) {
        if (i < 0) {
            consumer.accept('-');
            if (i == Integer.MIN_VALUE)
                outputStringScaled(MIN_INTEGER_DIGITS, scale, separator, consumer);
            else
                outputPositiveIntScaled(-i, scale, separator, consumer);
        }
        else
            outputPositiveIntScaled(i, scale, separator, consumer);
    }

    /**
     * Output a given string (representing a decimal value) using an {@link IntConsumer}, using a scale parameter to
     * indicate the number of decimal places.

     * @param   string      the string of digits
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @param   consumer    the {@link IntConsumer}
     */
    private static void outputStringScaled(String string, int scale, char separator, IntConsumer consumer) {
        if (scale <= 0)
            outputString(string, consumer);
        else {
            int length = string.length();
            if (scale >= length) {
                consumer.accept('0');
                consumer.accept(separator);
                for (int i = scale; i > length; i--)
                    consumer.accept('0');
                outputString(string, consumer);
            }
            else {
                int insertionPoint = length - scale;
                for (int i = 0; i < length; i++) {
                    if (i == insertionPoint)
                        consumer.accept(separator);
                    consumer.accept(string.charAt(i));
                }
            }
        }
    }

    /**
     * Output a positive {@code int} left-trimmed using an {@link IntConsumer}, using a scale parameter to indicate the
     * number of decimal places.  This method outputs the digits left to right, avoiding the need to allocate a separate
     * object to hold the string form.
     *
     * @param   i           the {@code int}
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputPositiveIntScaled(int i, int scale, char separator, IntConsumer consumer) {
        if (scale > 2) {
            int n = i / 100;
            outputPositiveIntScaled(n, scale - 2, separator, consumer);
            output2Digits(i - n * 100, consumer);
        }
        else if (scale == 2) {
            int n = i / 100;
            outputPositiveInt(n, consumer);
            consumer.accept(separator);
            output2Digits(i - n * 100, consumer);
        }
        else if (scale == 1) {
            int n = i / 10;
            outputPositiveInt(n, consumer);
            consumer.accept(separator);
            consumer.accept(digits[i - n * 10]);
        }
        else if (i >= 100) {
            int n = i / 100;
            outputPositiveInt(n, consumer);
            output2Digits(i - n * 100, consumer);
        }
        else if (i >= 10)
            output2Digits(i, consumer);
        else
            consumer.accept(digits[i]);
    }

    /**
     * Output a {@code long} left-trimmed using an {@link IntConsumer}.  This method outputs the digits left to right,
     * avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   n           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputLong(long n, IntConsumer consumer) {
        if (n < 0) {
            consumer.accept('-');
            if (n == Long.MIN_VALUE)
                outputString(MIN_LONG_DIGITS, consumer);
            else
                outputPositiveLong(-n, consumer);
        }
        else
            outputPositiveLong(n, consumer);
    }

    /**
     * Output a positive {@code int} left-trimmed using an {@link IntConsumer}.  This method outputs the digits left to
     * right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   n           the {@code long}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputPositiveLong(long n, IntConsumer consumer) {
        if (n >= 100) {
            long m = n / 100;
            outputPositiveLong(m, consumer);
            output2Digits((int)(n - m * 100), consumer);
        }
        else {
            int i = (int)n;
            if (i >= 10)
                consumer.accept(tensDigits[i]);
            consumer.accept(digits[i]);
        }
    }

    /**
     * Output an unsigned {@code long} left-trimmed using an {@link IntConsumer}.  This method outputs the digits left
     * to right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   n           the {@code long}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputUnsignedLong(long n, IntConsumer consumer) {
        if (n >= 0)
            outputPositiveLong(n, consumer);
        else {
            long m = (n >>> 1) / 50;
            outputPositiveLong(m, consumer);
            output2Digits((int)(n - m * 100), consumer);
        }
    }

    /**
     * Append an {@code int} left-trimmed to an {@link Appendable}, using a scale parameter to indicate the number of
     * decimal places.  This method outputs the digits left to right, avoiding the need to allocate a separate object to
     * hold the string form.
     *
     * @param   n           the {@code long}
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputLongScaled(long n, int scale, char separator, IntConsumer consumer) {
        if (n < 0) {
            consumer.accept('-');
            if (n == Long.MIN_VALUE)
                outputStringScaled(MIN_LONG_DIGITS, scale, separator, consumer);
            else
                outputPositiveLongScaled(-n, scale, separator, consumer);
        }
        else
            outputPositiveLongScaled(n, scale, separator, consumer);
    }

    /**
     * Append a positive {@code long} left-trimmed to an {@link Appendable}, using a scale parameter to indicate the
     * number of decimal places.  This method outputs the digits left to right, avoiding the need to allocate a separate
     * object to hold the string form.
     *
     * @param   n           the {@code int}
     * @param   scale       the number of decimal places
     * @param   separator   the decimal separator character to use
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputPositiveLongScaled(long n, int scale, char separator, IntConsumer consumer) {
        if (scale > 2) {
            long m = n / 100;
            outputPositiveLongScaled(m, scale - 2, separator, consumer);
            output2Digits((int)(n - m * 100), consumer);
        }
        else if (scale == 2) {
            long m = n / 100;
            outputPositiveLong(m, consumer);
            consumer.accept(separator);
            output2Digits((int)(n - m * 100), consumer);
        }
        else if (scale == 1) {
            long m = n / 10;
            outputPositiveLong(m, consumer);
            consumer.accept(separator);
            consumer.accept(digits[(int)(n - m * 10)]);
        }
        else if (n >= 100) {
            long m = n / 100;
            outputPositiveLong(m, consumer);
            output2Digits((int)(n - m * 100), consumer);
        }
        else {
            int i = (int)n;
            if (i >= 10)
                consumer.accept(tensDigits[i]);
            consumer.accept(digits[i]);
        }
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as two decimal digits.  There is often a requirement to output
     * a number as 2 digits, for example the cents value in dollars and cents, or hours, minutes and seconds in a time
     * string.  Note that there is no range check on the input value; to use this method in cases where the value is not
     * guaranteed to be in the range 00-99, use:
     * <pre>
     *     IntOutput.output2Digits(Math.abs(i) % 100, ch -&gt; sb.append((char)ch));
     * </pre>
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output2Digits(int i, IntConsumer consumer) {
        consumer.accept(tensDigits[i]);
        consumer.accept(digits[i]);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as three decimal digits.  There is less frequently a
     * requirement to output a number as 3 digits, for example the milliseconds in a time string.  Note that there is no
     * range check on the input value; to use this method in cases where the value is not guaranteed to be in the range
     * 000-999, use:
     * <pre>
     *     IntOutput.output3Digits(Math.abs(i) % 1000, ch -&gt; sb.append((char)ch));
     * </pre>
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output3Digits(int i, IntConsumer consumer) {
        int n = i / 100;
        consumer.accept(digits[n]);
        output2Digits(i - n * 100, consumer);
    }

    /**
     * Output an {@code int} left-trimmed using an {@link IntConsumer} with digits grouped in 3s and separated by the
     * specified grouping character.
     *
     * @param   i               the {@code int}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @param   consumer        the {@link IntConsumer}
     */
    public static void outputIntGrouped(int i, char groupingChar, IntConsumer consumer) {
        if (i < 0) {
            consumer.accept('-');
            if (i == Integer.MIN_VALUE) {
                consumer.accept(MIN_INTEGER_DIGITS.charAt(0));
                consumer.accept(groupingChar);
                outputString(MIN_INTEGER_DIGITS, 1, 4, consumer);
                consumer.accept(groupingChar);
                outputString(MIN_INTEGER_DIGITS, 4, 7, consumer);
                consumer.accept(groupingChar);
                outputString(MIN_INTEGER_DIGITS, 7, 10, consumer);
            }
            else
                outputPositiveIntGrouped(-i, groupingChar, consumer);
        }
        else
            outputPositiveIntGrouped(i, groupingChar, consumer);
    }

    /**
     * Output a positive {@code int} left-trimmed using an {@link IntConsumer} with digits grouped in 3s and separated
     * by the specified grouping character.
     *
     * @param   i               the {@code int}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @param   consumer        the {@link IntConsumer}
     */
    public static void outputPositiveIntGrouped(int i, char groupingChar, IntConsumer consumer) {
        if (i >= 100) {
            int n = i / 100;
            outputPositiveIntGrouped1(n, groupingChar, consumer);
            output2Digits(i - n * 100, consumer);
        }
        else if (i >= 10)
            output2Digits(i, consumer);
        else
            consumer.accept(digits[i]);
    }

    private static void outputPositiveIntGrouped1(int i, char groupingChar, IntConsumer consumer) {
        if (i >= 100) {
            int n = i / 100;
            outputPositiveIntGrouped2(n, groupingChar, consumer);
            i -= n * 100;
            consumer.accept(tensDigits[i]);
            consumer.accept(groupingChar);
            consumer.accept(digits[i]);
        }
        else if (i >= 10) {
            consumer.accept(tensDigits[i]);
            consumer.accept(groupingChar);
            consumer.accept(digits[i]);
        }
        else
            consumer.accept(digits[i]);
    }

    private static void outputPositiveIntGrouped2(int i, char groupingChar, IntConsumer consumer) {
        if (i >= 100) {
            int n = i / 100;
            outputPositiveIntGrouped(n, groupingChar, consumer);
            consumer.accept(groupingChar);
            output2Digits(i - n * 100, consumer);
        }
        else if (i >= 10)
            output2Digits(i, consumer);
        else
            consumer.accept(digits[i]);
    }

    /**
     * Output a {@code long} left-trimmed using an {@link IntConsumer} with digits grouped in 3s and separated by the
     * specified grouping character.
     *
     * @param   n               the {@code long}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @param   consumer        the {@link IntConsumer}
     */
    public static void outputLongGrouped(long n, char groupingChar, IntConsumer consumer) {
        if (n < 0) {
            consumer.accept('-');
            if (n == Long.MIN_VALUE) {
                consumer.accept(MIN_LONG_DIGITS.charAt(0));
                consumer.accept(groupingChar);
                outputString(MIN_LONG_DIGITS, 1, 4, consumer);
                consumer.accept(groupingChar);
                outputString(MIN_LONG_DIGITS, 4, 7, consumer);
                consumer.accept(groupingChar);
                outputString(MIN_LONG_DIGITS, 7, 10, consumer);
                consumer.accept(groupingChar);
                outputString(MIN_LONG_DIGITS, 10, 13, consumer);
                consumer.accept(groupingChar);
                outputString(MIN_LONG_DIGITS, 13, 16, consumer);
                consumer.accept(groupingChar);
                outputString(MIN_LONG_DIGITS, 16, 19, consumer);
            }
            else
                outputPositiveLongGrouped(-n, groupingChar, consumer);
        }
        else
            outputPositiveLongGrouped(n, groupingChar, consumer);
    }

    /**
     * Output a positive {@code long} left-trimmed using an {@link IntConsumer} with digits grouped in 3s and separated
     * by the specified grouping character.
     *
     * @param   n               the {@code long}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @param   consumer        the {@link IntConsumer}
     */
    public static void outputPositiveLongGrouped(long n, char groupingChar, IntConsumer consumer) {
        if (n >= 100) {
            long m = n / 100;
            outputPositiveLongGrouped1(m, groupingChar, consumer);
            output2Digits((int)(n - m * 100), consumer);
        }
        else {
            int i = (int)n;
            if (i >= 10)
                consumer.accept(tensDigits[i]);
            consumer.accept(digits[i]);
        }
    }

    private static void outputPositiveLongGrouped1(long n, char groupingChar, IntConsumer consumer) {
        if (n >= 100) {
            long m = n / 100;
            outputPositiveLongGrouped2(m, groupingChar, consumer);
            int i = (int)(n - m * 100);
            consumer.accept(tensDigits[i]);
            consumer.accept(groupingChar);
            consumer.accept(digits[i]);
        }
        else {
            int i = (int)n;
            if (i >= 10) {
                consumer.accept(tensDigits[i]);
                consumer.accept(groupingChar);
            }
            consumer.accept(digits[i]);
        }
    }

    private static void outputPositiveLongGrouped2(long n, char groupingChar, IntConsumer consumer) {
        if (n >= 100) {
            long m = n / 100;
            outputPositiveLongGrouped(m, groupingChar, consumer);
            consumer.accept(groupingChar);
            output2Digits((int)(n - m * 100), consumer);
        }
        else {
            int i = (int)n;
            if (i >= 10)
                consumer.accept(tensDigits[i]);
            consumer.accept(digits[i]);
        }
    }

    /**
     * Output an {@code int} left-trimmed using an {@link IntConsumer} in hexadecimal.  This method outputs the digits
     * left to right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputIntHex(int i, IntConsumer consumer) {
        if ((i & ~0xFFFF) != 0) {
            output16BitsHex(i >>> 16, consumer);
            output4Hex(i, consumer);
        }
        else
            output16BitsHex(i, consumer);
    }

    private static void output8BitsHex(int i, IntConsumer consumer) {
        if ((i & ~0xF) != 0)
            consumer.accept(digitsHex[i >>> 4]);
        consumer.accept(digitsHex[i & 0xF]);
    }

    private static void output16BitsHex(int i, IntConsumer consumer) {
        if ((i & ~0xFF) != 0) {
            output8BitsHex(i >>> 8, consumer);
            output2Hex(i, consumer);
        }
        else
            output8BitsHex(i, consumer);
    }

    /**
     * Output an {@code int} left-trimmed using an {@link IntConsumer} in hexadecimal, using lower-case for the
     * alphabetic characters.  This method outputs the digits left to right, avoiding the need to allocate a separate
     * object to hold the string form.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputIntHexLC(int i, IntConsumer consumer) {
        if ((i & ~0xFFFF) != 0) {
            output16BitsHexLC(i >>> 16, consumer);
            output4HexLC(i, consumer);
        }
        else
            output16BitsHexLC(i, consumer);
    }

    private static void output8BitsHexLC(int i, IntConsumer consumer) {
        if ((i & ~0xF) != 0)
            consumer.accept(digitsHexLC[i >>> 4]);
        consumer.accept(digitsHexLC[i & 0xF]);
    }

    private static void output16BitsHexLC(int i, IntConsumer consumer) {
        if ((i & ~0xFF) != 0) {
            output8BitsHexLC(i >>> 8, consumer);
            output2HexLC(i, consumer);
        }
        else
            output8BitsHexLC(i, consumer);
    }

    /**
     * Output a {@code long} left-trimmed using an {@link IntConsumer} in hexadecimal.  This method outputs the digits
     * left to right, avoiding the need to allocate a separate object to hold the string form.
     *
     * @param   n           the {@code long}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputLongHex(long n, IntConsumer consumer) {
        int hi = (int)(n >>> 32);
        int lo = (int)n;
        if (hi != 0) {
            outputIntHex(hi, consumer);
            output8Hex(lo, consumer);
        }
        else
            outputIntHex(lo, consumer);
    }

    /**
     * Output a {@code long} left-trimmed using an {@link IntConsumer} in hexadecimal, using lower-case for the
     * alphabetic characters.  This method outputs the digits left to right, avoiding the need to allocate a separate
     * object to hold the string form.
     *
     * @param   n           the {@code long}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void outputLongHexLC(long n, IntConsumer consumer) {
        int hi = (int)(n >>> 32);
        int lo = (int)n;
        if (hi != 0) {
            outputIntHexLC(hi, consumer);
            output8HexLC(lo, consumer);
        }
        else
            outputIntHexLC(lo, consumer);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as eight hexadecimal digits.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output8Hex(int i, IntConsumer consumer) {
        output4Hex(i >>> 16, consumer);
        output4Hex(i, consumer);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as eight hexadecimal digits, using lower-case for the
     * alphabetic characters.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output8HexLC(int i, IntConsumer consumer) {
        output4HexLC(i >>> 16, consumer);
        output4HexLC(i, consumer);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as four hexadecimal digits.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output4Hex(int i, IntConsumer consumer) {
        output2Hex(i >> 8, consumer);
        output2Hex(i, consumer);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as four hexadecimal digits, using lower-case for the
     * alphabetic characters.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output4HexLC(int i, IntConsumer consumer) {
        output2HexLC(i >> 8, consumer);
        output2HexLC(i, consumer);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as two hexadecimal digits.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output2Hex(int i, IntConsumer consumer) {
        consumer.accept(digitsHex[(i >> 4) & 0xF]);
        consumer.accept(digitsHex[i & 0xF]);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as two hexadecimal digits, using lower-case for the alphabetic
     * characters.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output2HexLC(int i, IntConsumer consumer) {
        consumer.accept(digitsHexLC[(i >> 4) & 0xF]);
        consumer.accept(digitsHexLC[i & 0xF]);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as a single hexadecimal digit.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output1Hex(int i, IntConsumer consumer) {
        consumer.accept(digitsHex[i & 0xF]);
    }

    /**
     * Output an {@code int} using an {@link IntConsumer} as a single hexadecimal digit, using lower-case for the
     * alphabetic characters.
     *
     * @param   i           the {@code int}
     * @param   consumer    the {@link IntConsumer}
     */
    public static void output1HexLC(int i, IntConsumer consumer) {
        consumer.accept(digitsHexLC[i & 0xF]);
    }

    private static void outputString(String s, int start, int end, IntConsumer consumer) {
        for (int i = start; i < end; i++)
            consumer.accept(s.charAt(i));
    }

    private static void outputString(String s, IntConsumer consumer) {
        outputString(s, 0, s.length(), consumer);
    }

}
