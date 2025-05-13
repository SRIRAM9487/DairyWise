package com.DairyWise.backend.Admin.Service;


import com.DairyWise.backend.Admin.DTO.Request.AdminRegistrationRequestDTO;
import com.DairyWise.backend.Admin.DTO.Response.AdminRegistrationResponseDTO;
import com.DairyWise.backend.Admin.Exception.AdminInvalidEmailException;
import com.DairyWise.backend.Admin.Exception.AdminInvalidNameException;
import com.DairyWise.backend.Admin.Exception.AdminInvalidPhoneNumberException;
import com.DairyWise.backend.Admin.Model.AdminModel;
import com.DairyWise.backend.Admin.Repository.AdminRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {

  // -------------------------- DEPENDENCIES --------------------------
  @Autowired
  private AdminRepository adminRepository;

  private final Logger LOG = LoggerFactory.getLogger(AdminServiceImp.class);

  // -------------------------- METHODS --------------------------

  @Override
  public AdminRegistrationResponseDTO register(AdminRegistrationRequestDTO adminRegistrationRequestDTO)
      throws AdminInvalidNameException, AdminInvalidPhoneNumberException, AdminInvalidEmailException {
    if (adminRegistrationRequestDTO.getName() == null || adminRegistrationRequestDTO.getName().isBlank()
        || adminRegistrationRequestDTO.getName().length() < 3) {
      LOG.error("Admin registration failed: Invalid name '{}'", adminRegistrationRequestDTO.getName());
      throw new AdminInvalidNameException("Invalid name: must be at least 3 characters long and not blank.");
    }

    if (adminRegistrationRequestDTO.getPhoneNumber().isEmpty() || adminRegistrationRequestDTO.getPhoneNumber() == null
        || !adminRegistrationRequestDTO.getPhoneNumber().matches("^[1-9][0-9]{9}$")) {
      LOG.error("Admin registration failed: Invalid phone number '{}'", adminRegistrationRequestDTO.getPhoneNumber());
      throw new AdminInvalidPhoneNumberException("Invalid phone number: must be 10 digits and not start with 0.");
    }

    if (adminRegistrationRequestDTO.getEmail() == null || adminRegistrationRequestDTO.getEmail().isBlank()
        || !adminRegistrationRequestDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
      LOG.error("Admin registration failed: Invalid email '{}'", adminRegistrationRequestDTO.getEmail());
      throw new AdminInvalidEmailException("Invalid email format.");
    }

    AdminModel admin = AdminModel
        .builder()
        .id(adminRegistrationRequestDTO.getId())
        .name(adminRegistrationRequestDTO.getName())
        .email(adminRegistrationRequestDTO.getEmail())
        .phoneNumber(adminRegistrationRequestDTO.getPhoneNumber())
        .imageUrl(adminRegistrationRequestDTO.getImageUrl())
        .build();

    adminRepository.save(admin);

    LOG.info("Admin registration  for name '{}', phone '{}', email '{}' successfull",
        adminRegistrationRequestDTO.getName(), adminRegistrationRequestDTO.getPhoneNumber(),
        adminRegistrationRequestDTO.getEmail());

    return AdminRegistrationResponseDTO.builder().build();
  }


}
