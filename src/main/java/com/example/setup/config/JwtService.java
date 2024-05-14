package com.example.setup.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY = "5259dd47c61ad9cf359e3f2797ae6d60dddf9328ffadbcf7090352d036a45765";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*1000))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        var username = extractUsername(token);
        return Objects.equals(username,userDetails.getUsername()) && isTokenExpired(token);
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsTFunction){
        var claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public SecretKey getSignInKey(){
        byte[] keys = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keys);
    }

}
