package com.DairyWise.backend.User.Config;

import com.DairyWise.backend.User.DTO.Jwt.JwtRequestDTO;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

  public String generateToken(JwtRequestDTO requestDTO) {
    return "JWT TOKEN";
  }

}
