package com.educationsite.StudentDirectory.POJO;

import com.educationsite.StudentDirectory.Security.User.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

  public Integer id;
  
  public String token;


  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  public User user;
}
