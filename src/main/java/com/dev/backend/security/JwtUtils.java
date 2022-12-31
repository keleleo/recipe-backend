package com.dev.backend.security;

import com.dev.backend.models.UserAccountAuth;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private String secret = "uZx4z6B9EbGdKgNjRnTqVtYv2x5A7C9FcHeMhPkRpUrWtZw3y5B8DaGdJfMjQmSpV" +
            "hQmSpVsXuZx4z6B9EbGeKgNjRnTqVtYv2x5A7CaFcHeMhPkRpUrWtZw3y6B8DaGdJ" +
            "y5B8DaFdJfMjQmSpVsXuZx4z6B9EbGeKgNjRnTqVtYv2y5A7CaFcHeMhPkRpUrWuZ" +
            "pUrWtZw3y5B8DaGdJfMjQmSpVsXuZx4z6C9EbGeKgNjRnTqWtYv2y5A7CaFcHeMhP";
    private int ttl = 10000;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtUtils.class);

    public String token(UserAccountAuth userAuth) {

        return "Bearer " + Jwts.builder()
                .setSubject(userAuth.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + ttl))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validate(String token, HttpServletRequest request) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {

        } catch (ExpiredJwtException ex) {
            request.setAttribute("auth-token-validation", "expired token");
        } catch (UnsupportedJwtException ex) {

        }
        return false;
    }
}
