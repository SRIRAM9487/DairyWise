package com.DairyWise.backend.User.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterResponseDTO {

  private String userId;
  private String password;
  private String role;
  private String name;
  private String status;

}
