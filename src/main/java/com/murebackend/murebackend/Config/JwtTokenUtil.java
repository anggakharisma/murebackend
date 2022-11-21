package com.murebackend.murebackend.Config;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.SecretKey;

import com.murebackend.murebackend.User.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenUtil implements Serializable {
  private static final long serialVersionUID = -2550185165626007488L;

  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  @Value("${jwtSecret}")
  private String secret;

  public String generateAccessToken(User user) {
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    return Jwts.builder()
        .setSubject("whatever")
        .setIssuer("murebackend")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .signWith(key)
        .compact();
  }

}
