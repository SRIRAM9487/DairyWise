package com.DairyWise.backend.Config;

import com.DairyWise.backend.User.Service.UserDetailsServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsServiceImp userDetailsServiceImp;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    String[] permittedUrls = { "/admin/login", "/admin/register", "/Manager/login", "/admin/", "/manager/",
        "/customer/", "/dailyEntry/" };
    httpSecurity
        .cors(Customizer.withDefaults())
        .csrf(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults())
        .logout(page -> page.disable())
        .formLogin(page -> page.disable())
        .authorizeHttpRequests(
            request -> request
                .requestMatchers(permittedUrls).permitAll()
                .anyRequest().authenticated());
    return httpSecurity.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImp);
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

}
