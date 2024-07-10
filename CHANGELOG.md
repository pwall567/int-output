# Change Log

The format is based on [Keep a Changelog](http://keepachangelog.com/).

## [Unreleased]
### Added
- `build.yml`, `deploy.yml`: converted project to GitHub Actions
### Removed
- `.travis.yml`

## [2.1] - 2023-12-02
### Changed
- `IntOutput`: added `append1Digit` and `output1Digit`
- `IntOutput`: added "Safe" versions of `append1Digit`, `output1Digit`, `append2Digits`, `output2Digits`,
  `append3Digits` and `output3Digits`

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
