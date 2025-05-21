package com.DairyWise.backend.User.DTO.Request;

import lombok.Getter;

@Getter
public class UserRegisterRequestDTO {

  private String userId;
  private String password;
  private String role;
  private String email;

  
}
