package Runners;

import Pages.JLRLoginPage;
import Pages.MasterDataPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * Created by E001489 on 04-01-2017.
 */
public class MasterDataStepDefs {
    MasterDataPage masterData;
    JLRLoginPage Login;

    @Given("^I am on MasterData page$")
    public void iAmOnMasterDataPage() throws Throwable {
        masterData.logOnToMasterDataPage();

    }

    @When("^I search master data as \"([^\"]*)\" to verify feeds$")
    public void iSearchMasterDataAsToVerigyFeeds(String masterDataValue) throws Throwable {
        masterData.sendMasterDatavalue(masterDataValue);
    }


    @When("^I login into jlr appsflow with valid \"([^\"]*)\" and  \"([^\"]*)\"$")
    public void iLoginIntoJlrAppsflowWithValidAnd(String user, String pwd) throws Throwable {
        Login.loginToAppsFlow(user,pwd);
    }

    @And("^I cliked on masterdata  link$")
    public void iClikedOnMasterdataLink() throws Throwable {
       masterData.clickOnMaterDataLink();
    }
}
