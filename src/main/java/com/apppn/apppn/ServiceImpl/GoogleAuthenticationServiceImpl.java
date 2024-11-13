package com.apppn.apppn.ServiceImpl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.apppn.apppn.DTO.Request.UserProfileGoogle;
import com.apppn.apppn.Exceptions.ErrorResponse;
import com.apppn.apppn.Models.User;
import com.apppn.apppn.Service.GoogleAuthenticationService;
import com.apppn.apppn.Service.UserService;
import com.apppn.apppn.Utils.Functions;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
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
    private final UserService userService;
    private final Functions functions;

    public GoogleAuthenticationServiceImpl(UserService userService, Functions functions) {
        this.userService = userService;
        APPLICATION_NAME = "APPPN";
        JSON_FACTORY = JacksonFactory.getDefaultInstance();
        SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
                "https://www.googleapis.com/auth/userinfo.email",
                "openid");
        this.functions = functions;
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
                                .concat("&access_type=offline&prompt=consent"))));

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
    public ResponseEntity<RedirectView> googleCallBack(String code, List<String> scope)
            throws IOException, GeneralSecurityException {

        try {
            GoogleClientSecrets clientSecrets = createGoogleClientSecrets(clientId, clientSecret);
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, scope)
                    .setAccessType("offline").build();

            TokenResponse tokenResponse = flow.newTokenRequest(code)
                    .setRedirectUri(urlBackend.concat("/api/v1/google/callback"))
                    .execute();

           

            System.out.println("Token access Inicial: " + tokenResponse.getAccessToken());
            System.out.println("Token refresh inicial: " + tokenResponse.getRefreshToken());
            System.out.println("Expiration: " + tokenResponse.getExpiresInSeconds());
            

            TokenResponse newTokenResponse = refreshAccessToken(tokenResponse.getRefreshToken());
           
            UserProfileGoogle profileInfo = getUserProfileInfo(newTokenResponse.getAccessToken());

            System.out.println("Email Profile: " + profileInfo.getEmail());

            ResponseEntity<?> user = userService.getUserByEmail(profileInfo.getEmail());
            if (user.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RedirectView("https://www.gov.uk/404"));
            }
            User userFound = (User) user.getBody();
            if (Objects.isNull(userFound)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RedirectView("https://www.gov.uk/404"));
            }

            return ResponseEntity.status(HttpStatus.OK).body(new RedirectView("https://rentaraiz.duckdns.org/"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new RedirectView("https://www.gov.uk/404"));
        }

    }

    private UserProfileGoogle getUserProfileInfo(String accessToken) throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();

        GenericUrl url = new GenericUrl("https://www.googleapis.com/oauth2/v3/userinfo");
        url.put("access_token", accessToken);

        HttpResponse response = requestFactory.buildGetRequest(url).execute();
        return response.parseAs(UserProfileGoogle.class);
    }

    @Override
    public ResponseEntity<?> verifyTokenAndGetUserProfile(String accessToken)
            throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY)
                .setAudience(Collections.singletonList(clientId))
                .build();

        GoogleIdToken googleIdToken = verifier.verify(accessToken);
        if (googleIdToken != null) {

            GoogleIdToken.Payload payload = googleIdToken.getPayload();

            UserProfileGoogle userProfile = new UserProfileGoogle();

            userProfile.setEmail(payload.getEmail());
            userProfile.setName((String) payload.get("name"));
            userProfile.setPicture((String) payload.get("picture"));

            return ResponseEntity.status(HttpStatus.OK).body(userProfile);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Invalid token"));
        }
    }

    private TokenResponse refreshAccessToken(String refreshToken) throws IOException, GeneralSecurityException {
        TokenResponse tokenResponse = new GoogleRefreshTokenRequest(
                new NetHttpTransport(),
                new JacksonFactory(),
                refreshToken,
                clientId,
                clientSecret)
                .execute();

        return tokenResponse;
    }
}
