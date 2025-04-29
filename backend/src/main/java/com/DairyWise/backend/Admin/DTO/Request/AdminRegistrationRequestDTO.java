package com.DairyWise.backend.Admin.DTO.Request;

import lombok.Getter;

@Getter
public class AdminRegistrationRequestDTO {

  private Long id;
  private String name;
  private String email;
  private String phoneNumber;
  private String imageUrl;

}
