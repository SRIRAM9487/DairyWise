package com.DairyWise.backend.User.DTO.Request;

import lombok.Getter;

@Getter
public class UserLoginRequestDTO {

  private String userId;
  private String password;

}
