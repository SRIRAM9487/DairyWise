package com.DairyWise.backend.User.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDTO {

  private String token;
  private String status;

}
