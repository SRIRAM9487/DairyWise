package com.DairyWise.backend.User.Controller;

import com.DairyWise.backend.User.DTO.Request.UserLoginRequestDTO;
import com.DairyWise.backend.User.DTO.Request.UserRegisterRequestDTO;
import com.DairyWise.backend.User.Exception.UserInvalidNameException;
import com.DairyWise.backend.User.Exception.UserInvalidPasswordException;
import com.DairyWise.backend.User.Exception.UserInvalidRoleException;
import com.DairyWise.backend.User.Exception.UserInvalidUserIdException;
import com.DairyWise.backend.User.Exception.UserNameAlreadyExistsException;
import com.DairyWise.backend.User.Service.UserServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  public String server(){
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

}
