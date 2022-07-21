package com.revature.razang.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.razang.models.User;

public class JWTTest {
    
    @Test
    public void CreateJWT () {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                .withIssuer("razang")
                .sign(algorithm);
            System.out.println(token);
        } catch (JWTCreationException e){
            //Invalid Signing configuration / Couldn't convert Claims.
            e.printStackTrace();
        }
    }

    @Test
    public void VerifyJWT () {
        User user = new User();
        user.setUsername("ctang");
        user.setEmail("ctang@gmail.com");

        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                .withIssuer("razang")
                .withIssuedAt(new Date())
                .withClaim("username", user.getUsername())
                .withClaim("email", user.getEmail())
                .sign(algorithm);
            System.out.println(token);
        } catch (JWTCreationException e){
            //Invalid Signing configuration / Couldn't convert Claims.
            e.printStackTrace();
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("razang")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("Decoding");
            System.out.println(jwt.getIssuedAt());
            System.out.println(jwt.getIssuer());
            System.out.println(jwt.getClaim("username"));
            System.out.println(jwt.getClaim("email"));
        } catch (JWTVerificationException e){
            e.printStackTrace();
        }
    }

}
