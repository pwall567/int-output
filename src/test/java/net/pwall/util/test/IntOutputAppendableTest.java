/*
 * @(#) IntOutputAppendableTest.java
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

package net.pwall.util.test;

import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import net.pwall.util.IntOutput;

public class IntOutputAppendableTest {

    @Test
    public void shouldConvertIntCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendInt(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendInt(sb, 123456);
        assertEquals("123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendInt(sb, -22334455);
        assertEquals("-22334455", sb.toString());
        sb.setLength(0);
        IntOutput.appendInt(sb, Integer.MAX_VALUE);
        assertEquals("2147483647", sb.toString());
        sb.setLength(0);
        IntOutput.appendInt(sb, Integer.MIN_VALUE);
        assertEquals("-2147483648", sb.toString());
    }

    @Test
    public void shouldConvertUnsignedIntCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendUnsignedInt(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendUnsignedInt(sb, 123456);
        assertEquals("123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendUnsignedInt(sb, (int)2147483648L);
        assertEquals("2147483648", sb.toString());
        sb.setLength(0);
        IntOutput.appendUnsignedInt(sb, (int)3456789012L);
        assertEquals("3456789012", sb.toString());
        sb.setLength(0);
        IntOutput.appendUnsignedInt(sb, 0x89ABCDEF);
        assertEquals("2309737967", sb.toString());
    }

    @Test
    public void shouldConvertIntScaled() throws IOException {
        char separator = '.';
        StringBuilder sb = new StringBuilder();
        IntOutput.appendIntScaled(sb, 0, 0, separator);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 0, 1, separator);
        assertEquals("0.0", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 0, 2, separator);
        assertEquals("0.00", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 0, 3, separator);
        assertEquals("0.000", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 123456, 0, separator);
        assertEquals("123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 123456, 1, separator);
        assertEquals("12345.6", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 123456, 2, separator);
        assertEquals("1234.56", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 123456, 3, separator);
        assertEquals("123.456", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 123456, 6, separator);
        assertEquals("0.123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 123456, 7, separator);
        assertEquals("0.0123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, 123456, 8, separator);
        assertEquals("0.00123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, -22334455, 0, separator);
        assertEquals("-22334455", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, -22334455, 1, separator);
        assertEquals("-2233445.5", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, -22334455, 2, separator);
        assertEquals("-223344.55", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, -22334455, 3, separator);
        assertEquals("-22334.455", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MAX_VALUE, 0, separator);
        assertEquals("2147483647", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MAX_VALUE, 1, separator);
        assertEquals("214748364.7", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MAX_VALUE, 2, separator);
        assertEquals("21474836.47", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MAX_VALUE, 3, separator);
        assertEquals("2147483.647", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MIN_VALUE, 0, separator);
        assertEquals("-2147483648", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MIN_VALUE, 1, separator);
        assertEquals("-214748364.8", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MIN_VALUE, 2, separator);
        assertEquals("-21474836.48", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MIN_VALUE, 3, separator);
        assertEquals("-2147483.648", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MIN_VALUE, 10, separator);
        assertEquals("-0.2147483648", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MIN_VALUE, 11, separator);
        assertEquals("-0.02147483648", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntScaled(sb, Integer.MIN_VALUE, 12, separator);
        assertEquals("-0.002147483648", sb.toString());
    }

    @Test
    public void shouldConvertLongCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendLong(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendLong(sb, 123456789012345678L);
        assertEquals("123456789012345678", sb.toString());
        sb.setLength(0);
        IntOutput.appendLong(sb, -2233445566778899L);
        assertEquals("-2233445566778899", sb.toString());
        sb.setLength(0);
        IntOutput.appendLong(sb, Integer.MAX_VALUE);
        assertEquals("2147483647", sb.toString());
        sb.setLength(0);
        IntOutput.appendLong(sb, Integer.MIN_VALUE);
        assertEquals("-2147483648", sb.toString());
        sb.setLength(0);
        IntOutput.appendLong(sb, Long.MAX_VALUE);
        assertEquals("9223372036854775807", sb.toString());
        sb.setLength(0);
        IntOutput.appendLong(sb, Long.MIN_VALUE);
        assertEquals("-9223372036854775808", sb.toString());
    }

    @Test
    public void shouldConvertUnsignedLongCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendUnsignedLong(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendUnsignedLong(sb, 1234567890123456789L);
        assertEquals("1234567890123456789", sb.toString());
        sb.setLength(0);
        long n = Long.MAX_VALUE;
        IntOutput.appendUnsignedLong(sb, n + 1);
        assertEquals("9223372036854775808", sb.toString());
        sb.setLength(0);
        n = 1234567890123456789L;
        IntOutput.appendUnsignedLong(sb, n * 10);
        assertEquals("12345678901234567890", sb.toString());
    }

    @Test
    public void shouldConvertLongScaled() throws IOException {
        char separator = '.';
        StringBuilder sb = new StringBuilder();
        IntOutput.appendLongScaled(sb, 0, 0, separator);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 0, 1, separator);
        assertEquals("0.0", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 0, 2, separator);
        assertEquals("0.00", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 0, 3, separator);
        assertEquals("0.000", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456, 0, separator);
        assertEquals("123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456, 1, separator);
        assertEquals("12345.6", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456, 2, separator);
        assertEquals("1234.56", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456, 3, separator);
        assertEquals("123.456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456, 6, separator);
        assertEquals("0.123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456, 7, separator);
        assertEquals("0.0123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456, 8, separator);
        assertEquals("0.00123456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, -22334455, 0, separator);
        assertEquals("-22334455", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, -22334455, 1, separator);
        assertEquals("-2233445.5", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, -22334455, 2, separator);
        assertEquals("-223344.55", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, -22334455, 3, separator);
        assertEquals("-22334.455", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456789012345678L, 0, separator);
        assertEquals("123456789012345678", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456789012345678L, 1, separator);
        assertEquals("12345678901234567.8", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456789012345678L, 2, separator);
        assertEquals("1234567890123456.78", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456789012345678L, 3, separator);
        assertEquals("123456789012345.678", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456789012345678L, 18, separator);
        assertEquals("0.123456789012345678", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456789012345678L, 19, separator);
        assertEquals("0.0123456789012345678", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, 123456789012345678L, 20, separator);
        assertEquals("0.00123456789012345678", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, -2233445566778899L, 0, separator);
        assertEquals("-2233445566778899", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, -2233445566778899L, 1, separator);
        assertEquals("-223344556677889.9", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, -2233445566778899L, 16, separator);
        assertEquals("-0.2233445566778899", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, -2233445566778899L, 17, separator);
        assertEquals("-0.02233445566778899", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MAX_VALUE, 0, separator);
        assertEquals("9223372036854775807", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MAX_VALUE, 1, separator);
        assertEquals("922337203685477580.7", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MAX_VALUE, 2, separator);
        assertEquals("92233720368547758.07", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MAX_VALUE, 3, separator);
        assertEquals("9223372036854775.807", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MIN_VALUE, 0, separator);
        assertEquals("-9223372036854775808", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MIN_VALUE, 1, separator);
        assertEquals("-922337203685477580.8", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MIN_VALUE, 2, separator);
        assertEquals("-92233720368547758.08", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MIN_VALUE, 3, separator);
        assertEquals("-9223372036854775.808", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MIN_VALUE, 10, separator);
        assertEquals("-922337203.6854775808", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MIN_VALUE, 19, separator);
        assertEquals("-0.9223372036854775808", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MIN_VALUE, 20, separator);
        assertEquals("-0.09223372036854775808", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongScaled(sb, Long.MIN_VALUE, 21, separator);
        assertEquals("-0.009223372036854775808", sb.toString());
    }

    @Test
    public void shouldOutput1DigitCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append1Digit(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.append1Digit(sb, 1);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.append1Digit(sb, 9);
        assertEquals("9", sb.toString());
    }

    @Test
    public void shouldOutput1DigitSafely() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append1DigitSafe(sb, 50);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.append1DigitSafe(sb, -511);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.append1DigitSafe(sb, 99999999);
        assertEquals("9", sb.toString());
    }

    @Test
    public void shouldOutput2DigitsCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append2Digits(sb, 0);
        assertEquals("00", sb.toString());
        sb.setLength(0);
        IntOutput.append2Digits(sb, 1);
        assertEquals("01", sb.toString());
        sb.setLength(0);
        IntOutput.append2Digits(sb, 21);
        assertEquals("21", sb.toString());
    }

    @Test
    public void shouldOutput2DigitsSafely() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append2DigitsSafe(sb, 5000);
        assertEquals("00", sb.toString());
        sb.setLength(0);
        IntOutput.append2DigitsSafe(sb, -801);
        assertEquals("01", sb.toString());
        sb.setLength(0);
        IntOutput.append2DigitsSafe(sb, 222221);
        assertEquals("21", sb.toString());
    }

    @Test
    public void shouldOutput3DigitsCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append3Digits(sb, 0);
        assertEquals("000", sb.toString());
        sb.setLength(0);
        IntOutput.append3Digits(sb, 1);
        assertEquals("001", sb.toString());
        sb.setLength(0);
        IntOutput.append3Digits(sb, 21);
        assertEquals("021", sb.toString());
        sb.setLength(0);
        IntOutput.append3Digits(sb, 321);
        assertEquals("321", sb.toString());
    }

    @Test
    public void shouldOutput3DigitsSafely() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append3DigitsSafe(sb, 5000);
        assertEquals("000", sb.toString());
        sb.setLength(0);
        IntOutput.append3DigitsSafe(sb, 1234569001);
        assertEquals("001", sb.toString());
        sb.setLength(0);
        IntOutput.append3DigitsSafe(sb, -4021);
        assertEquals("021", sb.toString());
        sb.setLength(0);
        IntOutput.append3DigitsSafe(sb, 7654321);
        assertEquals("321", sb.toString());
    }

    @Test
    public void shouldOutputIntegerWithGrouping() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendIntGrouped(sb, 0, ',');
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 1, ',');
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 123, ',');
        assertEquals("123", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 1234, ',');
        assertEquals("1,234", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 12345, ',');
        assertEquals("12,345", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 123456, ',');
        assertEquals("123,456", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 1234567, ',');
        assertEquals("1,234,567", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 12345678, ',');
        assertEquals("12,345,678", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 123456789, ',');
        assertEquals("123,456,789", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, 1234567890, ',');
        assertEquals("1,234,567,890", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, Integer.MAX_VALUE, ',');
        assertEquals("2,147,483,647", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntGrouped(sb, Integer.MIN_VALUE, ',');
        assertEquals("-2,147,483,648", sb.toString());
    }

    @Test
    public void shouldOutputLongWithGrouping() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendLongGrouped(sb, 0, ',');
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 1, ',');
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 123, ',');
        assertEquals("123", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 1234, ',');
        assertEquals("1,234", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 12345, ',');
        assertEquals("12,345", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 123456, ',');
        assertEquals("123,456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 1234567, ',');
        assertEquals("1,234,567", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 12345678, ',');
        assertEquals("12,345,678", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 123456789, ',');
        assertEquals("123,456,789", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 1234567890, ',');
        assertEquals("1,234,567,890", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 12345678901L, ',');
        assertEquals("12,345,678,901", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 123456789012L, ',');
        assertEquals("123,456,789,012", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 1234567890123L, ',');
        assertEquals("1,234,567,890,123", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 12345678901234L, ',');
        assertEquals("12,345,678,901,234", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 123456789012345L, ',');
        assertEquals("123,456,789,012,345", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 1234567890123456L, ',');
        assertEquals("1,234,567,890,123,456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 12345678901234567L, ',');
        assertEquals("12,345,678,901,234,567", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 123456789012345678L, ',');
        assertEquals("123,456,789,012,345,678", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, 1234567890123456789L, ',');
        assertEquals("1,234,567,890,123,456,789", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, Long.MAX_VALUE, ',');
        assertEquals("9,223,372,036,854,775,807", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongGrouped(sb, Long.MIN_VALUE, ',');
        assertEquals("-9,223,372,036,854,775,808", sb.toString());
    }

    @Test
    public void shouldFormatMoney() {
        assertEquals("$1.00", formatMoney(1, 0));
        assertEquals("$1,234.56", formatMoney(1234, 56));
    }

    private String formatMoney(long dollars, int cents) {
        StringBuilder sb = new StringBuilder(32);
        try {
            sb.append('$');
            IntOutput.appendPositiveLongGrouped(sb, dollars, ',');
            sb.append('.');
            IntOutput.append2Digits(sb, cents);
        }
        catch (IOException ignore) {
            // can't happen - StringBuilder doesn't throw IOException
        }
        return sb.toString();
    }

    @Test
    public void shouldConvertIntToHexCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendIntHex(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHex(sb, 1);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHex(sb, 0x23);
        assertEquals("23", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHex(sb, 0x456);
        assertEquals("456", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHex(sb, 0xA7B9);
        assertEquals("A7B9", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHex(sb, 0x8A1B1);
        assertEquals("8A1B1", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHex(sb, 0xFEEABC);
        assertEquals("FEEABC", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHex(sb, 0xDEADFEED);
        assertEquals("DEADFEED", sb.toString());
    }

    @Test
    public void shouldConvertIntToHexCorrectlyUsingLowerCase() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendIntHexLC(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHexLC(sb, 1);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHexLC(sb, 0x23);
        assertEquals("23", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHexLC(sb, 0x456);
        assertEquals("456", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHexLC(sb, 0xA7B9);
        assertEquals("a7b9", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHexLC(sb, 0x8A1B1);
        assertEquals("8a1b1", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHexLC(sb, 0xFEEABC);
        assertEquals("feeabc", sb.toString());
        sb.setLength(0);
        IntOutput.appendIntHexLC(sb, 0xDEADFEED);
        assertEquals("deadfeed", sb.toString());
    }

    @Test
    public void shouldConvertLongToHexCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendLongHex(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 1);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 0x23);
        assertEquals("23", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 0x456);
        assertEquals("456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 0xA7B9);
        assertEquals("A7B9", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 0x8A1B1);
        assertEquals("8A1B1", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 0xFEEABC);
        assertEquals("FEEABC", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 0xDEADFEEDL);
        assertEquals("DEADFEED", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 0x123DEADFEEDL);
        assertEquals("123DEADFEED", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHex(sb, 0x8000000000000000L);
        assertEquals("8000000000000000", sb.toString());
    }

    @Test
    public void shouldConvertLongToHexCorrectlyUsingLowerCase() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.appendLongHexLC(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 1);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 0x23);
        assertEquals("23", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 0x456);
        assertEquals("456", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 0xA7B9);
        assertEquals("a7b9", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 0x8A1B1);
        assertEquals("8a1b1", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 0xFEEABC);
        assertEquals("feeabc", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 0xDEADFEEDL);
        assertEquals("deadfeed", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 0x123DEADFEEDL);
        assertEquals("123deadfeed", sb.toString());
        sb.setLength(0);
        IntOutput.appendLongHexLC(sb, 0x8000000000000000L);
        assertEquals("8000000000000000", sb.toString());
    }

    @Test
    public void shouldOutput8DigitsHexCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append8Hex(sb, 0);
        assertEquals("00000000", sb.toString());
        sb.setLength(0);
        IntOutput.append8Hex(sb, 1);
        assertEquals("00000001", sb.toString());
        sb.setLength(0);
        IntOutput.append8Hex(sb, 0xABCD);
        assertEquals("0000ABCD", sb.toString());
        sb.setLength(0);
        IntOutput.append8Hex(sb, 0x9ABCD);
        assertEquals("0009ABCD", sb.toString());
        sb.setLength(0);
        IntOutput.append8Hex(sb, 0x89ABCD);
        assertEquals("0089ABCD", sb.toString());
        sb.setLength(0);
        IntOutput.append8Hex(sb, 0xE89ABCD);
        assertEquals("0E89ABCD", sb.toString());
        sb.setLength(0);
        IntOutput.append8Hex(sb, 0x7E89ABCD);
        assertEquals("7E89ABCD", sb.toString());
    }

    @Test
    public void shouldOutput8DigitsHexCorrectlyUsingLowerCase() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append8HexLC(sb, 0);
        assertEquals("00000000", sb.toString());
        sb.setLength(0);
        IntOutput.append8HexLC(sb, 1);
        assertEquals("00000001", sb.toString());
        sb.setLength(0);
        IntOutput.append8HexLC(sb, 0xABCD);
        assertEquals("0000abcd", sb.toString());
        sb.setLength(0);
        IntOutput.append8HexLC(sb, 0x9ABCD);
        assertEquals("0009abcd", sb.toString());
        sb.setLength(0);
        IntOutput.append8HexLC(sb, 0x89ABCD);
        assertEquals("0089abcd", sb.toString());
        sb.setLength(0);
        IntOutput.append8HexLC(sb, 0xE89ABCD);
        assertEquals("0e89abcd", sb.toString());
        sb.setLength(0);
        IntOutput.append8HexLC(sb, 0xFE89ABCD);
        assertEquals("fe89abcd", sb.toString());
    }

    @Test
    public void shouldOutput4DigitsHexCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append4Hex(sb, 0);
        assertEquals("0000", sb.toString());
        sb.setLength(0);
        IntOutput.append4Hex(sb, 1);
        assertEquals("0001", sb.toString());
        sb.setLength(0);
        IntOutput.append4Hex(sb, 0xABCD);
        assertEquals("ABCD", sb.toString());
    }

    @Test
    public void shouldOutput4DigitsHexCorrectlyUsingLowerCase() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append4HexLC(sb, 0);
        assertEquals("0000", sb.toString());
        sb.setLength(0);
        IntOutput.append4HexLC(sb, 1);
        assertEquals("0001", sb.toString());
        sb.setLength(0);
        IntOutput.append4HexLC(sb, 0xABCD);
        assertEquals("abcd", sb.toString());
    }

    @Test
    public void shouldOutput2DigitsHexCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append2Hex(sb, 0);
        assertEquals("00", sb.toString());
        sb.setLength(0);
        IntOutput.append2Hex(sb, 1);
        assertEquals("01", sb.toString());
        sb.setLength(0);
        IntOutput.append2Hex(sb, 0xAB);
        assertEquals("AB", sb.toString());
    }

    @Test
    public void shouldOutput2DigitsHexCorrectlyUsingLowerCase() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append2HexLC(sb, 0);
        assertEquals("00", sb.toString());
        sb.setLength(0);
        IntOutput.append2HexLC(sb, 1);
        assertEquals("01", sb.toString());
        sb.setLength(0);
        IntOutput.append2HexLC(sb, 0xAB);
        assertEquals("ab", sb.toString());
    }

    @Test
    public void shouldOutput1DigitHexCorrectly() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append1Hex(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.append1Hex(sb, 1);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.append1Hex(sb, 0xA);
        assertEquals("A", sb.toString());
    }

    @Test
    public void shouldOutput1DigitHexCorrectlyUsingLowerCase() throws IOException {
        StringBuilder sb = new StringBuilder();
        IntOutput.append1HexLC(sb, 0);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.append1HexLC(sb, 1);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.append1HexLC(sb, 0xA);
        assertEquals("a", sb.toString());
    }

}
