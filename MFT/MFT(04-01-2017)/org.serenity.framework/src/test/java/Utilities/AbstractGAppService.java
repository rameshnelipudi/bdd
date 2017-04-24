package Utilities;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;

public abstract class AbstractGAppService implements IGoogleService{

    protected static Logger LOG = LoggerFactory.getLogger(AbstractGAppService.class);
    /** Global instance of the JSON factory. */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    @NotNull
    private String applicationName;
    @NotNull
    private String clientSecretJson;
    @NotNull
    private File p12KeyFile;
    @NotNull
    public String userName;
    @NotNull
    public String password;
    @NotNull
    private String serviceAccountUserEmail;
    @NotNull
    private String serviceAccountId;
    @NotNull
    private File jsonKeyFile;

    protected static HttpTransport HTTP_TRANSPORT;
    /** Directory to store user credentials for this application. */
    private static final File DATA_STORE_DIR = new File(
            System.getProperty("user.home"), "/temp/secret_google_cred.json");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static String LOCAL_HOST = "localhost";
    private static int PORT =  51818;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public Credential authoriseWithJson() throws Exception {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(getJsonKeyFile()), HTTP_TRANSPORT, JSON_FACTORY);
        credential = credential.createScoped(getScopes());
        return credential;
    }

    @Override
    public Credential authorizeByServiceAccount() throws Exception{

        return new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountUser(getServiceAccountUserEmail())
                .setServiceAccountId(getServiceAccountId())
                .setServiceAccountScopes( getScopes() )
                .setServiceAccountPrivateKeyFromP12File(getP12KeyFile())
                .build();
    }

//    @Override
//    public Credential authorize() throws Exception {
//        FileUtils.deleteDirectory(DATA_STORE_DIR);
//        DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
//        // Load client secrets.
//        GoogleClientSecrets clientSecrets =
//                GoogleClientSecrets.load(JSON_FACTORY,
//                        new InputStreamReader(new FileInputStream(new File(getClientSecretJson()))));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow =
//                new GoogleAuthorizationCodeFlow.Builder(
//                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, getScopes())
//                        .setDataStoreFactory(DATA_STORE_FACTORY)
//                        .setAccessType("offline")
//                        .build();
//        LocalServerReceiver serverReceiver = new LocalServerReceiver.Builder()
//                .setHost(LOCAL_HOST).setPort(PORT).build();
//        Credential credential = new SeleniumAuthorizationForGoogleApp(
//                flow, serverReceiver).authorize("user", getUserName(), getPassword());
//        LOG.info("Credentials saved to {}", DATA_STORE_DIR.getAbsolutePath());
//        return credential;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String getServiceAccountUserEmail() {
        return serviceAccountUserEmail;
    }

    protected void setServiceAccountUserEmail(String serviceAccountUserEmail) {
        this.serviceAccountUserEmail = serviceAccountUserEmail;
    }

    private String getServiceAccountId() {
        return serviceAccountId;
    }

    protected void setServiceAccountId(String serviceAccountId) {
        this.serviceAccountId = serviceAccountId;
    }

    private File getP12KeyFile() {
        return p12KeyFile;
    }

    protected void setP12KeyFile(File p12KeyFile) {
        this.p12KeyFile = p12KeyFile;
    }

    private String getClientSecretJson() {
        return clientSecretJson;
    }

    protected void setClientSecretJson(String clientSecretJson) {
        this.clientSecretJson = clientSecretJson;
    }

    protected String getApplicationName() {
        return applicationName;
    }

    protected void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    protected void setJsonKeyFile(File jsonKeyFile) {
        this.jsonKeyFile = jsonKeyFile;
    }

    private File getJsonKeyFile() {
        return jsonKeyFile;
    }
}