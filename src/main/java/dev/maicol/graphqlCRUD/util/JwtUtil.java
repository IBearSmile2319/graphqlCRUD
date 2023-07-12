package dev.maicol.graphqlCRUD.util;

import com.google.common.base.Charsets;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class JwtUtil {
    @Value("${jwt.key}")
    private String SECRET_KEY;

    public String generateJwtToken(String username, List<String> roles) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        SecretKey key = getSecretKey(SECRET_KEY);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(8, ChronoUnit.HOURS)))
                .setId(UUID.randomUUID().toString())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    private SecretKey getSecretKey(String signedSecret) {
        return Keys.hmacShaKeyFor(signedSecret.getBytes(Charsets.UTF_8));
    }

    //    validate token
    public Claims validateToken(String token) {
            SecretKey key = getSecretKey(SECRET_KEY);
            return Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token)
                    .getBody();
    }

}
