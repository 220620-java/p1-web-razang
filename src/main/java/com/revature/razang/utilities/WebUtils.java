package com.revature.razang.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class WebUtils {

	/**
	 * Generates a random account number.
	 * @return long - account number
	 */
	public static long generateRandomAccountNumber() {
		long number = (long) Math.floor(Math.random() * 9000000000000L)
				+ 1000000000000L;
		return number; 
	}

	    /** Generate an encrypted password with a SHA-512 salt.
     * @param password The password to be converted into SHA-512.
     * @param salt The salt for encrypting.
     * @return String
     */
    public static String generateEncryptedPassword (String password, byte[] salt) {
        String encryptedPassword = "";
        try {
            // Choose SHA-512 for the algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

            // Add salt to the input
            messageDigest.update(salt);

            // Generate the hashed password
            byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Build the string from the bytes
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    
    /** Generates a SHA salt to be used with encrypting passwords.
     * @return byte[]
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        try {
            // Create Random Number Generator with SHA1PRNG algorithm
            SecureRandom randomNumber = SecureRandom.getInstance("SHA1PRNG");

            // Create the byte array to store the number
            randomNumber.nextBytes(salt);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }

    
    /** Creates a JWT
     * @return String
     */
    public static String CreateJWT () {
        try {
            Algorithm algorithm = Algorithm.HMAC256("razang-web-secret");
            String token = JWT.create()
                .withIssuer("razang")
                .sign(algorithm);
            return token;
        } catch (JWTCreationException e){
            //Invalid Signing configuration / Couldn't convert Claims.
            e.printStackTrace();
        }
        return null;
    }

    
    /** Verifies a JWT
     * @param token
     * @return DecodedJWT
     */
    public static DecodedJWT VerifyJWT (String token) {
        Algorithm algorithm = Algorithm.HMAC256("razang-web-secret"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("razang")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
    }

}
