# int-output

[![Build Status](https://travis-ci.com/pwall567/int-output.svg?branch=main)](https://app.travis-ci.com/github/pwall567/int-output)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/net.pwall.util/int-output?label=Maven%20Central)](https://search.maven.org/search?q=g:%22net.pwall.util%22%20AND%20a:%22int-output%22)

Integer output functions

## Background

Many projects have a requirement to output integer values in string form, either as variable-length strings with leading
zeroes omitted, or as a fixed number of digits.
This library consists of a number of static functions to output integer values to an `Appendable`, without incurring the
cost (in execution-time terms) of a memory allocation.

This may seem like a trivial operation, but because the length of the string representation is not known in advance, a
simplistic implementation might allocate a memory buffer to hold the converted value, and then copy the result to the
output.
But memory allocation is a relatively costly operation compared to the basic numeric conversion, and an optimisation
that avoids memory allocation can produce worthwhile CPU time savings.

The problem with optimisation, of course, is that the additional complexity of the code can be a source of errors.
This library offers a set of well-tested, highly optimised conversion functions that give the efficiency gains with none
of the risks.

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
        IntOutput.appendPositiveLong(sb, intValue);
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
        IntOutput.append3Digits(sb, hours);
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
        IntOutput.appendLongGrouped(sb, intValue, ',');
```

If the `long` value is guaranteed to be positive:
```java
        StringBuilder sb = new StringBuilder(12);
        long longValue = 1234567812345678;
        IntOutput.appendPositiveLongGrouped(sb, intValue, ',');
```

## Exceptions

Unfortunately, the `append()` functions of the `Appendable` interface are declared as throwing an `IOException`, so all
the functions in this library are similarly specified.
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

The latest version of the library is 1.0, and it may be obtained from the Maven Central repository.

### Maven
```xml
    <dependency>
      <groupId>net.pwall.util</groupId>
      <artifactId>int-output</artifactId>
      <version>1.0</version>
    </dependency>
```
### Gradle
```groovy
    implementation 'net.pwall.util:int-output:1.0'
```
### Gradle (kts)
```kotlin
    implementation("net.pwall.util:int-output:1.0")
```

Peter Wall

2021-08-25
