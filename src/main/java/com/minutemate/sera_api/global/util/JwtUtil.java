package com.minutemate.sera_api.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

public class JwtUtil {
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

    public static String jwt(String secretKey, Long expiredSeconds, Map<String, Object> claims) {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime expiredDate = now.plusSeconds(expiredSeconds);
        return Jwts.builder()
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(expiredDate))
                .setClaims(claims)
                .signWith(ALGORITHM, secretKey)
                .compact();
    }

    public static Claims parse(String secretKey, String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (IllegalArgumentException e) {
            throw new JwtException("JWT 토큰이 비었습니다!", e);
        }
    }
}
