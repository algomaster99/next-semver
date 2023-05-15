package io.github.algomaster99.semver;

import java.util.concurrent.Callable;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import picocli.CommandLine;

/**
 * Increments the version number of a project.
 */
@CommandLine.Command(
        name = "next-semver",
        mixinStandardHelpOptions = true,
        description = "Increments the version number of a project.")
public class NextSemverMojo implements Callable<Integer> {

    /**
     * The current version of the project.
     */
    @CommandLine.Parameters(index = "0", description = "The current version of the project.")
    private String currentVersion;

    /**
     * The type of release to perform.
     */
    @CommandLine.Option(
            names = {"-r", "--release-type"},
            description = "The type of release to perform. Options are: ${COMPLETION-CANDIDATES}.")
    private ReleaseType releaseType;

    public Integer call() {
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
        return 0;
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
}
