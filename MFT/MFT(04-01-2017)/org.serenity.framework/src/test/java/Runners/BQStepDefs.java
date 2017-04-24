package Runners;

import Pages.BQPage;
import Pages.GCPPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.List;


/**
 * Created by E001489 on 07-12-2016.
 */
public class BQStepDefs {
    //String dataset="test_mft_upload";
    BQPage bqPage;
    @Given("^I instantiate the BigQuery service$")
    public void instantiateBigQueryService() throws Throwable {
        bqPage.instantiateBigQuery();
    }

    @Given("^I log into the Google console$")
    public void logIntoBigQueryConsole() {
       // bqPage.login();
    }
    @Given("^the expected tables are present under the \"(.*?)\" dataset$")
    public void assertTablesArePresentWithinDataset(String dataset, DataTable table) throws IOException {
        List<String> tableNames = table.asList(String.class);
        bqPage.checkTablesPresentInAGivenDataset(tableNames, dataset);
    }

}
