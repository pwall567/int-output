/*
 * @(#) IntOutputIntConsumerTest.java
 *
 * int-output  Integer output functions
 * Copyright (c) 2022 Peter Wall
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

import java.util.function.IntConsumer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import net.pwall.util.IntOutput;

public class IntOutputIntConsumerTest {

    @Test
    public void shouldConvertIntCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputInt(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputInt(123456, ic);
        assertEquals("123456", sb.toString());
        sb.setLength(0);
        IntOutput.outputInt(-22334455, ic);
        assertEquals("-22334455", sb.toString());
        sb.setLength(0);
        IntOutput.outputInt(Integer.MAX_VALUE, ic);
        assertEquals("2147483647", sb.toString());
        sb.setLength(0);
        IntOutput.outputInt(Integer.MIN_VALUE, ic);
        assertEquals("-2147483648", sb.toString());
    }

    @Test
    public void shouldConvertUnsignedIntCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputUnsignedInt(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputUnsignedInt(123456, ic);
        assertEquals("123456", sb.toString());
        sb.setLength(0);
        IntOutput.outputUnsignedInt((int)2147483648L, ic);
        assertEquals("2147483648", sb.toString());
        sb.setLength(0);
        IntOutput.outputUnsignedInt((int)3456789012L, ic);
        assertEquals("3456789012", sb.toString());
        sb.setLength(0);
        IntOutput.outputUnsignedInt(0x89ABCDEF, ic);
        assertEquals("2309737967", sb.toString());
    }

    @Test
    public void shouldConvertLongCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputLong(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputLong(123456789012345678L, ic);
        assertEquals("123456789012345678", sb.toString());
        sb.setLength(0);
        IntOutput.outputLong(-2233445566778899L, ic);
        assertEquals("-2233445566778899", sb.toString());
        sb.setLength(0);
        IntOutput.outputLong(Integer.MAX_VALUE, ic);
        assertEquals("2147483647", sb.toString());
        sb.setLength(0);
        IntOutput.outputLong(Integer.MIN_VALUE, ic);
        assertEquals("-2147483648", sb.toString());
        sb.setLength(0);
        IntOutput.outputLong(Long.MAX_VALUE, ic);
        assertEquals("9223372036854775807", sb.toString());
        sb.setLength(0);
        IntOutput.outputLong(Long.MIN_VALUE, ic);
        assertEquals("-9223372036854775808", sb.toString());
    }

    @Test
    public void shouldConvertUnsignedLongCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputUnsignedLong(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputUnsignedLong(1234567890123456789L, ic);
        assertEquals("1234567890123456789", sb.toString());
        sb.setLength(0);
        long n = Long.MAX_VALUE;
        IntOutput.outputUnsignedLong(n + 1, ic);
        assertEquals("9223372036854775808", sb.toString());
        sb.setLength(0);
        n = 1234567890123456789L;
        IntOutput.outputUnsignedLong(n * 10, ic);
        assertEquals("12345678901234567890", sb.toString());
    }

    @Test
    public void shouldOutput2DigitsCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output2Digits(0, ic);
        assertEquals("00", sb.toString());
        sb.setLength(0);
        IntOutput.output2Digits(1, ic);
        assertEquals("01", sb.toString());
        sb.setLength(0);
        IntOutput.output2Digits(21, ic);
        assertEquals("21", sb.toString());
    }

    @Test
    public void shouldOutput3DigitsCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output3Digits(0, ic);
        assertEquals("000", sb.toString());
        sb.setLength(0);
        IntOutput.output3Digits(1, ic);
        assertEquals("001", sb.toString());
        sb.setLength(0);
        IntOutput.output3Digits(21, ic);
        assertEquals("021", sb.toString());
        sb.setLength(0);
        IntOutput.output3Digits(321, ic);
        assertEquals("321", sb.toString());
    }

    @Test
    public void shouldOutputIntegerWithGroupingUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputIntGrouped(0, ',', ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(1, ',', ic);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(123, ',', ic);
        assertEquals("123", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(1234, ',', ic);
        assertEquals("1,234", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(12345, ',', ic);
        assertEquals("12,345", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(123456, ',', ic);
        assertEquals("123,456", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(1234567, ',', ic);
        assertEquals("1,234,567", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(12345678, ',', ic);
        assertEquals("12,345,678", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(123456789, ',', ic);
        assertEquals("123,456,789", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(1234567890, ',', ic);
        assertEquals("1,234,567,890", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(Integer.MAX_VALUE, ',', ic);
        assertEquals("2,147,483,647", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntGrouped(Integer.MIN_VALUE, ',', ic);
        assertEquals("-2,147,483,648", sb.toString());
    }

    @Test
    public void shouldOutputLongWithGroupingUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputLongGrouped(0, ',', ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(1, ',', ic);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(123, ',', ic);
        assertEquals("123", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(1234, ',', ic);
        assertEquals("1,234", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(12345, ',', ic);
        assertEquals("12,345", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(123456, ',', ic);
        assertEquals("123,456", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(1234567, ',', ic);
        assertEquals("1,234,567", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(12345678, ',', ic);
        assertEquals("12,345,678", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(123456789, ',', ic);
        assertEquals("123,456,789", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(1234567890, ',', ic);
        assertEquals("1,234,567,890", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(12345678901L, ',', ic);
        assertEquals("12,345,678,901", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(123456789012L, ',', ic);
        assertEquals("123,456,789,012", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(1234567890123L, ',', ic);
        assertEquals("1,234,567,890,123", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(12345678901234L, ',', ic);
        assertEquals("12,345,678,901,234", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(123456789012345L, ',', ic);
        assertEquals("123,456,789,012,345", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(1234567890123456L, ',', ic);
        assertEquals("1,234,567,890,123,456", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(12345678901234567L, ',', ic);
        assertEquals("12,345,678,901,234,567", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(123456789012345678L, ',', ic);
        assertEquals("123,456,789,012,345,678", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(1234567890123456789L, ',', ic);
        assertEquals("1,234,567,890,123,456,789", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(Long.MAX_VALUE, ',', ic);
        assertEquals("9,223,372,036,854,775,807", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongGrouped(Long.MIN_VALUE, ',', ic);
        assertEquals("-9,223,372,036,854,775,808", sb.toString());
    }

    @Test
    public void shouldConvertIntToHexCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputIntHex(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHex(1, ic);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHex(0x23, ic);
        assertEquals("23", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHex(0x456, ic);
        assertEquals("456", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHex(0xA7B9, ic);
        assertEquals("A7B9", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHex(0x8A1B1, ic);
        assertEquals("8A1B1", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHex(0xFEEABC, ic);
        assertEquals("FEEABC", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHex(0xDEADFEED, ic);
        assertEquals("DEADFEED", sb.toString());
    }

    @Test
    public void shouldConvertIntToHexCorrectlyInLowerCaseUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputIntHexLC(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHexLC(1, ic);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHexLC(0x23, ic);
        assertEquals("23", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHexLC(0x456, ic);
        assertEquals("456", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHexLC(0xA7B9, ic);
        assertEquals("a7b9", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHexLC(0x8A1B1, ic);
        assertEquals("8a1b1", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHexLC(0xFEEABC, ic);
        assertEquals("feeabc", sb.toString());
        sb.setLength(0);
        IntOutput.outputIntHexLC(0xDEADFEED, ic);
        assertEquals("deadfeed", sb.toString());
    }

    @Test
    public void shouldConvertLongToHexCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputLongHex(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(1, ic);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(0x23, ic);
        assertEquals("23", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(0x456, ic);
        assertEquals("456", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(0xA7B9, ic);
        assertEquals("A7B9", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(0x8A1B1, ic);
        assertEquals("8A1B1", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(0xFEEABC, ic);
        assertEquals("FEEABC", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(0xDEADFEEDL, ic);
        assertEquals("DEADFEED", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(0x123DEADFEEDL, ic);
        assertEquals("123DEADFEED", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHex(0x8000000000000000L, ic);
        assertEquals("8000000000000000", sb.toString());
    }

    @Test
    public void shouldConvertLongToHexCorrectlyInLowerCaseUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.outputLongHexLC(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(1, ic);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(0x23, ic);
        assertEquals("23", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(0x456, ic);
        assertEquals("456", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(0xA7B9, ic);
        assertEquals("a7b9", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(0x8A1B1, ic);
        assertEquals("8a1b1", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(0xFEEABC, ic);
        assertEquals("feeabc", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(0xDEADFEEDL, ic);
        assertEquals("deadfeed", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(0x123DEADFEEDL, ic);
        assertEquals("123deadfeed", sb.toString());
        sb.setLength(0);
        IntOutput.outputLongHexLC(0x8000000000000000L, ic);
        assertEquals("8000000000000000", sb.toString());
    }

    @Test
    public void shouldOutput8DigitsHexCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output8Hex(0, ic);
        assertEquals("00000000", sb.toString());
        sb.setLength(0);
        IntOutput.output8Hex(1, ic);
        assertEquals("00000001", sb.toString());
        sb.setLength(0);
        IntOutput.output8Hex(0xABCD, ic);
        assertEquals("0000ABCD", sb.toString());
        sb.setLength(0);
        IntOutput.output8Hex(0x9ABCD, ic);
        assertEquals("0009ABCD", sb.toString());
        sb.setLength(0);
        IntOutput.output8Hex(0x89ABCD, ic);
        assertEquals("0089ABCD", sb.toString());
        sb.setLength(0);
        IntOutput.output8Hex(0xE89ABCD, ic);
        assertEquals("0E89ABCD", sb.toString());
        sb.setLength(0);
        IntOutput.output8Hex(0x7E89ABCD, ic);
        assertEquals("7E89ABCD", sb.toString());
    }

    @Test
    public void shouldOutput8DigitsHexCorrectlyInLowerCaseUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output8HexLC(0, ic);
        assertEquals("00000000", sb.toString());
        sb.setLength(0);
        IntOutput.output8HexLC(1, ic);
        assertEquals("00000001", sb.toString());
        sb.setLength(0);
        IntOutput.output8HexLC(0xABCD, ic);
        assertEquals("0000abcd", sb.toString());
        sb.setLength(0);
        IntOutput.output8HexLC(0x9ABCD, ic);
        assertEquals("0009abcd", sb.toString());
        sb.setLength(0);
        IntOutput.output8HexLC(0x89ABCD, ic);
        assertEquals("0089abcd", sb.toString());
        sb.setLength(0);
        IntOutput.output8HexLC(0xE89ABCD, ic);
        assertEquals("0e89abcd", sb.toString());
        sb.setLength(0);
        IntOutput.output8HexLC(0xFE89ABCD, ic);
        assertEquals("fe89abcd", sb.toString());
    }

    @Test
    public void shouldOutput4DigitsHexCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output4Hex(0, ic);
        assertEquals("0000", sb.toString());
        sb.setLength(0);
        IntOutput.output4Hex(1, ic);
        assertEquals("0001", sb.toString());
        sb.setLength(0);
        IntOutput.output4Hex(0xABCD, ic);
        assertEquals("ABCD", sb.toString());
    }

    @Test
    public void shouldOutput4DigitsHexCorrectlyInLowerCaseUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output4HexLC(0, ic);
        assertEquals("0000", sb.toString());
        sb.setLength(0);
        IntOutput.output4HexLC(1, ic);
        assertEquals("0001", sb.toString());
        sb.setLength(0);
        IntOutput.output4HexLC(0xABCD, ic);
        assertEquals("abcd", sb.toString());
    }

    @Test
    public void shouldOutput2DigitsHexCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output2Hex(0, ic);
        assertEquals("00", sb.toString());
        sb.setLength(0);
        IntOutput.output2Hex(1, ic);
        assertEquals("01", sb.toString());
        sb.setLength(0);
        IntOutput.output2Hex(0xAB, ic);
        assertEquals("AB", sb.toString());
    }

    @Test
    public void shouldOutput2DigitsHexCorrectlyInLowerCaseUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output2HexLC(0, ic);
        assertEquals("00", sb.toString());
        sb.setLength(0);
        IntOutput.output2HexLC(1, ic);
        assertEquals("01", sb.toString());
        sb.setLength(0);
        IntOutput.output2HexLC(0xAB, ic);
        assertEquals("ab", sb.toString());
    }

    @Test
    public void shouldOutput1DigitHexCorrectlyUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output1Hex(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.output1Hex(1, ic);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.output1Hex(0xA, ic);
        assertEquals("A", sb.toString());
    }

    @Test
    public void shouldOutput1DigitHexCorrectlyInLowerCaseUsingLambda() {
        StringBuilder sb = new StringBuilder();
        IntConsumer ic = ch -> sb.append((char)ch);
        IntOutput.output1HexLC(0, ic);
        assertEquals("0", sb.toString());
        sb.setLength(0);
        IntOutput.output1HexLC(1, ic);
        assertEquals("1", sb.toString());
        sb.setLength(0);
        IntOutput.output1HexLC(0xA, ic);
        assertEquals("a", sb.toString());
    }

}
