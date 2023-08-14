package com.konkuk.soar.common.config.oauth;

import com.konkuk.soar.common.domain.Token;
import com.konkuk.soar.common.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private final TokenService tokenService;
//  private final ObjectMapper objectMapper;

  public OAuth2SuccessHandler(TokenService tokenService) {
    this.tokenService = tokenService;
//    this.objectMapper = objectMapper;
  }

  // oauth 서버에서 accessToken, refreshToken 생성
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    log.info(oAuth2User.toString());

    Token token = tokenService.generateToken(Math.toIntExact(oAuth2User.getAttribute("id")),
        "USER");

    makeTokenResponse(response, token);
  }

  private void makeTokenResponse(HttpServletResponse response, Token token) throws IOException {
    response.addHeader(HttpHeaders.AUTHORIZATION, token.getAccessToken());
    response.addHeader("Refresh", token.getRefreshToken());

    PrintWriter writer = response.getWriter();
//    writer.println(objectMapper.writeValueAsString(token));
    writer.flush();
  }
}