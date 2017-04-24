package Utilities.services;

/*import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
*//*import com.google.api.services.datastore.v1.Datastore;
import com.google.api.services.datastore.v1.DatastoreScopes;
import com.google.api.services.datastore.v1.model.*;*//*
import org.junit.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.datastore.Datastore;
import com.google.api.services.datastore.DatastoreScopes;
import com.google.api.services.datastore.model.*;
import Utilities.AbstractGAppService;


*//**
 * Created by Chandra on 22/09/2016.
 *//*
public class DataStoreService extends AbstractGAppService {

    static Datastore dataStoreService;

    public DataStoreService(String appName, File jsonKeyFile) throws Exception {
        setApplicationName(appName);
        setJsonKeyFile(jsonKeyFile);
        dataStoreService = (Datastore) getService();
    }

//    @Override
//    public AbstractGoogleJsonClient getService() throws Exception {
//        return new Datastore.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
//                .setHttpRequestInitializer(authoriseWithJson())
//                .setApplicationName(getApplicationName())
//                .build();
//    }


    @Override
    public AbstractGoogleJsonClient getService() throws Exception {
        Credential credential = authoriseWithJson();
        return new Datastore.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(getApplicationName())
                .build();
    }

//    @Override
//    public List<String> getScopes() {
//        return new ArrayList<>(Collections.singletonList(DatastoreScopes.CLOUD_PLATFORM));
//    }

    @Override
    public List<String> getScopes() {
        return new ArrayList<>(DatastoreScopes.all());
    }


    public List<EntityResult> getTableDataByName(String tableName) throws Exception {
        return getResponseFromQueryString(tableName, false);
    }

    public List<EntityResult> getQueryResults(String queryString) throws Exception {
        return getResponseFromQueryString(queryString, true);
    }

    public String getPropertyValueByNameInGivenTable(String propertyName, int rowIndexInTable, String tableName) throws Exception {
        List<EntityResult> tableRecords = getTableDataByName(tableName);
        EntityResult entityResultAtGivenIndex = tableRecords.get(rowIndexInTable);
        Value expectedValue = entityResultAtGivenIndex.getEntity().getProperties().get(propertyName);
        return getPropertyValueByDataType(expectedValue);
    }

    public List<EntityResult> getResponseFromQueryString(String givenQueryOrTableName, boolean isQuery) throws Exception {
        String finalQuery = isQuery ? givenQueryOrTableName : "SELECT * FROM " + givenQueryOrTableName;
        RunQueryRequest runQueryRequest = new RunQueryRequest();
        GqlQuery gqlQuery = new GqlQuery();
        gqlQuery.setQueryString(finalQuery);
        runQueryRequest.setGqlQuery(gqlQuery);
        RunQueryResponse response = dataStoreService.projects()
                .runQuery(getApplicationName(), runQueryRequest).execute();
        return response.getBatch().getEntityResults();
    }

    public String getPropertyValueByDataType(Value value) {
        String actualDataTypeOfValue = null;
        Object[] keySetinValues = value.keySet().toArray();
        for (int i = 0; i < keySetinValues.length; i++) {
            if (!(keySetinValues[i].toString().contains("excludeFromIndexes")))
                actualDataTypeOfValue = keySetinValues[i].toString();
        }
        String stringConvertedValue = null;
        switch (actualDataTypeOfValue) {
            case "booleanValue":
                stringConvertedValue = value.getBooleanValue().toString();
                break;
            case "arrayValue":
                stringConvertedValue = value.getArrayValue().toString();
                break;
            case "stringValue":
                stringConvertedValue = value.getStringValue();
                break;
        }
        return stringConvertedValue;
    }

    public void setPropertyValueByDataType(Value value, Object valueToBeSet) {
        String actualDataTypeOfValue = value.keySet().toArray()[0].toString();
        switch (actualDataTypeOfValue) {
            case "booleanValue":
                value.setBooleanValue(Boolean.valueOf(valueToBeSet.toString()));
                break;
            case "stringValue":
                value.setStringValue(valueToBeSet.toString());
                break;
        }
    }

    public void setPropertyValueByNameInGivenTable(String propertyName, Object valueTobeSent, int rowIndexInTable, String tableName) throws Exception {
        List<EntityResult> tableRecords = getTableDataByName(tableName);
        EntityResult entityResultAtGivenIndex = tableRecords.get(rowIndexInTable);
        Value expectedValue = entityResultAtGivenIndex.getEntity().getProperties().get(propertyName);
        setPropertyValueByDataType(expectedValue, valueTobeSent);
        entityResultAtGivenIndex.setEntity(entityResultAtGivenIndex.getEntity());
    }

    public void checkForFileNotificationWithName(String fileName) throws Exception {
        List<EntityResult> matchedRecords = getTableDataByName("Notification");
        List<String> matchedFileName = new ArrayList();
        for (EntityResult entityResult : matchedRecords) {
            Value expectedValue = entityResult.getEntity().getProperties().get("fileName");
            String fileNameInRecord = getPropertyValueByDataType(expectedValue);
            if (fileNameInRecord.equals(fileName)) {
                matchedFileName.add(fileName);
            }
        }
        Assert.assertEquals("Number of matched records is not 1", matchedFileName.size(), 1);
    }

    public void checkForIngestStatusWithFilename(String status, String fileName) throws Exception {
        String query = "Select * from IngestJob ORDER BY startTime DESC";
        String outcomeInRecord = null;
        List<EntityResult> matchedRecords = getQueryResults(query);
        for (EntityResult entityResult : matchedRecords) {
            Value expectedValue = entityResult.getEntity().getProperties().get("target");
            String fileNameInRecord = getPropertyValueByDataType(expectedValue);
            if (fileNameInRecord.contains(fileName)) {
                Value outcomeValueAtRecord = entityResult.getEntity().getProperties().get("status");
                outcomeInRecord = getPropertyValueByDataType(outcomeValueAtRecord);
            }
        }
        Assert.assertEquals("status mismatched", outcomeInRecord, status);
    }

    public List<String> getIngestOutcomesForGivenFile(String fileName) throws Exception {
        String jobID = getIngestJobIDFromFileName(fileName);
        List<String> ingestOutcomeValues = new ArrayList<>();
        String query = "Select * from IngestOutcome";
        List<EntityResult> matchedRecords = getQueryResults(query);
        for (EntityResult entityResult : matchedRecords) {
            Value expectedValue = entityResult.getEntity().getProperties().get("jobId");
            String ingestJobID = getPropertyValueByDataType(expectedValue);
            if (ingestJobID.equals(jobID)) {
                Value messageValueAtRecord = entityResult.getEntity().getProperties().get("message");
                ingestOutcomeValues.add(getPropertyValueByDataType(messageValueAtRecord));
            }
        }
        return ingestOutcomeValues;
    }

    public String getIngestJobIDFromFileName(String fileName) throws Exception {
        String ingestJobID = null;
        String query = "Select * from IngestJob ORDER BY startTime DESC";
        List<EntityResult> matchedRecords = getQueryResults(query);
        for (EntityResult entityResult : matchedRecords) {
            Value expectedValue = entityResult.getEntity().getProperties().get("target");
            String fileNameInRecord = getPropertyValueByDataType(expectedValue);
            if (fileNameInRecord.contains(fileName)) {
                Value jobIDValueAtRecord = entityResult.getEntity().getProperties().get("jobId");
                ingestJobID = getPropertyValueByDataType(jobIDValueAtRecord);
            }
        }
        return ingestJobID;
    }

    public void iCheckOutcomesForGivenFile(List<String> expectedOutcomes, String fileName) throws Exception {
        List<String> actualOutcomesList = getIngestOutcomesForGivenFile(fileName);
        for (String eachExpectedOutcome : expectedOutcomes) {
            Assert.assertTrue("message : '" + eachExpectedOutcome + "' missing in ingest outcome", actualOutcomesList.contains(eachExpectedOutcome));
        }
    }

    public void checkForIngestMessageWithFilename(String outcome, String fileName) throws Exception {
        String query = "Select * from IngestJob ORDER BY startTime DESC";
        String outcomeInRecord = null;
        List<EntityResult> matchedRecords = getQueryResults(query);
        for (EntityResult entityResult : matchedRecords) {
            Value expectedValue = entityResult.getEntity().getProperties().get("target");
            String fileNameInRecord = getPropertyValueByDataType(expectedValue);
            if (fileNameInRecord.contains(fileName)) {
                Value outcomeValueAtRecord = entityResult.getEntity().getProperties().get("outcome");
                outcomeInRecord = getPropertyValueByDataType(outcomeValueAtRecord);
            }
        }
        Assert.assertEquals("outcome mismatched", outcomeInRecord, outcome);
    }
}*/
