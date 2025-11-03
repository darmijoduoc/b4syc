package cl.duocuc.darmijo.users.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String SUPER_SECRET_KEY;
    
    private static final long TOKEN_LIFETIME = 24 * 60 * 60 * 1000;
    
    private Key getSigningKey() {
        byte[] keyBytes = SUPER_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public Claims verifyAndGetClaims(String jwtToken) {
        
        return Jwts.parser()
            .verifyWith((SecretKey) getSigningKey())
            .build()
            .parseSignedClaims(jwtToken)
            .getPayload();
    }
    
    public String createWithSubject(String email) {
        String token = Jwts.builder()
            .claims()
            .subject(email)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + TOKEN_LIFETIME))
            .and()
            .signWith(getSigningKey())
            .compact();
        
        return "Bearer " + token;
    }
}
