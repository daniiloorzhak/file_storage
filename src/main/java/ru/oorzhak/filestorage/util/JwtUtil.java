package ru.oorzhak.filestorage.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.expireSecs}")
    private static long expireSecs;

    @Value("${jwt.secretKey}")
    private static String secretKey;

    public static String generateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusSeconds(expireSecs).toInstant());

        return JWT.create()
                .withSubject("")
                .withClaim("username", username)
                .withClaim("userId", 1)
                .withIssuedAt(new Date())
                .withIssuer("daniil")
                .withExpiresAt(expirationDate)
                .withSubject(username)
                .withNotBefore(Date.from(ZonedDateTime.now().toInstant()))
                .sign(Algorithm.HMAC256(secretKey));
    }
}
