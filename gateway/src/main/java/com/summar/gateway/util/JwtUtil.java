package com.summar.gateway.util;

import com.summar.gateway.auth.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class JwtUtil {

    @Value("${spring.jwt.secret-access-token}")
    private String SECRET_KEY;

    @Value("${spring.jwt.secret-refresh-token}")
    private String REFRESH_SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractRefreshTokenUsername(String token) {
        return extractRefreshTokenClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Date extractRefreshTokenExpiration(String token) {
        return extractRefreshTokenClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public <T> T extractRefreshTokenClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractRefreshTokenAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Claims extractRefreshTokenAllClaims(String token) {
        return Jwts.parser().setSigningKey(REFRESH_SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Boolean isRefreshTokenExpired(String token) {
        return extractRefreshTokenExpiration(token).before(new Date());
    }

    public String generateToken(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, loginUser.getUserId());
    }

    public String generateRefreshToken(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, loginUser.getUserId());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))  //5분
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private String createRefreshToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  //10시간
                .signWith(SignatureAlgorithm.HS256, REFRESH_SECRET_KEY).compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean validateRefreshToken(String token, UserDetails userDetails) {
        final String username = extractRefreshTokenUsername(token);
        return (username.equals(userDetails.getUsername()) && !isRefreshTokenExpired(token));
    }

}
