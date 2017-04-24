package Pages;

import Utilities.services.BigQueryService;
import Utilities.GoogleApiFactory;
import net.thucydides.core.annotations.Step;

import java.io.IOException;
import java.util.List;

/**
 * Created by E002465 on 09-01-2017.
 */
public class BQPage extends BasePage {
    BigQueryService bigQueryService;
    String JSON_KEY_FILE="C:\\Users\\E002465\\Desktop\\mrw-test-insight.json";
    //String JSON_KEY_FILE="C:\\Users\\E002465\\Desktop\\jlr-dev-dl-a9cabf4ac51e.json";
    String project="jlr-dev-dl";
    @Step
    public void instantiateBigQuery() throws Exception {
        bigQueryService = GoogleApiFactory.configureWithJsonKey(JSON_KEY_FILE).getInstance(BigQueryService.class, "mrw-test-insight");
    }
    @Step
    public void checkTablesPresentInAGivenDataset(List<String> tables, String datasetId) throws IOException {
        List<String> tableListToCheck = bigQueryService.getTablesUnderDataSet(project, datasetId);
        for(String table : tables) {
            System.out.println("table<<>>>>>>>>>>"+table);
            assert tableListToCheck.contains(table);
        }
    }
}
