package Utilities.services;



/**
 * Created by Chandra on 27/09/2016.
 */

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.com.google.api.client.util.*;
import org.junit.Test;

//https://ikaisays.com/2010/06/03/introduction-to-working-with-app-engine%E2%80%99s-low-level-datastore-api/
//https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/unittests/src/test/java/com/google/appengine/samples/LocalDatastoreTest.java

public class NewDataStoreService {


//    final LocalServiceTestHelper helper =
//            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
//
//    @Before
//    public void setUp() {
//        helper.setUp();
//    }
//
//    @After
//    public void tearDown() {
//        helper.tearDown();
//    }


    @Test
    public void start() {

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        try {
            //Key myKey = KeyFactory.createKey("TestWVTA", "5629499534213120");

            Key myKey = KeyFactory.createKey("TestWVTA", "mydata");
            Entity myEntity = datastore.get(myKey);
            System.out.println(myEntity.getProperty("Type"));

            //System.out.println(datastore.prepare(new Query("SELECT * FROM TestWVTA")).countEntities(withLimit(10)));
            //assertEquals(0, datastore.prepare(new Query("mydata")).countEntities(withLimit(10)));
        }
        catch(EntityNotFoundException e){
            e.printStackTrace();
        }

    }


}
