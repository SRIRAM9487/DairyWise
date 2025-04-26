package com.DairyWise.backend.User.Service;

import com.DairyWise.backend.User.DTO.Request.UserDeleteRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserEnableRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserLockRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserLoginRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserRegisterRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserUpdateRequestDTO;
import com.DairyWise.backend.User.DTO.Response.UserDeleteResponseDTO;
import com.DairyWise.backend.User.DTO.Response.UserEnableResponseDTO;
import com.DairyWise.backend.User.DTO.Response.UserLockResponseDTO;
import com.DairyWise.backend.User.DTO.Response.UserLoginResponseDTO;
import com.DairyWise.backend.User.DTO.Response.UserRegisterResponseDTO;
import com.DairyWise.backend.User.DTO.Response.UserUpdateResponseDTO;
import com.DairyWise.backend.User.Exception.UserInvalidNameException;
import com.DairyWise.backend.User.Exception.UserInvalidPasswordException;
import com.DairyWise.backend.User.Exception.UserInvalidRoleException;
import com.DairyWise.backend.User.Exception.UserInvalidUserIdException;
import com.DairyWise.backend.User.Exception.UserNameAlreadyExistsException;
import com.DairyWise.backend.User.Exception.UserNotFoundException;
import com.DairyWise.backend.User.Model.UserModel;
import com.DairyWise.backend.User.Model.UserRoles;
import com.DairyWise.backend.User.Repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImp implements UserService {

  // -------------------------- DEPENDECIES --------------------------

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private BCryptPasswordEncoder encoder;

  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImp.class);

  // -------------------------- IMPLEMETATIONS --------------------------

  @Override
  public UserRegisterResponseDTO register(UserRegisterRequestDTO registerDTO)
      throws UserInvalidNameException, UserNameAlreadyExistsException,
      UserInvalidUserIdException, UserInvalidPasswordException, UserInvalidRoleException {

    LOG.debug("Saving an new User ");

    if (userRepository.existsByUserId(registerDTO.getUserId())) {
      LOG.error("User name already exists");
      throw new UserNameAlreadyExistsException("User Name already exists in the database");
    }

    if (registerDTO.getUserId().length() < 5) {
      LOG.error("Invalid User Id");
      throw new UserInvalidUserIdException("Invalid User id");

    }

    if (registerDTO.getPassword().length() < 5) {
      LOG.error("The password cannot be less than 5");
      throw new UserInvalidPasswordException("Invalid user password");
    }

    if (!isValidRole(registerDTO.getRole())) {
      LOG.error("Invalid User role");
      throw new UserInvalidRoleException("Invalid role");
    }

    UserModel user = UserModel
        .builder()
        .userId(registerDTO.getUserId())
        .password(encoder.encode(registerDTO.getPassword()))
        .role(UserRoles.valueOf(registerDTO.getRole()))
        .isAccountNonLocked(true)
        .isEnabled(true)
        .build();

    userRepository.save(user);
    LOG.info("User Registered ID : {}", registerDTO.getUserId());

    return UserRegisterResponseDTO
        .builder()
        .userId(registerDTO.getUserId())
        .role(registerDTO.getRole())
        .status("Registration Successfull")
        .build();
  }

  @Override
  public UserLoginResponseDTO login(UserLoginRequestDTO loginDTO)
      throws UserInvalidUserIdException, UserInvalidPasswordException {
    LOG.debug("User login ");

    if (loginDTO.getUserId().length() < 5) {
      LOG.error("Invalid User Id");
      throw new UserInvalidUserIdException("Invalid User id");
    }
    if (loginDTO.getPassword().length() < 5) {
      LOG.error("The password cannot be less than 5");
      throw new UserInvalidPasswordException("Invalid user password");
    }
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserId(), loginDTO.getPassword()));

    String jwtToken = "";

    LOG.info("User Logged UserId : {}", loginDTO.getUserId());
    return UserLoginResponseDTO
        .builder()
        .token(jwtToken)
        .status("Login Successfull")
        .build();
  }

  @Transactional
  @Override
  public UserUpdateResponseDTO update(UserUpdateRequestDTO userUpdateDTO)
      throws UserInvalidUserIdException, UserInvalidPasswordException, UserInvalidRoleException, UserNotFoundException {

    LOG.debug("User updation UserId : {}", userUpdateDTO.getUserId());

    if (userUpdateDTO.getUserId().length() < 5) {
      LOG.error("Invalid User Id");
      throw new UserInvalidUserIdException("Invalid User id");
    }

    if (userUpdateDTO.getPassword().length() < 5) {
      LOG.error("The password cannot be less than 5");
      throw new UserInvalidPasswordException("Invalid user password");
    }

    if (isValidRole(userUpdateDTO.getRole())) {
      LOG.error("Invalid User role");
      throw new UserInvalidRoleException("Invalid role");
    }

    UserModel user = userRepository.findByUserId(userUpdateDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User Not found UserId : {}", userUpdateDTO.getUserId());
      return new UserNotFoundException("User not Found");
    });

    user.setUserId(userUpdateDTO.getUserId());
    user.setRole(UserRoles.valueOf(userUpdateDTO.getRole().toUpperCase()));
    user.setPassword(encoder.encode(userUpdateDTO.getPassword()));

    userRepository.save(user);
    LOG.info("User Updated successfully UserId : {}", userUpdateDTO.getUserId());

    return UserUpdateResponseDTO.builder().build();
  }

  @Transactional
  @Override
  public UserDeleteResponseDTO delete(UserDeleteRequestDTO userDeleteDTO) throws UserNotFoundException {

    LOG.debug("User Deletion of UserId : {}", userDeleteDTO.getUserId());

    if (!userRepository.existsByUserId(userDeleteDTO.getUserId())) {
      LOG.error("User not found UserId : {}", userDeleteDTO.getUserId());
      throw new UserNotFoundException("User Name already exists in the database");
    }

    userRepository.deleteByUserId(userDeleteDTO.getUserId());

    LOG.info("User delete successfully UserID : {}", userDeleteDTO.getUserId());

    return UserDeleteResponseDTO.builder().build();
  }

  @Override
  public UserEnableResponseDTO disableUser(UserEnableRequestDTO userEnableDTO) throws UserNotFoundException {

    LOG.debug("User disable UserId : {}", userEnableDTO.getUserId());

    UserModel user = userRepository.findByUserId(userEnableDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User Not found UserId : {}", userEnableDTO.getUserId());
      return new UserNotFoundException("User not Found");
    });

    if (user.isEnabled()) {
      user.setEnabled(false);
    }

    userRepository.save(user);

    LOG.info("User Disabled UserId : {}", userEnableDTO.getUserId());

    return UserEnableResponseDTO.builder().build();
  }

  @Override
  public UserEnableResponseDTO enableUser(UserEnableRequestDTO userEnableDTO) throws UserNotFoundException {

    LOG.debug("User enable UserId : {}", userEnableDTO.getUserId());

    UserModel user = userRepository.findByUserId(userEnableDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User Not found UserId : {}", userEnableDTO.getUserId());
      return new UserNotFoundException("User not Found");
    });

    if (!user.isEnabled()) {
      user.setEnabled(true);
    }

    userRepository.save(user);

    LOG.info("User Enabled UserId : {}", userEnableDTO.getUserId());

    return UserEnableResponseDTO.builder().build();
  }

  @Override
  public UserLockResponseDTO lockUser(UserLockRequestDTO userLockDTO) throws UserNotFoundException {

    LOG.debug("User locking UserId : {}", userLockDTO.getUserId());

    UserModel user = userRepository.findByUserId(userLockDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User Not found UserId : {}", userLockDTO.getUserId());
      return new UserNotFoundException("User not Found");
    });

    if (user.isAccountNonLocked()) {
      user.setAccountNonLocked(false);
    }

    userRepository.save(user);

    LOG.info("User locked UserId : {}", userLockDTO.getUserId());

    return UserLockResponseDTO.builder().build();
  }

  @Override
  public UserLockResponseDTO unlockUser(UserLockRequestDTO userLockDTO) throws UserNotFoundException {

    LOG.debug("User unlocking UserId : {}", userLockDTO.getUserId());

    UserModel user = userRepository.findByUserId(userLockDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User Not found UserId : {}", userLockDTO.getUserId());
      return new UserNotFoundException("User not Found");
    });

    if (!user.isAccountNonLocked()) {
      user.setAccountNonLocked(true);
    }

    userRepository.save(user);

    LOG.info("User unlocked UserId : {}", userLockDTO.getUserId());

    return UserLockResponseDTO.builder().build();
  }

  // =========================== METHODS ==========================

  private boolean isValidRole(String role) {
    for (UserRoles roles : UserRoles.values()) {
      if (roles.name().equalsIgnoreCase(role)) {
        return true;
      }
    }
    return false;
  }

}
