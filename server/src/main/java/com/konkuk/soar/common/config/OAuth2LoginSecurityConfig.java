package com.konkuk.soar.common.config;

import com.konkuk.soar.common.config.jwt.JwtAuthFilter;
import com.konkuk.soar.common.config.oauth.OAuth2SuccessHandler;
import com.konkuk.soar.common.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2LoginSecurityConfig {

  private final CustomOAuth2UserService oAuth2UserService;
  private final OAuth2SuccessHandler oAuth2SuccessHandler;
  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(CsrfConfigurer::disable) // csrf 설정 해제
        .cors(Customizer.withDefaults()) // cors 설정
        .httpBasic(HttpBasicConfigurer::disable)
        .formLogin(FormLoginConfigurer::disable)
        .addFilterAfter(jwtAuthFilter, LogoutFilter.class)
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
        )
        .oauth2Login(oauth2 -> oauth2.
            userInfoEndpoint(userInfo ->
                userInfo.userService(oAuth2UserService)
            )
            .successHandler(oAuth2SuccessHandler)
        );
    return http.build();
  }

}
