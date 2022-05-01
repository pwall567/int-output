# int-output

[![Build Status](https://travis-ci.com/pwall567/int-output.svg?branch=main)](https://app.travis-ci.com/github/pwall567/int-output)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/net.pwall.util/int-output?label=Maven%20Central)](https://search.maven.org/search?q=g:%22net.pwall.util%22%20AND%20a:%22int-output%22)

Integer output functions

## Background

Many projects have a requirement to output integer values in string form, either as variable-length strings with leading
zeroes omitted, or as a fixed number of digits.
This library consists of a number of static functions to output integer values to an `Appendable`, or using an
`IntConsumer` function, without incurring the cost (in execution-time terms) of a memory allocation.

This may seem like a trivial operation, but because the length of the string representation is not known in advance, a
simplistic implementation might allocate a memory buffer to hold the converted value, and then copy the result to the
output.
But memory allocation is a relatively costly operation compared to the basic numeric conversion, and an optimisation
that avoids memory allocation can produce worthwhile CPU time savings.

The problem with optimisation, of course, is that the additional complexity of the code can be a source of errors.
This library offers a set of well-tested, highly optimised conversion functions that give the efficiency gains with none
of the risks.

## `Appendable` or `IntConsumer`

The most common use of these functions is to build a string using a `StringBuilder` (an implementation of `Appendable`),
but in some cases the requirement is to output the value in some other manner, for example using the
[`pipelines`](https://github.com/pwall567/pipelines) library.
For these cases, there are versions of all the functions which take an `IntConsumer` parameter; this lambda will be
called with each character (as an `int`) in turn.

So why maintain both?
After all, appending to an `Appendable` can always be achieved using the `IntConsumer` functions.

The fact is that the `Appendable` functions were implemented first, and they also represent the most common usage
pattern for the functions &ndash; building a string using `StringBuilder` &ndash; so it makes sense to maintain the
availability of both.
Also, there is a slight performance advantage in using the `Appendable` directly, rather than through a lambda.

## Decimal or Hexadecimal

There are functions to output left-trimmed strings of digits in decimal and hexadecimal, and in addition, there are
several functions to output fixed-length strings left-padded with zeros.

### Decimal

Function                    | Parameter | Output
----------------------------|-----------|----------------------------------------------------------------
`appendInt`                 | `int`     | left-trimmed
`appendPositiveInt`         | `int`     | left-trimmed (value must be positive)
`appendUnsignedInt`         | `int`     | left-trimmed (value is treated as unsigned)
`appendLong`                | `long`    | left-trimmed
`appendPositiveLong`        | `long`    | left-trimmed (value must be positive)
`appendUnsignedLong`        | `long`    | left-trimmed (value is treated as unsigned)
`append2Digits`             | `int`     | 2 digits left filled with zeros
`append3Digits`             | `int`     | 3 digits left filled with zeros
`appendIntGrouped`          | `int`     | left-trimmed, output in 3-digit groups
`appendPositiveIntGrouped`  | `int`     | left-trimmed, output in 3-digit groups (value must be positive)
`appendLongGrouped`         | `long`    | left-trimmed, output in 3-digit groups
`appendPositiveLongGrouped` | `long`    | left-trimmed, output in 3-digit groups (value must be positive)
(the "grouped" forms output digits in blocks of three, separated by a nominated separator character)

For each `appendXxxx` function there is an equivalent `outputXxxx` function, which instead of taking an `Appendable`
parameter, takes an `IntConsumer` which will be called with each output character.

### Hexadecimal

Function          | Parameter | Output
------------------|-----------|-----------------------------------------------------------------
`appendIntHex`    | `int`     | left-trimmed, hexadecimal
`appendIntHexLC`  | `int`     | left-trimmed, hexadecimal (using lower-case alphabetics)
`appendLongHex`   | `long`    | left-trimmed, hexadecimal
`appendLongHexLC` | `long`    | left-trimmed, hexadecimal (using lower-case alphabetics)
`append8Hex`      | `int`     | 8 digits left-padded, hexadecimal
`append8HexLC`    | `int`     | 8 digits left-padded, hexadecimal (using lower-case alphabetics)
`append4Hex`      | `int`     | 4 digits left-padded, hexadecimal
`append4HexLC`    | `int`     | 4 digits left-padded, hexadecimal (using lower-case alphabetics)
`append2Hex`      | `int`     | 2 digits left-padded, hexadecimal
`append2HexLC`    | `int`     | 2 digits left-padded, hexadecimal (using lower-case alphabetics)
`append1Hex`      | `int`     | 1 digit, hexadecimal
`append1HexLC`    | `int`     | 1 digit, hexadecimal (using lower-case alphabetics)
(the functions all have two variants, one which uses upper-case characters for the hexadecimal digits and one which uses
lower-case)

For each `appendXxxx` function there is an equivalent `outputXxxx` function, which instead of taking an `Appendable`
parameter, takes an `IntConsumer` which will be called with each output character.

## Usage

To output an `int` value to an `Appendable`:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 12345;
        IntOutput.appendInt(sb, intValue);
```

If the number is guaranteed to be positive:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 12345;
        IntOutput.appendPositiveInt(sb, intValue);
```

To output an `int` value to an `Appendable`, treating the `int` as unsigned:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 0x89ABCDEF;
        IntOutput.appendUnsignedInt(sb, intValue);
```

To output a `long` value to an `Appendable`:
```java
        StringBuilder sb = new StringBuilder(20);
        long longValue = 1234567812345678;
        IntOutput.appendLong(sb, longValue);
```

If the `long` value is guaranteed to be positive:
```java
        StringBuilder sb = new StringBuilder(20);
        long longValue = 1234567812345678;
        IntOutput.appendPositiveLong(sb, longValue);
```

To output a `long` value to an `Appendable`, treating the `long` as unsigned:
```java
        StringBuilder sb = new StringBuilder(20);
        long longValue = 1234567890123456789L;
        IntOutput.appendUnsignedLong(sb, longValue * 10);
```

To output an `int` as 2 digits, including leading zero if required (for example, when outputting a time):
```java
        StringBuilder sb = new StringBuilder(12);
        int hours = 23;
        IntOutput.append2Digits(sb, hours);
```

To output an `int` as 3 digits, including leading zeroes if required (for example, when outputting the number of
milliseconds in a time):
```java
        StringBuilder sb = new StringBuilder(12);
        int millis = 567;
        IntOutput.append3Digits(sb, millis);
```

To output an `int` value to an `Appendable`, with the digits grouped in 3s:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 12345;
        IntOutput.appendIntGrouped(sb, intValue, ',');
```

If the number is guaranteed to be positive:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 12345;
        IntOutput.appendPositiveIntGrouped(sb, intValue, ',');
```

To output a `long` value to an `Appendable`, with the digits grouped in 3s:
```java
        StringBuilder sb = new StringBuilder(12);
        long longValue = 1234567812345678;
        IntOutput.appendLongGrouped(sb, longValue, ',');
```

If the `long` value is guaranteed to be positive:
```java
        StringBuilder sb = new StringBuilder(12);
        long longValue = 1234567812345678;
        IntOutput.appendPositiveLongGrouped(sb, longValue, ',');
```

To output a number as 2 hexadecimal digits:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 160;
        IntOutput.append2Hex(sb, intValue);
```

To output a number as 2 hexadecimal digits using lower case for the alphabetic characters:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 160;
        IntOutput.append2HexLC(sb, intValue);
```

To output a number as 4 hexadecimal digits:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 65535;
        IntOutput.append4Hex(sb, intValue);
```

To output a number as 4 hexadecimal digits using lower case for the alphabetic characters:
```java
        StringBuilder sb = new StringBuilder(12);
        int intValue = 65535;
        IntOutput.append4HexLC(sb, intValue);
```

To output an `int` value using an `IntConsumer`:
```java
        int intValue = 12345;
        IntOutput.outputInt(intValue, (ch) -> channel.send(ch));
```

## Exceptions

Unfortunately, the `append()` functions of the `Appendable` interface are declared as throwing an `IOException`, so all
the functions in this library that use `Appendable` are similarly specified.
The exception can safely be ignored if the `Appendable` is a `StringBuilder`, but if the `Appendable` is a `Writer`, or
some other class that performs actual I/O, the caller of the functions must take that into account.

## Extended Example

The following code formats a money value, with dollar sign, commas, decimal point and two digits of cents.
```java
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
```

## Dependency Specification

The latest version of the library is 1.3, and it may be obtained from the Maven Central repository.

### Maven
```xml
    <dependency>
      <groupId>net.pwall.util</groupId>
      <artifactId>int-output</artifactId>
      <version>1.3</version>
    </dependency>
```
### Gradle
```groovy
    implementation 'net.pwall.util:int-output:1.3'
```
### Gradle (kts)
```kotlin
    implementation("net.pwall.util:int-output:1.3")
```

Peter Wall

2022-05-01
