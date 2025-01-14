package com.apppn.apppn.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class GoogleAuthenticationServiceImpl {

    // @Value("${google.clientId}")
    // private String clientId;

    // @Value("${google.clientIdMovil}")
    // private String clientIdMovil;

    // @Value("${google.client-secret}")
    // private String clientSecret;

    // @Value("${url-back}")
    // private String urlBackend;

    // private final String APPLICATION_NAME;
    // private final JsonFactory JSON_FACTORY;
    // private final List<String> SCOPE;
    // private final UserService userService;
    // private final Functions functions;

    // public GoogleAuthenticationServiceImpl(UserService userService, Functions functions) {
    //     this.userService = userService;
    //     APPLICATION_NAME = "APPPN";
    //     JSON_FACTORY = JacksonFactory.getDefaultInstance();
    //     SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
    //             "https://www.googleapis.com/auth/userinfo.email",
    //             "openid");
    //     this.functions = functions;
    // }

    // @Override
    // public RedirectView getGoogleAuthentication() {
    //     StringBuilder authorizationUrl = new StringBuilder();

    //     authorizationUrl.append("https://accounts.google.com/o/oauth2/auth?response_type=code&client_id="
    //             .concat(clientId)
    //             .concat("&redirect_uri=".concat(urlBackend).concat("/api/v1/google/callback&")
    //                     .concat("scope=" + String.join("%20",
    //                             Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
    //                                     "https://www.googleapis.com/auth/userinfo.email",
    //                                     "openid"))
    //                             .concat("&access_type=offline&prompt=consent"))));

    //     return new RedirectView(authorizationUrl.toString());
    // }

    // @Override
    // public GoogleClientSecrets createGoogleClientSecrets(String clientId, String clientSecret) {
    //     GoogleClientSecrets.Details details = new GoogleClientSecrets.Details();
    //     details.setClientId(clientId);
    //     details.setClientSecret(clientSecret);
    //     GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
    //     clientSecrets.setWeb(details);
    //     return clientSecrets;
    // }

    // @Override
    // public ResponseEntity<?> googleCallBack(String code, List<String> scope)
    //         throws IOException, GeneralSecurityException {

    //     try {
    //         GoogleClientSecrets clientSecrets = createGoogleClientSecrets(clientId, clientSecret);
    //         GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
    //                 GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, scope)
    //                 .setAccessType("offline").build();

    //         TokenResponse tokenResponse = flow.newTokenRequest(code)
    //                 .setRedirectUri(urlBackend.concat("/api/v1/google/callback"))
    //                 .execute();

    //         System.out.println("Token access Inicial: " + tokenResponse.getAccessToken());
    //         System.out.println("Token refresh inicial: " + tokenResponse.getRefreshToken());
    //         System.out.println("Expiration: " + tokenResponse.getExpiresInSeconds());

    //         TokenResponse newTokenResponse = refreshAccessToken(tokenResponse.getRefreshToken());

    //         UserProfileGoogle profileInfo = getUserProfileInfo(newTokenResponse.getAccessToken());

    //         System.out.println("Email Profile: " + profileInfo.getEmail());

    //         ResponseEntity<?> user = userService.getUserByEmail(profileInfo.getEmail());
    //         if (user.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RedirectView("https://www.gov.uk/404"));
    //         }
    //         User userFound = (User) user.getBody();
    //         if (Objects.isNull(userFound)) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RedirectView("https://www.gov.uk/404"));
    //         }

    //         UserDTO userDTO = new UserDTO();
    //         userDTO.setEmail(profileInfo.getEmail());
    //         userDTO.setLastname(profileInfo.getGiven_name());
    //         userDTO.setName(profileInfo.getFamily_name());


    //         ResponseEntity<?> userEditResponse = userService.editUser(userFound.getIdUser(), userDTO);

    //         if (userEditResponse.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
    //             return null;
    //         }

    //         return ResponseEntity.status(HttpStatus.OK).body(new RedirectView("https://rentaraiz.duckdns.org/"));

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         System.out.println(e.getMessage());
    //         return ResponseEntity.status(HttpStatus.OK).body(new RedirectView("https://www.gov.uk/404"));
    //     }

    // }

    // private UserProfileGoogle getUserProfileInfo(String accessToken) throws IOException {
    //     HttpTransport httpTransport = new NetHttpTransport();
    //     HttpRequestFactory requestFactory = httpTransport.createRequestFactory();

    //     GenericUrl url = new GenericUrl("https://www.googleapis.com/oauth2/v3/userinfo");
    //     url.put("access_token", accessToken);

    //     HttpResponse response = requestFactory.buildGetRequest(url).execute();

    //     String responseBody;
    //     try (InputStream content = response.getContent()) {
    //         responseBody = new String(content.readAllBytes(), StandardCharsets.UTF_8);
    //     }

    //     // Imprime el contenido de la respuesta para verificar el JSON
    //     System.out.println("Response Body: " + responseBody);

    //     // Usa Gson para analizar la respuesta JSON en un objeto UserProfileGoogle
    //     Gson gson = new Gson();
    //     try {
    //         UserProfileGoogle profileInfo = gson.fromJson(responseBody, UserProfileGoogle.class);
    //         System.out.println("Parsed User Profile: " + profileInfo.getEmail()); // Verifica un campo
    //         return profileInfo;
    //     } catch (JsonSyntaxException e) {
    //         e.printStackTrace();
    //         System.out.println("Error parsing JSON to UserProfileGoogle: " + e.getMessage());
    //         return null;
    //     }
    // }

    // @Override
    // public ResponseEntity<?> verifyTokenAndGetUserProfile(String accessToken)
    //         throws GeneralSecurityException, IOException {
    //     GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(),
    //             JSON_FACTORY)
    //             .setAudience(Arrays.asList(clientId, clientIdMovil))
    //             .build();

    //     GoogleIdToken googleIdToken = verifier.verify(accessToken);
    //     if (googleIdToken != null) {

    //         GoogleIdToken.Payload payload = googleIdToken.getPayload();

    //         UserProfileGoogle userProfile = new UserProfileGoogle();

    //         userProfile.setEmail(payload.getEmail());
    //         userProfile.setName((String) payload.get("name"));
    //         userProfile.setPicture((String) payload.get("picture"));

    //         return ResponseEntity.status(HttpStatus.OK).body(userProfile);
    //     } else {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Invalid token"));
    //     }
    // }

    // private TokenResponse refreshAccessToken(String refreshToken) throws IOException, GeneralSecurityException {
    //     TokenResponse tokenResponse = new GoogleRefreshTokenRequest(
    //             new NetHttpTransport(),
    //             new JacksonFactory(),
    //             refreshToken,
    //             clientId,
    //             clientSecret)
    //             .execute();

    //     return tokenResponse;
    // }
}
