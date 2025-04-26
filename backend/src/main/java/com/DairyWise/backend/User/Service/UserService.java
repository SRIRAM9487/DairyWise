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

public interface UserService {

  UserRegisterResponseDTO register(UserRegisterRequestDTO registerDTO) throws UserNameAlreadyExistsException,
      UserInvalidNameException, UserInvalidUserIdException, UserInvalidPasswordException, UserInvalidRoleException;

  UserLoginResponseDTO login(UserLoginRequestDTO loginDTO)
      throws UserInvalidUserIdException, UserInvalidPasswordException;

  UserUpdateResponseDTO update(UserUpdateRequestDTO userUpdateDTO) throws UserInvalidUserIdException,
      UserInvalidPasswordException, UserInvalidRoleException, UserNameAlreadyExistsException, UserNotFoundException;

  UserDeleteResponseDTO delete(UserDeleteRequestDTO userDeleteDTO) throws UserNotFoundException;

  UserLockResponseDTO lockUser(UserLockRequestDTO userLockDTO) throws UserNotFoundException;

  UserLockResponseDTO unlockUser(UserLockRequestDTO userLockDTO) throws UserNotFoundException;

  UserEnableResponseDTO enableUser(UserEnableRequestDTO userEnableDTO) throws UserNotFoundException;

  UserEnableResponseDTO disableUser(UserEnableRequestDTO userEnableDTO) throws UserNotFoundException;
}
