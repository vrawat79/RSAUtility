

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;


public class GoogleATVerifier {
	
	private static final String CLIENT_ID = "435468805013-qp89pdv6fi35vsi7l258g1eh3iq6h7c3.apps.googleusercontent.com";


	public static NetHttpTransport transport = new NetHttpTransport();


static GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, new JacksonFactory())
    // Specify the CLIENT_ID of the app that accesses the backend:
    .setAudience(Collections.singletonList(CLIENT_ID))
    // Or, if multiple clients access the backend:
    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
    .build();



// (Receive idTokenString by HTTPS POST)
public static void printTokenDetails(String idTokenString) throws GeneralSecurityException, IOException {
	

GoogleIdToken idToken = verifier.verify(idTokenString);
if (idToken != null) {
  Payload payload = idToken.getPayload();

  // Print user identifier
  String userId = payload.getSubject();
  System.out.println("User ID: " + userId);

  // Get profile information from payload
  String email = payload.getEmail();
  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
  String name = (String) payload.get("name");
  System.out.println("name: "  +name);
  String pictureUrl = (String) payload.get("picture");
  System.out.println("pictureUrl: "+ pictureUrl);
  String locale = (String) payload.get("locale");
  System.out.println("locale: " + locale);
  String familyName = (String) payload.get("family_name");
  System.out.println("familyName: " + familyName);
  String givenName = (String) payload.get("given_name");
  System.out.println("givenName: "+ givenName);

  // Use or store profile information
  // ...

} else {
  System.out.println("Invalid ID token.");
}
}

public static void main(String[] args) throws GeneralSecurityException, IOException {
	final String idTokenString = 
//			"ya29.a0AfH6SMAAwjFWCTudKfU5M-jtrX_hkHw3ll1XEvcq9kG1OnH3Fll0FetzVsbYd2unQqeuxdmrhLnusPj90exl7gs6bSGLMO0I_gYRSwsoTvAy0-7ZoWX7eL-dPFV78R7PVM2SPs4eVdyneRcoSSXeQg8M5GaLrvCBpmC6";
//			"eyJhbGciOiJSUzI1NiIsImtpZCI6IjdkYTc4NjNlODYzN2Q2NjliYzJhMTI2MjJjZWRlMmE4ODEzZDExYjEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXpwIjoiNDM1NDY4ODA1MDEzLXFwODlwZHY2ZmkzNXZzaTdsMjU4ZzFlaDNpcTZoN2MzLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiNDM1NDY4ODA1MDEzLXFwODlwZHY2ZmkzNXZzaTdsMjU4ZzFlaDNpcTZoN2MzLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTExNDU0Mzk5OTUxMTE3NDUxMjc1IiwiZW1haWwiOiJyYXdhdC52aWtyYW03OUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IlNCQk1Gc041NVVVWnlLUFVIcTJXcGciLCJuYW1lIjoiVmlrcmFtIFJhd2F0IiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hLS9BT2gxNEdqU3lGU0lnNmo2bVR1cDYzNkVDWFdxT21OQWdSb0xRM29tU0pST1BRPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6IlZpa3JhbSIsImZhbWlseV9uYW1lIjoiUmF3YXQiLCJsb2NhbGUiOiJlbiIsImlhdCI6MTYwMjg5NzUzMywiZXhwIjoxNjAyOTAxMTMzLCJqdGkiOiJmYWYxOTZjYTk3ZWNmZWIxYjZhNDkzZDljNDNjZDFjZTg1MjJkOWJhIn0.CnEp6MdSMVow6n8JZJNoz8kLrwO_viT5yDDaF81N5Y8hW0CZLaXO1XaB_8TNetM62boY6H-ZaVslHLVnIXdikbg8nFT4KcDu09GGU9rnCHMpO-tw_khYUTjwgdTADhRWnZmdCKJnFlnlJqmMf2AWSFciIKAnooOAfAkvCnBA8KPrPwYaMbjCtoLfpgLSBie7qpCItR_xFSZf3F4wzO2u9amiuFZUjJ1W-O8sxz5vaUx7cqW8Ki8mzdijgEitowMgw55w9ydrKVEnUIhk-iU4Z11NT1sKJQYytmm_9DKPEduiG2_LvbE9EGmcoggRGbJ32fPCnl9-nHnuLT4-66iEXA";
			"eyJhbGciOiJSUzI1NiIsImtpZCI6IjdkYTc4NjNlODYzN2Q2NjliYzJhMTI2MjJjZWRlMmE4ODEzZDExYjEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXpwIjoiNDM1NDY4ODA1MDEzLXFwODlwZHY2ZmkzNXZzaTdsMjU4ZzFlaDNpcTZoN2MzLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiNDM1NDY4ODA1MDEzLXFwODlwZHY2ZmkzNXZzaTdsMjU4ZzFlaDNpcTZoN2MzLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTE3NTc0NzczMDM5NTU4MDM0MTY5IiwiZW1haWwiOiJ2aWtyYW0ucmF3YXQ3OTgxQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoiT2c0Z2trTUpxR29IS1M3OXZSQ0dLQSIsIm5hbWUiOiJWaWtyYW0gUmF3YXQiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDYuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1DOTVTQlVhMzRWcy9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BTVp1dWNrZURxdVdEV1lpVGVWQUp5aF9udWJqVTFYam1RL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJWaWtyYW0iLCJmYW1pbHlfbmFtZSI6IlJhd2F0IiwibG9jYWxlIjoiZW4iLCJpYXQiOjE2MDI5NzgzMzgsImV4cCI6MTYwMjk4MTkzOCwianRpIjoiMTY2NDYxN2QwYmFlMzA0ZjM1MzliMDVlZjU2NWExN2Y0MGIyNzM1MiJ9.E-l76Xg_o1stxFOu_6p2-7nBW_Ym1o_VtOn6svQdSXDOtXHKuZ7GwExLnxB-HPm4OlzPmaByLtRvWEKsJCNnGPc-3jgodCHqvznjqrZj-t0k8wHYkSiBW_4PxyH9mg8baOWJJGvooWCAFL8qB4rqGFwjijbr4530P4Qh3KpHq0GU-P8zgZmL7Nvl2SFo4ZPq6e7I8u4S2z5PrypDZUcCBnAzNWe5jD0c4t5s9v9mWxW0QlKXPrkgB2bKzsi14QoRkBLHVYxTaYc7leoNd0-sTZ0xTHvTpDYhieuAsZuVQPwM5Xu7MvJihYxmntmL8ZZloESejBRr98zdzSs40o8_RQ";

	for (int i=0; i<2; i++)
	printTokenDetails(idTokenString);
}

}