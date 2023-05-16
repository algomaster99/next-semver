# Next Semver

Simple maven plugin to bump the next semver version. Uses `ANTLR4` to parse
the current version.

> I wrote it to learn ANTLR4 :stuck_out_tongue:

## Build

```bash
mvn clean package
```

## Usage

```bash
java -jar target/semver-1.0.2-SNAPSHOT-jar-with-dependencies.jar -r major 1.0.0
>>> 2.0.0
```

```
Usage: next-semver [-hV] -r=<releaseType> <currentVersion>
Increments the version number of a project.
      <currentVersion>
  -h, --help             Show this help message and exit.
  -r, --release-type=<releaseType>
                         The type of release to perform. Options are: major,
                           minor, patch, preRelease.
  -V, --version          Print version information and exit.

```

## Release Types

- `major`: bump the major version eg. 1.0.0 -> 2.0.0
- `minor`: bump the minor version eg. 1.0.0 -> 1.1.0
- `patch`: bump the patch version eg. 1.0.0 -> 1.0.1
- `premajor`: bump the major version and add a prerelease suffix eg. 1.0.0-alpha.2 -> 1.0.0-alpha.3
