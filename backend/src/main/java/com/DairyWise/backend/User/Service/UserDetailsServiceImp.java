package com.DairyWise.backend.User.Service;

import com.DairyWise.backend.User.Model.UserDetailsImp;
import com.DairyWise.backend.User.Model.UserModel;
import com.DairyWise.backend.User.Repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImp.class);
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    LOG.debug("Attempting to fetch user with {}", userId);
    UserModel user = userRepository.findByUserId(userId)
        .orElseThrow(() -> {
          LOG.error("User with {} not found", userId);
          return new UsernameNotFoundException("User not found");
        });
    LOG.info("User with {} has been fetched", userId);
    return new UserDetailsImp(user);
  }

}
