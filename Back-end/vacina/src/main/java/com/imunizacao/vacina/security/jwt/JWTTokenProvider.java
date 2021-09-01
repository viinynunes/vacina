package com.imunizacao.vacina.security.jwt;

import com.imunizacao.vacina.exception.InvalidJWTToken;
import com.imunizacao.vacina.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JWTTokenProvider {

    private String secretKey = "MYSECRETKEY";
    private long validityInMilliseconds = 3600000; //1H

    @Autowired
    private UserService userService;

    @PostConstruct

    public void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        var now = new Date();
        var expiration = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request){
        var bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        return null;
    }

    public boolean validateToken(String token){
        try {
            var claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())){
                return false;
            }
            return true;
        } catch (InvalidJWTToken e){
            throw new InvalidJWTToken("Invalid or Expired Token");
        }
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userService.loadUserByUsername(getUsernameFromToken(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
