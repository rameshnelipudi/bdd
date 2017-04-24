package Runners;

import Pages.JLRLoginPage;
import cucumber.api.java.en.Given;

/**
 * Created by E001489 on 07-12-2016.
 */
public class JLRLoginStepDefs {

    JLRLoginPage JLRUser;

    @Given("^I am on JLR ADL login page$")
    public void i_am_on_JLR_ADL_login_page()  {

        JLRUser.navigateJlrAnalyticDataLab();
    }

    @Given("^I login into JLR Analytic DataLab applicaton with valid \"([^\"]*)\" and  \"([^\"]*)\"$")
    public void i_login_into_JLR_Analytic_DataLab_applicaton_with_valid_and(String username, String pwd ) throws Throwable {
        JLRUser.loginToJLRAnalyticsDataLab(username,pwd);
    }
}
