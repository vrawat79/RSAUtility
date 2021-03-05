import java.io.File;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

//https://stackoverflow.com/questions/6559272/algid-parse-error-not-a-sequence
// generate private key: openssl genrsa -out private.pem 1024
// generate public key: openssl rsa -in private.pem -pubout -outform PEM -out public_key.pem
// covert private key to pkcs8 format: openssl pkcs8 -topk8 -inform PEM -in private.pem -out private_key.pem -nocrypt

public class KeysLoader {

	public RSAPublicKey readPublicKey(File file) throws Exception {
		KeyFactory factory = KeyFactory.getInstance("RSA");

		try (FileReader keyReader = new FileReader(file); PemReader pemReader = new PemReader(keyReader)) {

			PemObject pemObject = pemReader.readPemObject();
			byte[] content = pemObject.getContent();
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
			return (RSAPublicKey) factory.generatePublic(pubKeySpec);
		}
	}

	public RSAPrivateKey readPrivateKey(File file) throws Exception {
		KeyFactory factory = KeyFactory.getInstance("RSA");

		try (FileReader keyReader = new FileReader(file); PemReader pemReader = new PemReader(keyReader)) {

			PemObject pemObject = pemReader.readPemObject();
			byte[] content = pemObject.getContent();
			PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
			return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
		}
	}

	public static void main(String[] args) throws Exception {
		
				
		File file = new File("/Users/vrawat/.ssh/public_key.pem");
		KeysLoader keysLoader = new KeysLoader();
		RSAPublicKey rsaPubKey = keysLoader.readPublicKey(file);
		System.out.println("pub key: "+ rsaPubKey);
		
		
		File file2 = new File("/Users/vrawat/.ssh/private_key.pem");
		 keysLoader = new KeysLoader();
		RSAPrivateKey rsaPrivKey = keysLoader.readPrivateKey(file2);
		System.out.println("priv key: "+ rsaPrivKey);
		
		//create and sign a token
		RSAPublicKey publicKey = rsaPubKey;
				RSAPrivateKey privateKey = rsaPrivKey;
				try {
				    Algorithm algorithm = Algorithm.RSA256(null, privateKey);
				    String token = JWT.create()
				        .withIssuer("auth0")
				        .withClaim("name", "john doe")
				        .sign(algorithm);
				    System.out.println("Token is: "+ token);
				} catch (JWTCreationException exception){
				    //Invalid Signing configuration / Couldn't convert Claims.
				}
				
				
		
		
	}

}
