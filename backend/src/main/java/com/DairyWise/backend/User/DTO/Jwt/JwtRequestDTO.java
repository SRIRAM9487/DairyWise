package com.DairyWise.backend.User.DTO.Jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequestDTO {
  private String userName;
  private String password;
  private String role;
  
}
