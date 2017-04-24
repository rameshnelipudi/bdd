package Utilities;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;

import java.util.List;

/**
 * Created by Yuvarej on 09/06/2016.
 */
public interface IGoogleService {

    AbstractGoogleJsonClient getService() throws Exception;

//    Credential authorize() throws Exception;

    Credential authorizeByServiceAccount() throws Exception;

    List<String> getScopes();
}