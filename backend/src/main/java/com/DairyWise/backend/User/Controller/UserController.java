package com.DairyWise.backend.User.Controller;

import com.DairyWise.backend.User.DTO.Request.UserDeleteRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserDetailRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserEnableRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserLockRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserLoginRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserRegisterRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserUpdateRequestDTO;
import com.DairyWise.backend.User.Exception.UserInvalidNameException;
import com.DairyWise.backend.User.Exception.UserInvalidPasswordException;
import com.DairyWise.backend.User.Exception.UserInvalidRoleException;
import com.DairyWise.backend.User.Exception.UserInvalidUserIdException;
import com.DairyWise.backend.User.Exception.UserNameAlreadyExistsException;
import com.DairyWise.backend.User.Exception.UserNotFoundException;
import com.DairyWise.backend.User.Service.UserServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserServiceImp userServiceImp;

  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/")
  public String server() {
    return "The User Service is up";
  }

  @PostMapping("/register")
  public ResponseEntity<?> userRegistration(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.register(userRegisterRequestDTO));
    } catch (UserInvalidNameException | UserNameAlreadyExistsException | UserInvalidUserIdException
        | UserInvalidPasswordException | UserInvalidRoleException e) {
      LOG.error("Error while registering new user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while registering new user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> userLogin(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.login(userLoginRequestDTO));
    } catch (UserInvalidUserIdException | UserInvalidPasswordException e) {
      LOG.error("Error while Logging user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while Logging user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }

  }

  @PutMapping("/update")
  public ResponseEntity<?> userUpdate(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.update(userUpdateRequestDTO));
    } catch (UserInvalidUserIdException | UserInvalidPasswordException | UserInvalidRoleException
        | UserNotFoundException e) {
      LOG.error("Error while Updating user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while Updating user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> userDelete(@RequestBody UserDeleteRequestDTO userDeleteRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.delete(userDeleteRequestDTO));
    } catch (UserNotFoundException e) {
      LOG.error("Error while Deleting user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while Deleting user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @PostMapping("/enable")
  public ResponseEntity<?> userEnable(@RequestBody UserEnableRequestDTO userEnableRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.enableUser(userEnableRequestDTO));
    } catch (UserNotFoundException e) {
      LOG.error("Error while enabling user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while Enabling user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @PostMapping("/disable")
  public ResponseEntity<?> userDisable(@RequestBody UserEnableRequestDTO userEnableRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.disableUser(userEnableRequestDTO));
    } catch (UserNotFoundException e) {
      LOG.error("Error while Disabling user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while Disabling user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @PostMapping("/lock")
  public ResponseEntity<?> userLock(@RequestBody UserLockRequestDTO userLockRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.lockUser(userLockRequestDTO));
    } catch (UserNotFoundException e) {
      LOG.error("Error while Locking user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while Locking user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @PostMapping("/unlock")
  public ResponseEntity<?> userUnlock(@RequestBody UserLockRequestDTO userLockRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.unlockUser(userLockRequestDTO));
    } catch (UserNotFoundException e) {
      LOG.error("Error while Unlcoking user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while Unlcoking user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @GetMapping("/get/all")
  public ResponseEntity<?> userGetAll() {
    try {
      return ResponseEntity.ok(userServiceImp.getAllUsers());
    } catch (Exception e) {
      LOG.error("Error while Unlcoking user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @PostMapping("/get/user")
  public ResponseEntity<?> userGetByUserId(@RequestBody UserDetailRequestDTO userDetailRequestDTO) {
    try {
      return ResponseEntity.ok(userServiceImp.getUserByUserId(userDetailRequestDTO));
    } catch (UserInvalidUserIdException | UserNotFoundException e) {
      LOG.error("Error while fetching user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    } catch (Exception e) {
      LOG.error("Error while fetching user : {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

}
