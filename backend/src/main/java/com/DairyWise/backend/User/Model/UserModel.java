package com.DairyWise.backend.User.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "userId", nullable = false, unique = true)
  @NotBlank
  @Size(min = 5 , message = "UserId should be atleast 5 words")
  private String userId;

  @Column(name = "password", nullable = false)
  @NotBlank
  @Size(min = 5 , message = "Password should be atleast 5 words")
  private String password;

  @Column(name = "email", nullable = false)
  @NotBlank
  @Size(min = 5 , message = "Email should be atleast 5 words")
  private String email;

  @Column(name = "phone", nullable = false)
  @NotBlank
  @Size(min = 10 , message = "Phone Number should be atleast 10 words")
  private String phone;

  @Column(name = "nonLocked", nullable = false)
  private boolean isAccountNonLocked;

  @Column(name = "Enabled", nullable = false)
  private boolean isEnabled;

  @Column(name = "role", nullable = false)
  private UserRoles role;


  @CreationTimestamp
  @Column(name = "createdAt", nullable = false)
  private LocalDateTime createdAt;
}
