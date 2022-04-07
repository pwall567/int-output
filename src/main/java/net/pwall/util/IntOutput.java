/*
 * @(#) IntOutput.java
 *
 * int-output  Integer output functions
 * Copyright (c) 2021, 2022 Peter Wall
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

    /**
     * Append an {@code int} to an {@link Appendable}.  This method outputs the digits left to right, avoiding the need
     * to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws IOException if thrown by the {@link Appendable}
     */
    public static void appendInt(Appendable a, int i) throws IOException {
        if (i < 0) {
            if (i == Integer.MIN_VALUE)
                a.append("-2147483648");
            else {
                a.append('-');
                appendPositiveInt(a, -i);
            }
        }
        else
            appendPositiveInt(a, i);
    }

    /**
     * Append a positive {@code int} to an {@link Appendable}.  This method outputs the digits left to right, avoiding
     * the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveInt(Appendable a, int i) throws IOException {
        if (i >= 100) {
            int n = i / 100;
            appendPositiveInt(a, n);
            i -= n * 100;
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
        else if (i >= 10) {
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
        else
            a.append(digits[i]);
    }

    /**
     * Append an unsigned {@code int} to an {@link Appendable}.  This method outputs the digits left to right, avoiding
     * the need to allocate a separate object to hold the string form.
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
            i -= n * 100;
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
    }

    /**
     * Append a {@code long} to an {@link Appendable}.  This method outputs the digits left to right, avoiding the need
     * to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code long}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendLong(Appendable a, long n) throws IOException {
        if (n < 0) {
            if (n == Long.MIN_VALUE)
                a.append("-9223372036854775808");
            else {
                a.append('-');
                appendPositiveLong(a, -n);
            }
        }
        else
            appendPositiveLong(a, n);
    }

    /**
     * Append a positive {@code long} to an {@link Appendable}.  This method outputs the digits left to right, avoiding
     * the need to allocate a separate object to hold the string form.
     *
     * @param   a           the {@link Appendable}
     * @param   n           the {@code long}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveLong(Appendable a, long n) throws IOException {
        if (n >= 100) {
            long m = n / 100;
            appendPositiveLong(a, m);
            int i = (int)(n - m * 100);
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
        else {
            int i = (int)n;
            if (i >= 10)
                a.append(tensDigits[i]);
            a.append(digits[i]);
        }
    }

    /**
     * Append an unsigned {@code long} to an {@link Appendable}.  This method outputs the digits left to right, avoiding
     * the need to allocate a separate object to hold the string form.
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
            int i = (int)(n - m * 100);
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
     *     Strings.append2Digits(a, Math.abs(i) % 100);
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
     *     Strings.append3Digits(a, Math.abs(i) % 1000);
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
     * Append an {@code int} to an {@link Appendable} as four hexadecimal digits.
     *
     * @param   a           the {@link Appendable}
     * @param   i           the {@code int}
     * @throws  IOException if thrown by the {@link Appendable}
     */
    public static void append4Hex(Appendable a, int i) throws IOException {
        append2Hex(a, i >> 8);
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
        append2HexLC(a, i >> 8);
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
     * Append an {@code int} to an {@link Appendable} with digits grouped in 3s and separated by the specified grouping
     * character.
     *
     * @param   a               the {@link Appendable}
     * @param   i               the {@code int}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @throws IOException if thrown by the {@link Appendable}
     */
    public static void appendIntGrouped(Appendable a, int i, char groupingChar) throws IOException {
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                a.append("-2");
                a.append(groupingChar);
                a.append("147");
                a.append(groupingChar);
                a.append("483");
                a.append(groupingChar);
                a.append("648");
            }
            else {
                a.append('-');
                appendPositiveIntGrouped(a, -i, groupingChar);
            }
        }
        else
            appendPositiveIntGrouped(a, i, groupingChar);
    }

    /**
     * Append a positive {@code int} to an {@link Appendable} with digits grouped in 3s and separated by the specified
     * grouping character.
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
            i -= n * 100;
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
        else if (i >= 10) {
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
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
            i -= n * 100;
            a.append(groupingChar);
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
        else if (i >= 10) {
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
        else
            a.append(digits[i]);
    }

    /**
     * Append a {@code long} to an {@link Appendable} with digits grouped in 3s and separated by the specified grouping
     * character.
     *
     * @param   a               the {@link Appendable}
     * @param   n               the {@code long}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @throws IOException if thrown by the {@link Appendable}
     */
    public static void appendLongGrouped(Appendable a, long n, char groupingChar) throws IOException {
        if (n < 0) {
            if (n == Long.MIN_VALUE) {
                a.append("-9");
                a.append(groupingChar);
                a.append("223");
                a.append(groupingChar);
                a.append("372");
                a.append(groupingChar);
                a.append("036");
                a.append(groupingChar);
                a.append("854");
                a.append(groupingChar);
                a.append("775");
                a.append(groupingChar);
                a.append("808");
            }
            else {
                a.append('-');
                appendPositiveLongGrouped(a, -n, groupingChar);
            }
        }
        else
            appendPositiveLongGrouped(a, n, groupingChar);
    }

    /**
     * Append a positive {@code long} to an {@link Appendable} with digits grouped in 3s and separated by the specified
     * grouping character.
     *
     * @param   a               the {@link Appendable}
     * @param   n               the {@code long}
     * @param   groupingChar    the grouping character (e.g. ',')
     * @throws IOException if thrown by the {@link Appendable}
     */
    public static void appendPositiveLongGrouped(Appendable a, long n, char groupingChar) throws IOException {
        if (n >= 100) {
            long m = n / 100;
            appendPositiveLongGrouped1(a, m, groupingChar);
            int i = (int)(n - m * 100);
            a.append(tensDigits[i]);
            a.append(digits[i]);
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
            int i = (int)(n - m * 100);
            a.append(groupingChar);
            a.append(tensDigits[i]);
            a.append(digits[i]);
        }
        else {
            int i = (int)n;
            if (i >= 10)
                a.append(tensDigits[i]);
            a.append(digits[i]);
        }
    }

}
