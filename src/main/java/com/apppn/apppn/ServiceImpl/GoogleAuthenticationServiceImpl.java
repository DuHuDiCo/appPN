package com.apppn.apppn.ServiceImpl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.apppn.apppn.Service.GoogleAuthenticationService;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class GoogleAuthenticationServiceImpl implements GoogleAuthenticationService {

    @Value("${google.clientId}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${url-back}")
    private String urlBackend;

    private final String APPLICATION_NAME;
    private final JsonFactory JSON_FACTORY;
    private final List<String> SCOPE;

    public GoogleAuthenticationServiceImpl( ) {
        APPLICATION_NAME = "APPPN";
        JSON_FACTORY = JacksonFactory.getDefaultInstance();
        SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
                "https://www.googleapis.com/auth/userinfo.email",
                "openid");
    }

    @Override
    public RedirectView getGoogleAuthentication() {
        StringBuilder authorizationUrl = new StringBuilder();

        authorizationUrl.append("https://accounts.google.com/o/oauth2/auth?response_type=code&client_id="
                .concat(clientId)
                .concat("&redirect_uri=".concat(urlBackend).concat("/api/v1/google/callback&")
                        .concat("scope=" + String.join("%20",
                                Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
                                        "https://www.googleapis.com/auth/userinfo.email",
                                        "openid"))
                                .concat("&access_type=offline"))));

        return new RedirectView(authorizationUrl.toString());
    }

    @Override
    public GoogleClientSecrets createGoogleClientSecrets(String clientId, String clientSecret) {
        GoogleClientSecrets.Details details = new GoogleClientSecrets.Details();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
        clientSecrets.setWeb(details);
        return clientSecrets;
    }

    @Override
    public RedirectView googleCallBack(String code, List<String> scope) throws IOException, GeneralSecurityException {


        try {
            GoogleClientSecrets clientSecrets = createGoogleClientSecrets(clientId, clientSecret);
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, scope).setAccessType("offline").build();

            TokenResponse tokenResponse = flow.newTokenRequest(code)
                    .setRedirectUri(urlBackend.concat("api/v1/google/callback"))
                    .execute();

            System.out.println(tokenResponse.getAccessToken());
            System.out.println(tokenResponse.getRefreshToken());
        } catch (Exception e) {
            return null;
        }
        return new RedirectView("www.google.com");

    }

}
