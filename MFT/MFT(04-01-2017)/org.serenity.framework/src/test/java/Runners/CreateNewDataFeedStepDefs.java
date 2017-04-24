package Runners;

import Pages.DataFeedRequestFormPage;
import Pages.JLRLandingPage;
import Pages.JLRLoginPage;

import Pages.ReadEmailPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mail.ReadEmail;


import java.awt.*;

/**
 * Created by E001489 on 08-12-2016.
 */
public class CreateNewDataFeedStepDefs {

    //  JLRLandingPage JLRHomePage;
    JLRLandingPage JLRUser;
    JLRLoginPage loginPage;

    DataFeedRequestFormPage userDataFeedRequestForm;

 /*   @When("^I clicked on New data feed request$")
    public void i_clicked_on_New_data_feed_request() {

        JLRUser.clikOnRequiredLeftNavLink();
    }
*/

   /* @Then("^I Entered  Request title$")
    public void i_Entered_Request_title() throws Throwable {

        JLRUser.submitNewDataFeedRequestForm();
    }*/

    @And("^I clicked on \"([^\"]*)\"$")
    public void iClickedOn(String linkname) throws Throwable {

        JLRUser.clickOnJlrLeftNavLink(linkname);
    }

    @Then("^I should see DataLab UploadSearch Data Feed Request form page$")
    public void iShouldSeeDataLabUploadSearchDataFeedRequestFormPage() {

        JLRUser.dataFeedReqformPage();
    }

    @Then("^I entered  \"([^\"]*)\"$")
    public void iEntered(String reqestTitle) throws Throwable {
        userDataFeedRequestForm.setRequestTitleFieldValue(reqestTitle);
    }

    @Then("^I entered \"([^\"]*)\" as value of budget value$")
    public void iEnteredAsValueOfBudgetValue(String valueOfBudget) throws Throwable {
        userDataFeedRequestForm.setValueofBudgetFieldValue(valueOfBudget);    }

    @And("^I entered FeedName as \"([^\"]*)\"$")
    public void iEnteredFeedNameAs(String feedName) throws Throwable {
        userDataFeedRequestForm.setFeedNameFieldValue(feedName);
    }

    @And("^I selected  destinationBucket as \"([^\"]*)\"$")
    public void iSelectedDestinationBucketAs(String destinationBktVal) throws Throwable {
        userDataFeedRequestForm.setDestinationBucketFieldValue(destinationBktVal);
    }

    @And("^I selected project id as \"([^\"]*)\"$")
    public void iSelectedProjectIdAs(String projId) throws Throwable {
        userDataFeedRequestForm.setProjectIdFieldValue(projId);
    }

    @And("^I entered FolderName as \"([^\"]*)\"$")
    public void iEnteredFolderNameAs(String folderNameFieldValue) throws Throwable {
        userDataFeedRequestForm.setFolderNameFieldValue(folderNameFieldValue);
    }

    @And("^I selected FeedStatus as \"([^\"]*)\"$")
    public void iSelectedFeedStatusAs(String feedStatusFieldValue) throws InterruptedException {
        userDataFeedRequestForm.setFeedStatusFieldValue(feedStatusFieldValue);

    }

    @And("^I entered contactEmailAddress as \"([^\"]*)\"$")
    public void iEnteredContactEmailAddressAs(String contactEmailAddressFieldValue) throws Throwable {
        userDataFeedRequestForm.setContactEmailAddressFieldValue(contactEmailAddressFieldValue);
    }

    @And("^I entered Tags as \"([^\"]*)\"$")
    public void iEnteredTagsAs(String testtagsFieldValuetag) throws Throwable {
        userDataFeedRequestForm.setTagsFieldValue(testtagsFieldValuetag);
    }

    @And("^I entered feedDescription \"([^\"]*)\"$")
    public void iEnteredFeedDescription(String feedDescriptionFieldValue) throws Throwable {

        userDataFeedRequestForm.setFeedDescriptionFieldValue(feedDescriptionFieldValue);
    }

    @And("^I entered dataOwner \"([^\"]*)\"$")
    public void iEnteredDataOwner(String dataOwner) {
        userDataFeedRequestForm.setDataOwnerFieldValue(dataOwner);
    }

    @And("^I selected DataSensitivity as \"([^\"]*)\"$")
    public void iSelectedDataSensitivityAs(String dataSensitivityFieldValue) throws Throwable {
        userDataFeedRequestForm.setDataSensitivityFieldValue(dataSensitivityFieldValue);
    }

    @And("^I selected ImportDataToBigQuery flag as \"([^\"]*)\"  and fill bigQuery definition values if yes$")
    public void iSelectedImportDataToBigQueryFlagAsAndFilledBigQueryDefinitionValues(String imporrtDataToBigQueryFlagValue) throws InterruptedException, AWTException {
        userDataFeedRequestForm.setImporrtDataToBigQueryFlagValue(imporrtDataToBigQueryFlagValue);
    }

    @And("^I cliked on submit$")
    public void iClikedOnSubmit() throws Throwable {
        userDataFeedRequestForm.submitNewFeedDataLabRequestForm();
    }

    @And("^I entered supporting comments$")
    public void iEnteredSupportingComments() throws Throwable {
        userDataFeedRequestForm.setSupportingCommentsText();
    }


    @And("^^I logined as approver with valid user and password$")
    public void iLoginedAsApproverWithValidAnd() throws Throwable {

        loginPage.loginTogmail();
    }



    @And("^I entered remeadyId as \"([^\"]*)\"$")
    public void iEnteredRemeadyIdAs(String RemedyDdy) throws Throwable {
        userDataFeedRequestForm.setRemedyIdFieldValue(RemedyDdy);
    }


}