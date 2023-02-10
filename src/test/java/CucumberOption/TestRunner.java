package CucumberOption;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//FeatureFile
//StepDefinition

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Feature/Login.feature",
        glue = "StepDefinition"
)
public class TestRunner {

}
