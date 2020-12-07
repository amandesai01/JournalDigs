package com.journaldigs.api.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    private String secretKey;
    
    public JwtService() {
        if(System.getenv("SECRET") == null) this.secretKey = "TEMP_SECRET";
        else this.secretKey = System.getenv("SECRET");
    }

    private String extractUserId(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
    }

    public String decode(String token){
        return extractUserId(token);
    }

    public String getTokenForUser(String uid){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, uid);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).signWith(SignatureAlgorithm.HS256, this.secretKey).compact();
    }
}
