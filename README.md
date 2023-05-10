# Next Semver

Simple maven plugin to bump the next semver version. Uses `ANTLR4` to parse
the current version.

> **Note:** This is a work in progress. It is not yet ready for use.

## Build

```bash
mvn clean install
```

## Usage

```bash
mvn io.github.algomaster99:semver:next-semver -DcurrentVersion=1.0.0
```
