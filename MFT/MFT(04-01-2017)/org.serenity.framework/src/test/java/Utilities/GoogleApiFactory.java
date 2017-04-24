package Utilities;

import java.io.File;

public class GoogleApiFactory {

    private String userName;
    private String password;
    private String clientJson;
    private String serviceAccountUser;
    private String serviceAccountId;
    private String p12;
    private String jsonKeyFile;
    private boolean usingServiceAccount;
    private boolean usingJsonKey;
    private static AbstractGAppService service;
    private static GoogleApiFactory factory;

    private GoogleApiFactory(String userName, String password, String clientJson){
        this.userName = userName;
        this.password = password;
        this.clientJson = clientJson;
    }

    private GoogleApiFactory(String serviceAccountUser, String serviceAccountId, String p12, boolean serviceAccount){
        this.serviceAccountUser = serviceAccountUser;
        this.serviceAccountId = serviceAccountId;
        this.p12 = p12;
        this.usingServiceAccount = serviceAccount;
    }

    private GoogleApiFactory(String jsonKeyFile, boolean usingJsonKey) {
        this.jsonKeyFile = jsonKeyFile;
        this.usingJsonKey = usingJsonKey;
    }

    public static GoogleApiFactory configure(String userName, String password, String clientJson){
        factory = new GoogleApiFactory(userName, password, clientJson);
        return factory;
    }

    public static GoogleApiFactory configureServiceAccount(String serviceAccountUser, String serviceAccountId, String p12){
        factory = new GoogleApiFactory(serviceAccountUser, serviceAccountId, p12, true);
        return factory;
    }

    public static GoogleApiFactory configureWithJsonKey(String jsonKeyFile) {
        factory = new GoogleApiFactory(jsonKeyFile, true);
        return factory;
    }

    /**
     * returns the instance of Google API service
     * @param tClass Service class to pass
     * @param <T> Service class type extending the
     * @return service instance
     * @throws Exception
     */
    public <T extends AbstractGAppService> T getInstance(Class<T> tClass, String applicationName) throws Exception{
        if(usingServiceAccount){
            service = (T) (tClass.getConstructor(String.class, File.class, String.class, String.class)
                    .newInstance(applicationName, new File(p12), serviceAccountUser, serviceAccountId));
        }
        else if(usingJsonKey) {
            service = (T) (tClass.getConstructor(String.class, File.class)
                    .newInstance(applicationName, new File(jsonKeyFile)));
        }
        else {
            service = (T) (tClass.getConstructor(String.class, String.class, String.class, String.class)
                    .newInstance(applicationName, clientJson, userName, password));
        }
        return (T)service;
    }
}
