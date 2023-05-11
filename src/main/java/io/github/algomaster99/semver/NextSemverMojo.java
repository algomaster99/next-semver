package io.github.algomaster99.semver;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.component.configurator.converters.basic.AbstractBasicConverter;

/**
 * Goal which increments the version number of a project.
 */
@Mojo(name = "next-semver")
public class NextSemverMojo extends AbstractMojo {

    /**
     * The current version of the project.
     */
    @Parameter(property = "currentVersion", required = true)
    private String currentVersion;

    /**
     * The type of release to perform.
     * Options are: major, minor, patch, prerelease
     */
    @Parameter(property = "releaseType", required = true)
    private ReleaseType releaseType;

    public void execute() {
        SemverLexer lexer = new SemverLexer(CharStreams.fromString(currentVersion));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SemverParser parser = new SemverParser(tokens);

        SemverParser.SemverContext tree = parser.semver();

        String major = tree.versionCore().major.getText();
        String minor = tree.versionCore().minor.getText();
        String patch = tree.versionCore().patch.getText();
        SemverParser.PreReleaseContext preRelease = tree.preRelease();

        String newVersion = updateVersion(major, minor, patch, preRelease);

        System.out.println(newVersion);
    }

    private String updateVersion(String major, String minor, String patch, SemverParser.PreReleaseContext preRelease) {
        int majorInt = Integer.parseInt(major);
        int minorInt = Integer.parseInt(minor);
        int patchInt = Integer.parseInt(patch);

        switch (releaseType) {
            case MAJOR:
                majorInt++;
                minorInt = 0;
                patchInt = 0;
                return String.format("%d.%d.%d", majorInt, minorInt, patchInt);
            case MINOR:
                minorInt++;
                patchInt = 0;
                return String.format("%d.%d.%d", majorInt, minorInt, patchInt);
            case PATCH:
                patchInt++;
                return String.format("%d.%d.%d", majorInt, minorInt, patchInt);
            case PRERELEASE:
                if (preRelease == null) {
                    return String.format("%d.%d.%d-%s.%d", majorInt, minorInt, patchInt, "alpha", 0);
                }
                String tag = ((SemverParser.PreReleaseTaggedContext) preRelease)
                        .tag()
                        .getText();
                int tagVersionInt =
                        Integer.parseInt((((SemverParser.PreReleaseTaggedContext) preRelease).version).getText());
                tagVersionInt++;
                return String.format("%d.%d.%d-%s.%d", majorInt, minorInt, patchInt, tag, tagVersionInt);
        }

        throw new RuntimeException("Unknown release type: " + releaseType);
    }

    static class ReleaseTypeConverter extends AbstractBasicConverter {
        @Override
        protected Object fromString(String releaseType) {
            return ReleaseType.valueOf(releaseType.toUpperCase());
        }

        public boolean canConvert(Class<?> aClass) {
            return aClass.equals(ReleaseType.class);
        }
    }
}
