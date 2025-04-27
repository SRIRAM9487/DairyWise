package com.DairyWise.backend.User.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailResponseDTO {

  private String userId;
  private String role;
  private boolean isAccountNonLocked;
  private boolean isEnabled;

}
