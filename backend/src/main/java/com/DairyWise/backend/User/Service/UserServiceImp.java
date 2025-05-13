package com.DairyWise.backend.User.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.DairyWise.backend.User.DTO.Request.UserDeleteRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserDetailRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserEnableRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserLockRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserLoginRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserRegisterRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserUpdateRequestDTO;
import com.DairyWise.backend.User.DTO.Response.UserDeleteResponseDTO;
import com.DairyWise.backend.User.DTO.Response.UserDetailResponseDTO;
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

  // -------------------------- DEPENDENCIES --------------------------

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private BCryptPasswordEncoder encoder;

  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImp.class);

  // -------------------------- IMPLEMENTATIONS --------------------------

  @Override
  public UserRegisterResponseDTO register(UserRegisterRequestDTO registerDTO)
      throws UserInvalidNameException, UserNameAlreadyExistsException,
      UserInvalidUserIdException, UserInvalidPasswordException, UserInvalidRoleException {

    LOG.debug("Registering new user");

    if (userRepository.existsByUserId(registerDTO.getUserId())) {
      LOG.error("User ID '{}' already exists", registerDTO.getUserId());
      throw new UserNameAlreadyExistsException("User ID already exists");
    }

    if (registerDTO.getUserId().length() < 5) {
      LOG.error("Invalid User ID length");
      throw new UserInvalidUserIdException("User ID must be at least 5 characters");
    }

    if (registerDTO.getPassword().length() < 5) {
      LOG.error("Password too short");
      throw new UserInvalidPasswordException("Password must be at least 5 characters");
    }

    if (!isValidRole(registerDTO.getRole())) {
      LOG.error("Invalid role '{}'", registerDTO.getRole());
      throw new UserInvalidRoleException("Invalid role");
    }

    UserModel user = UserModel.builder()
        .userId(registerDTO.getUserId())
        .password(encoder.encode(registerDTO.getPassword()))
        .role(UserRoles.valueOf(registerDTO.getRole()))
        .isAccountNonLocked(true)
        .isEnabled(true)
        .build();

    userRepository.save(user);
    LOG.info("User ID '{}' registered successfully", registerDTO.getUserId());

    return UserRegisterResponseDTO.builder()
        .userId(registerDTO.getUserId())
        .role(registerDTO.getRole())
        .status("Registration successful")
        .build();
  }

  @Override
  public UserLoginResponseDTO login(UserLoginRequestDTO loginDTO)
      throws UserInvalidUserIdException, UserInvalidPasswordException {

    LOG.debug("User login attempt for UserId: {}", loginDTO.getUserId());

    if (loginDTO.getUserId().length() < 5) {
      LOG.error("Invalid User ID length");
      throw new UserInvalidUserIdException("User ID must be at least 5 characters");
    }

    if (loginDTO.getPassword().length() < 5) {
      LOG.error("Password too short");
      throw new UserInvalidPasswordException("Password must be at least 5 characters");
    }

    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserId(), loginDTO.getPassword()));

    String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYWRtaW4iLCJ1c2VyTmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNjUyMTQ4NjA3fQ.ejYsN4HnVffz4xwV6rGmy-0dW6K73W-kdTdxR1iAdG8";

    LOG.info("User ID '{}' logged in successfully", loginDTO.getUserId());
    return UserLoginResponseDTO.builder()
        .token(jwtToken)
        .status("Login successful")
        .build();
  }

  @Transactional
  @Override
  public UserUpdateResponseDTO update(UserUpdateRequestDTO userUpdateDTO)
      throws UserInvalidUserIdException, UserInvalidPasswordException, UserInvalidRoleException, UserNotFoundException {

    LOG.debug("Updating user with UserId: {}", userUpdateDTO.getUserId());

    if (userUpdateDTO.getUserId().length() < 5) {
      LOG.error("Invalid User ID length");
      throw new UserInvalidUserIdException("User ID must be at least 5 characters");
    }

    if (userUpdateDTO.getPassword().length() < 5) {
      LOG.error("Password too short");
      throw new UserInvalidPasswordException("Password must be at least 5 characters");
    }

    if (!isValidRole(userUpdateDTO.getRole())) {
      LOG.error("Invalid role '{}'", userUpdateDTO.getRole());
      throw new UserInvalidRoleException("Invalid role");
    }

    UserModel user = userRepository.findByUserId(userUpdateDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User not found for UserId: {}", userUpdateDTO.getUserId());
      return new UserNotFoundException("User not found");
    });

    user.setUserId(userUpdateDTO.getUserId());
    user.setRole(UserRoles.valueOf(userUpdateDTO.getRole().toUpperCase()));
    user.setPassword(encoder.encode(userUpdateDTO.getPassword()));

    userRepository.save(user);
    LOG.info("User ID '{}' updated successfully", userUpdateDTO.getUserId());

    return UserUpdateResponseDTO.builder().build();
  }

  @Transactional
  @Override
  public UserDeleteResponseDTO delete(UserDeleteRequestDTO userDeleteDTO) throws UserNotFoundException {

    LOG.debug("Deleting user with UserId: {}", userDeleteDTO.getUserId());

    if (!userRepository.existsByUserId(userDeleteDTO.getUserId())) {
      LOG.error("User not found for UserId: {}", userDeleteDTO.getUserId());
      throw new UserNotFoundException("User not found");
    }

    userRepository.deleteByUserId(userDeleteDTO.getUserId());
    LOG.info("User ID '{}' deleted successfully", userDeleteDTO.getUserId());

    return UserDeleteResponseDTO.builder().build();
  }

  @Override
  public UserEnableResponseDTO disableUser(UserEnableRequestDTO userEnableDTO) throws UserNotFoundException {

    LOG.debug("Disabling user with UserId: {}", userEnableDTO.getUserId());

    UserModel user = userRepository.findByUserId(userEnableDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User not found for UserId: {}", userEnableDTO.getUserId());
      return new UserNotFoundException("User not found");
    });

    user.setEnabled(false);
    userRepository.save(user);

    LOG.info("User ID '{}' disabled", userEnableDTO.getUserId());

    return UserEnableResponseDTO.builder().build();
  }

  @Override
  public UserEnableResponseDTO enableUser(UserEnableRequestDTO userEnableDTO) throws UserNotFoundException {

    LOG.debug("Enabling user with UserId: {}", userEnableDTO.getUserId());

    UserModel user = userRepository.findByUserId(userEnableDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User not found for UserId: {}", userEnableDTO.getUserId());
      return new UserNotFoundException("User not found");
    });

    user.setEnabled(true);
    userRepository.save(user);

    LOG.info("User ID '{}' enabled", userEnableDTO.getUserId());

    return UserEnableResponseDTO.builder().build();
  }

  @Override
  public UserLockResponseDTO lockUser(UserLockRequestDTO userLockDTO) throws UserNotFoundException {

    LOG.debug("Locking user with UserId: {}", userLockDTO.getUserId());

    UserModel user = userRepository.findByUserId(userLockDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User not found for UserId: {}", userLockDTO.getUserId());
      return new UserNotFoundException("User not found");
    });

    user.setAccountNonLocked(false);
    userRepository.save(user);

    LOG.info("User ID '{}' locked", userLockDTO.getUserId());

    return UserLockResponseDTO.builder().build();
  }

  @Override
  public UserLockResponseDTO unlockUser(UserLockRequestDTO userLockDTO) throws UserNotFoundException {

    LOG.debug("Unlocking user with UserId: {}", userLockDTO.getUserId());

    UserModel user = userRepository.findByUserId(userLockDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User not found for UserId: {}", userLockDTO.getUserId());
      return new UserNotFoundException("User not found");
    });

    user.setAccountNonLocked(true);
    userRepository.save(user);

    LOG.info("User ID '{}' unlocked", userLockDTO.getUserId());

    return UserLockResponseDTO.builder().build();
  }

  @Override
  public List<UserDetailResponseDTO> getAllUsers() {
    LOG.debug("Fetching all users");

    List<UserModel> users = userRepository.findAll();
    LOG.info("Fetched {} users", users.size());

    return users.stream()
        .map(user -> UserDetailResponseDTO.builder()
            .userId(user.getUserId())
            .role(user.getRole().name())
            .isAccountNonLocked(user.isAccountNonLocked())
            .isEnabled(user.isEnabled())
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public UserDetailResponseDTO getUserByUserId(UserDetailRequestDTO userDetailRequestDTO)
      throws UserInvalidUserIdException, UserNotFoundException {

    LOG.debug("Fetching user detail for UserId: {}", userDetailRequestDTO.getUserId());

    if (userDetailRequestDTO.getUserId().length() < 5) {
      LOG.error("Invalid User ID length");
      throw new UserInvalidUserIdException("User ID must be at least 5 characters");
    }

    UserModel user = userRepository.findByUserId(userDetailRequestDTO.getUserId()).orElseThrow(() -> {
      LOG.error("User not found for UserId: {}", userDetailRequestDTO.getUserId());
      return new UserNotFoundException("User not found");
    });

    LOG.info("Fetched user details for UserId: {}", userDetailRequestDTO.getUserId());

    return UserDetailResponseDTO.builder()
        .userId(user.getUserId())
        .role(user.getRole().name())
        .isEnabled(user.isEnabled())
        .isAccountNonLocked(user.isAccountNonLocked())
        .build();
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
