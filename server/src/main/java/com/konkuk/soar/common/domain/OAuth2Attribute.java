package com.konkuk.soar.common.domain;

import com.konkuk.soar.member.domain.Member;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class OAuth2Attribute {

  private Map<String, Object> attributes;
  private String attributeKey;
  private String nickname;
  private String email;
  private Long id;


  public static OAuth2Attribute of(String provider,
      Map<String, Object> attributes) {
    switch (provider) {
      case "google":
        return ofGoogle(attributes);
      default:
        throw new RuntimeException();
    }
  }

  public static OAuth2Attribute ofGoogle(Map<String, Object> attributes) {
    return OAuth2Attribute.builder()
        .id((Long) attributes.get("id"))
        .nickname((String) attributes.get("name"))
        .email((String) attributes.get("email"))
        .attributes(attributes)
        .attributeKey("id")
        .build();
  }

  public Member toMember() {
    return new Member(nickname, email);
  }

  public Map<String, Object> toMap(long id) {
    Map<String, Object> map = new HashMap<>();
    map.put("id", id);
    map.put("key", attributeKey);
    map.put("nickname", nickname);

    return map;
  }

}
