package com.DairyWise.backend.User.DTO.Request;

import lombok.Getter;

@Getter
public class UserUpdateRequestDTO {

  private String userId;
  private String password;
  private String role;

}
