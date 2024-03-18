package Sakancom.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = "myFeatures",
        plugin = "html:target/cucumber/report.html",
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = "Sakancom.acceptance")



public class AcceptanceTest {
}
