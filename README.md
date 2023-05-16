# Next Semver

Simple maven plugin to bump the next semver version. Uses `ANTLR4` to parse
the current version.

> I wrote it to learn ANTLR4 :stuck_out_tongue:

## Build

```bash
mvn clean install
```

## Usage

```bash
mvn io.github.algomaster99:semver:next-semver -DcurrentVersion=1.0.0 -DreleaseType=major
>>> 2.0.0
```

```
Name: semver
Description: (no description available)
Group Id: io.github.algomaster99
Artifact Id: semver
Version: 0.0.1-SNAPSHOT
Goal Prefix: semver

This plugin has 1 goal:

semver:next-semver
  Description: Goal which increments the version number of a project.
  Implementation: io.github.algomaster99.semver.NextSemverCommand
  Language: java

  Available parameters:

    currentVersion
      Required: true
      User property: currentVersion
      The current version of the project.

    releaseType
      Required: true
      User property: releaseType
      The type of release to perform. Options are: major, minor, patch,
      prerelease
```

## Release Types

- `major`: bump the major version eg. 1.0.0 -> 2.0.0
- `minor`: bump the minor version eg. 1.0.0 -> 1.1.0
- `patch`: bump the patch version eg. 1.0.0 -> 1.0.1
- `premajor`: bump the major version and add a prerelease suffix eg. 1.0.0-alpha.2 -> 1.0.0-alpha.3
