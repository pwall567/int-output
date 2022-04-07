/*
 * @(#) IntOutputTest.java
 *
 * int-output  Integer output functions
 * Copyright (c) 2021 Peter Wall
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

public class IntOutputTest {

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
    public void  shouldOutput2DigitsCorrectly() throws IOException {
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
    public void  shouldOutput3DigitsCorrectly() throws IOException {
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
    public void  shouldOutput2DigitsHexCorrectly() throws IOException {
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
    public void  shouldOutput2DigitsHexCorrectlyUsingLowerCase() throws IOException {
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
    public void  shouldOutput4DigitsHexCorrectly() throws IOException {
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
    public void  shouldOutput4DigitsHexCorrectlyUsingLowerCase() throws IOException {
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
    public void  shouldOutputIntegerWithGrouping() throws IOException {
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
    public void  shouldOutputLongWithGrouping() throws IOException {
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

}
