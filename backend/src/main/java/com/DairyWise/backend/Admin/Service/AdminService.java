package com.DairyWise.backend.Admin.Service;

import java.util.List;

import com.DairyWise.backend.Admin.DTO.Request.AdminDetailsRequestDTO;
import com.DairyWise.backend.Admin.DTO.Request.ManagerDetailsRequestDTO;
import com.DairyWise.backend.Admin.DTO.Request.ManagerEnableRequestDTO;
import com.DairyWise.backend.Admin.DTO.Request.ManagerLockRequestDTO;
import com.DairyWise.backend.Admin.DTO.Request.ManagerRegistrationRequestDTO;
import com.DairyWise.backend.Admin.DTO.Response.AdminDetailsResponseDTO;
import com.DairyWise.backend.Admin.DTO.Response.ManagerDetailsResponseDTO;
import com.DairyWise.backend.Admin.DTO.Response.ManagerEnableResponseDTO;
import com.DairyWise.backend.Admin.DTO.Response.ManagerLockResponseDTO;
import com.DairyWise.backend.Admin.DTO.Response.ManagerRegistrationResponseDTO;

public interface AdminService {


  /*
  AdminRegistrationResponseDTO register(AdminRegistrationRequestDTO adminRegistrationRequestDTO)
      throws AdminInvalidNameException, AdminInvalidPhoneNumberException, AdminInvalidEmailException;
  */
  
  ManagerRegistrationResponseDTO managerRegistration(ManagerRegistrationRequestDTO managerRegistrationRequestDTO);

  ManagerEnableResponseDTO managerEnable(ManagerEnableRequestDTO managerRegistrationRequestDTO);

  ManagerLockResponseDTO managerLock(ManagerLockRequestDTO managerLockRequestDTO);

  List<ManagerDetailsResponseDTO> managerSearch(ManagerDetailsRequestDTO managerLockRequestDTO);

  AdminDetailsResponseDTO profile(AdminDetailsRequestDTO adminDetailsRequestDTO);
}
