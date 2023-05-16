package io.github.algomaster99.semver;

public enum ReleaseType {
    MAJOR("major"),
    MINOR("minor"),
    PATCH("patch"),
    PRERELEASE("preRelease");

    private String type;

    ReleaseType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
