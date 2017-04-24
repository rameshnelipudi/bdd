package Runners;

import Pages.GCPPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * Created by E001489 on 07-12-2016.
 */
public class GCPStepDefs {
    GCPPage gcpPage;
    @Given("^I am on GCP page$")
    public void i_am_on_GCP_page() throws Throwable {
        gcpPage.navigate_to_GCP();

    }

    @When("^I Switch to \"([^\"]*)\" project$")
    public void i_Switch_to(String switchToProject) throws Throwable {
    gcpPage.switchToProject(switchToProject);
    }

    @When("^Select \"([^\"]*)\"$")
    public void select(String prodandServName) throws Throwable {
    gcpPage.navigateTopands(prodandServName);
    }

    @When("^I click on \"([^\"]*)\" bucket$")
    public void i_click_on_bucket(String bucketName) throws Throwable {
        gcpPage.selectingBucket(bucketName);
    }

    @When("^I click on \"([^\"]*)\" to upload a file$")
    public void i_click_on_to_upload_a_file(String arg1) throws Throwable {
        gcpPage.uploadFile();
    }


    @When("^I switch to \"([^\"]*)\" project$")
    public void iSwitchToProject(String switchToProject) throws Throwable {
        gcpPage.switchToProject(switchToProject);
    }

    @Then("^I should see uploaded file in \"([^\"]*)\" destination bucket$")
    public void iShouldSeeUploadedFileInDestinationBucket(String bucketName) throws Throwable {
        gcpPage.selectingBucket(bucketName);
        gcpPage.verifyFileInDestBucket();
    }


}
