// package com.spring.social.config;

// import java.nio.charset.StandardCharsets;
// import java.util.Base64;
// import java.util.Date;
// import java.util.function.Function;

// import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Service;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;

// @Service
// public class JwtService {

// private static final String SECRET_KEY =
// "404E635266556A586E3272357538782F413F4428472B4B6250645367566B0795";

// public String extractUsername(String token) {
// return extractClaim(token, Claims::getSubject);
// }

// public String generateToken(UserDetails userDetails) {
// return Jwts.builder()
// .subject(userDetails.getUsername())
// .issuedAt(new Date(System.currentTimeMillis()))
// .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
// .signWith(getSignKey())
// .compact();
// }

// public boolean isTokenValid(String token, UserDetails userDetails) {
// final String username = extractUsername(token);
// return (username.equals(userDetails.getUsername())) &&
// !isTokenExpired(token);
// }

// private boolean isTokenExpired(String token) {
// return extractExpiration(token).before(new Date());
// }

// private Date extractExpiration(String token) {
// return extractClaim(token, Claims::getExpiration);
// }

// public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
// final Claims claims = extractAllClaims(token);
// return claimsResolver.apply(claims);
// }

// private Claims extractAllClaims(String token) {
// return Jwts
// .parser()
// .verifyWith(getSignKey())
// .build()
// .parseSignedClaims(token)
// .getPayload();
// }

// private SecretKey getSignKey() {
// byte[] keyBytes =
// Base64.getDecoder().decode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
// return new SecretKeySpec(keyBytes, "HmacSHA256");
// }
// }
