package Utilities.services;

import Utilities.AbstractGAppService;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storage.model.*;
import cucumber.api.DataTable;
import org.junit.Assert;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GCloudService extends AbstractGAppService {

    Storage gcloudService;
    private static Path currentRelativePath = Paths.get("");
    private static File pathToTarget = new File(currentRelativePath.toAbsolutePath().toString() + "/target");

    public GCloudService(String applicationName, File jsonKeyFile) throws Exception {
        setApplicationName(applicationName);
        setJsonKeyFile(jsonKeyFile);
        gcloudService = (Storage) getService();
    }

    public GCloudService(String applicationName, File p12Key, String serviceAccountUser, String serviceAccountId) throws Exception {
        this.setApplicationName(applicationName);
        this.setP12KeyFile(p12Key);
        this.setServiceAccountId(serviceAccountId);
        this.setServiceAccountUserEmail(serviceAccountUser);
        this.gcloudService = (Storage) this.getService();
    }

    @Override
    public AbstractGoogleJsonClient getService() throws Exception {
        return (new Storage.Builder(HTTP_TRANSPORT, JSON_FACTORY, (HttpRequestInitializer) null))
                .setHttpRequestInitializer(authoriseWithJson())
                .setApplicationName(this.getApplicationName())
                .build();
    }

    @Override
    public List<String> getScopes() {
        return new ArrayList<>(Collections.singletonList(StorageScopes.DEVSTORAGE_FULL_CONTROL));
    }

    /**
     * Uploads data to an object in a bucket.
     *
     * @param objectName  the name of the destination object.
     * @param contentType the MIME type of the data.
     * @param file        the file to upload.
     * @param bucketName  the name of the bucket to create the object in.
     */
    public void upload(String objectName, String contentType, File file, String bucketName)
            throws IOException, GeneralSecurityException {
        InputStreamContent contentStream = new InputStreamContent(contentType, new FileInputStream(file));
        // Setting the length improves upload performance
        contentStream.setLength(file.length());
        StorageObject objectMetadata = new StorageObject()
                // Set the destination object name
                .setName(objectName)
                // Set the access control list to publicly read-only
                .setAcl(Arrays.asList(
                        new ObjectAccessControl().setEntity("allUsers").setRole("READER")));
        // Do the insert
        Storage.Objects.Insert insertRequest = gcloudService.objects().insert(
                bucketName, objectMetadata, contentStream);
        System.out.println(bucketName + " , " + objectName + " , " + file + " , " + contentType);
        insertRequest.execute();
    }

    /**
     * Uploads all data from a folder to objects in a bucket.
     *
     * @param namePrefix  prefix for the file name (defines the folders in the bucket).
     * @param folder      the name of the folder containing objects for upload.
     * @param contentType the MIME type of the data.
     * @param bucketName  the name of the bucket to create the object in.
     */
    public void upload(String namePrefix, String folder, String contentType, String bucketName) throws IOException, GeneralSecurityException {
        File[] files = new File(folder).listFiles();
        if (files != null) {
            for (File file : files) {
                upload(namePrefix + file.getName(), contentType, file, bucketName);
            }
        }
    }

    /**
     * Uploads all data from a folder to objects in a bucket.
     *
     * @param objectPrefix prefix for the object to be downloaded (defines the folders in the bucket).
     * @param fileName     the name of the file to be downloaded.
     * @param bucketName   the name of the bucket to create the object in.
     * @param targetFolder path to destination folder.
     */
    public void download(String objectPrefix, String fileName, String bucketName, String targetFolder) throws IOException {
        Storage.Objects.Get getObject = gcloudService.objects().get(bucketName, objectPrefix + fileName);

        // Downloading data.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        getObject.getMediaHttpDownloader().setDirectDownloadEnabled(true);
        getObject.executeMediaAndDownloadTo(out);

        //File Creation and streaming to file
        OutputStream fileOut = new FileOutputStream(pathToTarget + "/" + targetFolder + "/" + fileName);
        out.writeTo(fileOut);
        System.out.println("File '" + fileName + "' created");
    }

    /**
     * Returns a list of folders in t.
     *
     * @param pathToFolder path to the folder to check.
     * @param bucketName   the name of the bucket to create the object in.
     */
    public List<String> listObjectsInFolder(String pathToFolder, String bucketName, String regex) throws IOException {
        List<String> folderNames = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Storage.Objects.List listObjects = gcloudService.objects().list(bucketName).setPrefix(pathToFolder);
        Objects objects;
        do {
            objects = listObjects.execute();
            for (StorageObject object : objects.getItems()) {
                Matcher matcher = pattern.matcher(object.getName());
                if (matcher.matches()) {
                    folderNames.add(object.getName().substring(object.getName().indexOf("2016")));
                }
            }
            listObjects.setPageToken(objects.getNextPageToken());
        } while (null != objects.getNextPageToken());
        return folderNames;
    }


    /**
     * Returns list of bucket names for a specified project
     *
     * @param projectId the project id.
     */
    public List<String> getBucketList(String projectId) throws IOException {
        Storage.Buckets.List bucketsList = gcloudService.buckets().list(projectId);
        Buckets buckets;
        List<String> bucketNames = new ArrayList<>();
        do {
            buckets = bucketsList.execute();
            for (Bucket bucket : buckets.getItems()) {
                bucketNames.add(bucket.getName());
            }
            bucketsList.setPageToken(buckets.getNextPageToken());
        } while (null != buckets.getNextPageToken());
        return bucketNames;
    }

    public void waitForFilePresent(String fileName, String bucket, String subFolder, int timeOutInSec) throws Exception {
        for (int i = 0; i <= timeOutInSec; i++) {
            boolean check = isFileExist(fileName, bucket, subFolder);
            if (check) {
                break;
            } else {
                Thread.sleep(1000);
                if (i == timeOutInSec) {
                    throw new TimeoutException("File not found in given " + timeOutInSec + "sec");
                }
            }
        }
    }

    public void waitForFileDisappear(String fileName, String bucket, String subFolder, int timeOutInSec) throws Exception {
        for (int i = 0; i <= timeOutInSec; i++) {
            boolean check = isFileExist(fileName, bucket, subFolder);
            if (!check) {
                break;
            } else {
                Thread.sleep(1000);
                if (i == timeOutInSec) {
                    throw new TimeoutException("File not Disappear in given " + timeOutInSec + "sec");
                }
            }
        }
    }

    public boolean isFileExist(String fileName, String bucket, String subFolder) throws Exception {
        boolean isExist = false;
        String expectedFileName = subFolder + fileName;
        Storage.Objects.List listObjects = gcloudService.objects().list(bucket).setPrefix(subFolder);
        List<StorageObject> storageObjects = listObjects.execute().getItems();
        List<String> existingfileNames = new ArrayList<>();
        for (StorageObject object : storageObjects) {
            existingfileNames.add(object.getName());
        }

        for (String existingFileName : existingfileNames) {
            if (existingFileName.equals(expectedFileName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public void checkGivenFilesInGivenFilder(DataTable dataTable, String bucketName) throws Exception {
        List<List<String>> givenTable = dataTable.raw();
        for (int i = 0; i < givenTable.size(); i++) {
            List<String> givenRow = givenTable.get(i);
            String fileName = givenRow.get(0);
            String folderName = givenRow.get(1);
            Assert.assertTrue("File " + fileName + " is not present in " + bucketName + "/" + folderName, isFileExist(fileName, bucketName, folderName));
        }
    }
}
