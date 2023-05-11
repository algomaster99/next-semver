package io.github.algomaster99.semver;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.configurator.BasicComponentConfigurator;
import org.codehaus.plexus.component.configurator.ComponentConfigurator;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;

@Component(role = ComponentConfigurator.class, hint = "basic")
public class MojoParameterChecker extends BasicComponentConfigurator implements Initializable {
    public void initialize() {
        converterLookup.registerConverter(new NextSemverMojo.ReleaseTypeConverter());
    }
}
