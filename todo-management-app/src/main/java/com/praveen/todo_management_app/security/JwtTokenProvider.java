package com.praveen.todo_management_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpirationDate;

    //Generate JWT Token
    public String generateToken(Authentication authentication){
        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder().
                setSubject(userName).
                setIssuedAt(new Date()).
                setExpiration(expireDate).
                signWith(key()).
                compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    //Get username from jwttoken
    public String getUsername(String token){
          Claims claims =Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody();

                  String username = claims.getSubject();
                 return username;
    }

    //Validate JWT Token
    public boolean validateToken(String token){
        Jwts.parser().setSigningKey(key()).build().parse(token);

        return true;
    }
}
