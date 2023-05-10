package io.github.algomaster99.semver;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "next-semver")
public class NextSemverMojo extends AbstractMojo {

    @Parameter(property = "currentVersion", required = true)
    private String currentVersion;

    public void execute() {
        SemverLexer lexer = new SemverLexer(CharStreams.fromString(currentVersion));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SemverParser parser = new SemverParser(tokens);

        SemverParser.SemverContext tree = parser.semver();

        String major = tree.versionCore().major.getText();
        String minor = tree.versionCore().minor.getText();
        String patch = tree.versionCore().patch.getText();
        String preRelease = tree.preRelease() != null ? tree.preRelease().getText() : "";

        System.out.println("major: " + major);
        System.out.println("minor: " + minor);
        System.out.println("patch: " + patch);
        System.out.println("preRelease: " + preRelease);
    }
}
