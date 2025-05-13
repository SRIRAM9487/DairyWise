package com.DairyWise.backend.Admin.Service;

import com.DairyWise.backend.Admin.DTO.Request.AdminRegistrationRequestDTO;
import com.DairyWise.backend.Admin.DTO.Response.AdminRegistrationResponseDTO;
import com.DairyWise.backend.Admin.Exception.Admin.AdminInvalidEmailException;
import com.DairyWise.backend.Admin.Exception.Admin.AdminInvalidNameException;
import com.DairyWise.backend.Admin.Exception.Admin.AdminInvalidPhoneNumberException;

public interface AdminService {

  AdminRegistrationResponseDTO register(AdminRegistrationRequestDTO adminRegistrationRequestDTO)
      throws AdminInvalidNameException, AdminInvalidPhoneNumberException, AdminInvalidEmailException;

}
