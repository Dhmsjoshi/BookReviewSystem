package dev.dharam.bookreviewsystem.jwtServices;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);


    private  String jwtSecret = generateSecretKey();

    private final long jwtExpirationInMs=86400000;



    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[256]; // 256 bits
        random.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }



    public  String generateToken(Authentication authentication) {
        // Get user details from the authentication object
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        // Set token expiration time
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        // Build the JWT token
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // Subject of the token (username)
                .setIssuedAt(new Date()) // When the token was issued
                .setExpiration(expiryDate) // When the token expires
                .signWith(key(), SignatureAlgorithm.HS512) // Sign the token with a secret key using HS512 algorithm
                .compact(); // Compacts the JWT into a URL-safe string
    }

    /**
     * Retrieves the signing key from the secret.
     *
     * @return The signing key.
     */
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token string.
     * @return The username.
     */
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token) // Parses the JWT and validates the signature
                .getBody(); // Gets the claims (payload) of the JWT

        return claims.getSubject(); // Returns the subject (username)
    }

    /**
     * Validates a JWT token.
     *
     * @param authToken The JWT token string.
     * @return True if the token is valid, false otherwise.
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);
            return true; // Token is valid
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false; // Token is invalid
    }

}
