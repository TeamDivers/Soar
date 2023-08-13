package com.konkuk.soar.common.service;

import com.konkuk.soar.common.domain.Token;
import com.konkuk.soar.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenService implements InitializingBean {

  private static final String BEARER_PREFIX = "Bearer ";
  private final String secret;
  private final long accessTokenValidityInMilliseconds;
  private final long refreshTokenValidityInMilliseconds;
  private final MemberService memberService;
  private Key key;


  public TokenService(@Value("${jwt.key}") String secret,
      @Value("${jwt.expiration.refresh}") long accessTokenValidityInMilliseconds,
      @Value("${jwt.expiration.access}") long refreshTokenValidityInMilliseconds,
      MemberService memberService) {
    this.secret = secret;
    this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds * 1000;
    this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds * 1000;
    this.memberService = memberService;
  }

  @Override
  public void afterPropertiesSet() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public Token generateToken(int memberId, String role) {
    Claims claims = Jwts.claims().setSubject(String.valueOf(memberId));
    claims.put("role", role);

    Instant now = Instant.now();
    return new Token(makeJwtValue(claims, now, accessTokenValidityInMilliseconds),
        makeJwtValue(claims, now, refreshTokenValidityInMilliseconds));
  }

  private String makeJwtValue(Claims claims, Instant now, long accessTokenValidityInMilliseconds) {
    return Jwts.builder().setClaims(claims).setIssuedAt(new Date(now.toEpochMilli()))
        .setExpiration(new Date(now.toEpochMilli() + accessTokenValidityInMilliseconds))
        .signWith(key, SignatureAlgorithm.HS256).compact();
  }

  public boolean validateToken(String token) {
    if (null == token) {
      return false;
    }

    if (false == token.startsWith(BEARER_PREFIX)) {
      return false;
    }

    String value = token.substring(BEARER_PREFIX.length());

    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(value);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      log.error("잘못된 jwt 서명을 가진 토큰입니다", e);
    } catch (ExpiredJwtException e) {
      log.error("만료된 jwt 토큰입니다", e);
    } catch (UnsupportedJwtException e) {
      log.error("지원하지 않는 jwt 토큰입니다", e);
    } catch (IllegalArgumentException e) {
      log.error("잘못된 jwt 토큰입니다", e);
    }
    return false;
  }

  public Authentication getAuthentication(String token) {
    int memberId = getMemberId(token);
    log.info("Authenticated Member Id:" + String.valueOf(memberId));
    return new UsernamePasswordAuthenticationToken(memberId, "",
        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
  }

  public int getMemberId(String token) {
    String value = token.substring(BEARER_PREFIX.length());
    String subject = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(value).getBody()
        .getSubject();
    return Integer.parseInt(subject);
  }

}