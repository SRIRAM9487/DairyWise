package com.DairyWise.backend.User.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "userId", nullable = false, unique = true)
  private String userId;

  @Column(name = "password", nullable = false)
  private String password;

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
