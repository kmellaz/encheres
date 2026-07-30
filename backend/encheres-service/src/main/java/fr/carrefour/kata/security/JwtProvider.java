package fr.carrefour.kata.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;

@Component
public class JwtProvider {

    private final Key key;
    private final long validityMs;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-ms}") long validityMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityMs = validityMs;
    }

    public String generateToken(Authentication auth) {
        String username = auth.getName();
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date now = new Date();
        Date exp = new Date(now.getTime() + validityMs);
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", authorities)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public List<String> getRoles(String token) {
        Object roles = Jwts.parser().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("roles");
        if (roles == null) return List.of();
        return List.of(roles.toString().split(","));
    }
}
