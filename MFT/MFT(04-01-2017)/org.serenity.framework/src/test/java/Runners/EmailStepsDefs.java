package Runners;

import Pages.ReadEmailPage;
import cucumber.api.java.en.And;

/**
 * Created by E001489 on 03-01-2017.
 */
public class EmailStepsDefs {

    ReadEmailPage mail;

    @And("^I should see review button in email and click$")
    public void iShouldSeeTheApprovalEmailAndClickOnReview() throws InterruptedException {

        mail.clickOnReviewEmailButton();
    }



}
