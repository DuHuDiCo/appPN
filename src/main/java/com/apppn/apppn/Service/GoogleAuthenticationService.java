package com.apppn.apppn.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.web.servlet.view.RedirectView;

public interface GoogleAuthenticationService {

    public RedirectView getGoogleAuthentication();

    public GoogleClientSecrets createGoogleClientSecrets(String clientId, String clientSecret);


    public RedirectView googleCallBack(String code, List<String> scope) throws IOException, GeneralSecurityException ;

}
