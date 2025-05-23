package com.DairyWise.backend.User.Config;

import com.DairyWise.backend.User.Model.UserRoles;
import com.DairyWise.backend.User.Service.UserDetailsServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsServiceImp userDetailsServiceImp;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable())
        .httpBasic(Customizer.withDefaults())
        .logout(page -> page.disable())
        .formLogin(page -> page.disable())
        .authorizeHttpRequests(
            request -> request
                .requestMatchers(AppConstant.PUBLIC_URLS).permitAll()
                .requestMatchers(AppConstant.SUDO_URLS).hasRole(UserRoles.SUDO.name())
                .requestMatchers(AppConstant.ADMIN_URLS).hasRole(UserRoles.ADMIN.name())
                .requestMatchers(AppConstant.MANAGER_URLS).hasRole(UserRoles.MANGER.name())
                .anyRequest().authenticated());
    return httpSecurity.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImp);
    daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

}
