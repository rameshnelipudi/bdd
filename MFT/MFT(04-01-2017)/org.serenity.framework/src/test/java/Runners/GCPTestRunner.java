package Runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by E002183 on 11/10/2016.
 */

/*@CucumberOptions(features="src/test/groovy/Features/",format = {"pretty","json:target/cucumber.json"},tags={"@smoketest"})
*/
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/java/Features/GCP.feature")
public class GCPTestRunner {


}
