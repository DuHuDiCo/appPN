package com.apppn.apppn.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

public interface GoogleAuthenticationService {

    public RedirectView getGoogleAuthentication();

    public GoogleClientSecrets createGoogleClientSecrets(String clientId, String clientSecret);


    public ResponseEntity<?> googleCallBack(String code, List<String> scope) throws IOException, GeneralSecurityException ;


    public ResponseEntity<?> verifyTokenAndGetUserProfile(String accessToken) throws GeneralSecurityException, IOException;

}
