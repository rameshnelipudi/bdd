package Runners;

import Pages.ApproveFeedRequestPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import mail.ReadEmail;

/**
 * Created by E001489 on 03-01-2017.
 */
public class DataFeedApproveStepDefs {

    ApproveFeedRequestPage feedApprove;
    @And("^I should see approve button$")
    public void iShouldSeeApproveButton() throws Throwable {

        feedApprove.waitForApprove();
    }

    @Then("^I Should compare and approve feed request data$")
    public void iShouldCompareAndApproveFeedRequestData() throws Throwable {
        feedApprove.compareFeedRequestFormData();
    }

    @Then("^I click on approve$")
    public void iClickOnApprove() throws Throwable {
        feedApprove.ciickOnApprove();
    }



}
