# Change Log

The format is based on [Keep a Changelog](http://keepachangelog.com/).

## [2.0] - 2023-11-09
### Changed
- `IntOutput`: minor tweaks / optimisations
- `IntOutput`: added `appendIntScaled`, `appendLongScaled`, `outputIntScaled` and `outputLongScaled`
- `IntOutput`: replaced `MIN_INTEGER_STRING` and `MIN_LONG_STRING` with `MIN_INTEGER_DIGITS` and `MIN_LONG_DIGITS`,
  omitting sign (possible breaking change)

## [1.3] - 2022-05-01
### Added
- `IntOutput`: functions taking an `IntConsumer`
- `IntOutput`: additional hex functions

## [1.2] - 2022-04-07
### Added
- `IntOutput`: added lower case alphabetic hex functions

## [1.1] - 2022-01-26
### Added
- `IntOutput`: added `appendUnsignedInt()` and `appendUnsignedLong()`

## [1.0] - 2021-08-25
### Added
- all files: initial versions
