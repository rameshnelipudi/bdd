package Utilities.services;


import Utilities.AbstractGAppService;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.api.services.bigquery.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reference:
 * https://cloud.google.com/bigquery/docs/reference/v2/jobs
 */
public class BigQueryService extends AbstractGAppService {

    Bigquery bigQueryService;

    public BigQueryService(String applicationName, File jsonKeyFile) throws Exception {
        setApplicationName(applicationName);
        setJsonKeyFile(jsonKeyFile);
        bigQueryService = (Bigquery) getService();
    }

    @Override
    public AbstractGoogleJsonClient getService() throws Exception {
        return new Bigquery.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
                .setHttpRequestInitializer(authoriseWithJson())
                .setApplicationName(getApplicationName())
                .build();
    }

    @Override
    public List<String> getScopes() {
        return new ArrayList<>(Collections.singletonList(BigqueryScopes.BIGQUERY));
    }

    public List<String> getDataSets(String projectId) throws IOException {
        Bigquery.Datasets.List request = bigQueryService.datasets().list(projectId);
        DatasetList response;
        List<String> datasets = new ArrayList<>();
        do {
            response = request.execute();
            if (response.getDatasets() == null) continue;
            for(DatasetList.Datasets dataset : response.getDatasets()) {
                datasets.add(dataset.getId());
            }
            request.setPageToken(response.getNextPageToken());
        } while (response.getNextPageToken() != null);
        return datasets;
    }

    public List<String> getTablesUnderDataSet(String projectId, String datasetId) throws IOException {
        Bigquery.Tables.List request = bigQueryService.tables().list(projectId, datasetId);
        TableList response;
        List<String> tables = new ArrayList<>();
        do {
            response = request.execute();
            if (response.getTables() == null) continue;
            for (TableList.Tables table : response.getTables()) {
                tables.add(table.getTableReference().getTableId());
            }

            request.setPageToken(response.getNextPageToken());
        } while (response.getNextPageToken() != null);

        return tables;
    }

    public Table getTable(String projectId, String datasetId, String tableId) throws IOException {
        Bigquery.Tables.Get request = bigQueryService.tables().get(projectId, datasetId, tableId);
        return request.execute();
    }

    public void checkTableSchema(String projectId, String datasetId, String tableId, String expectedSchema) throws Throwable {

        //  parsing expected table schema
        JSONParser jsonParser = new JSONParser();
        JSONObject expectedJSONObject = (JSONObject) jsonParser.parse(expectedSchema);
        JSONArray expectedArray = (JSONArray) expectedJSONObject.get("fields");

        System.out.print("expectedArray======="+expectedArray);

        //retrieving actual table schema
        Table table = getTable(projectId, datasetId, tableId);
        TableSchema schema = table.getSchema();
        JSONArray actualArray = new JSONArray();

        for(TableFieldSchema field:schema.getFields()) {
            actualArray.add(field);
        }
        System.out.print("actualAry======="+actualArray);
        assert actualArray.size()==expectedArray.size();
        assert actualArray.equals(expectedArray);

    }

    /**TODO Find way to return results in a usable format
     *   Note - Response is saved to a table in bigquery identifiable by
     *          bigquery.tabledata.list(table_reference) or bigquery.jobs.getQueryResults(job_reference),
     *          both of which cna be identified by the initial response.
     *          This response table expires after 24 hours.
     **/
    public void runQuery(String projectId, String queryString) throws IOException {
        QueryRequest query = new QueryRequest();
        query.setQuery(queryString);
        Bigquery.Jobs.Query request = bigQueryService.jobs().query(projectId, query);
        QueryResponse response = request.execute();
        List<TableRow> rows = response.getRows();
        String tableRow = "";
        for(TableRow row : rows) {
            for(TableCell cell : row.getF()) {
                tableRow = tableRow + "| " + cell.getV().toString();
            }
            System.out.println(tableRow);
        }
    }

    public void deleteTable(String projectId, String dataset, String tableName) throws IOException {
        Bigquery.Tables.Delete request = bigQueryService.tables().delete(projectId, dataset, tableName);
        request.execute();
    }


}