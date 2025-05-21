package com.DairyWise.backend.Admin.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
@Builder
public class AdminModel {

  @Id
  private Long id;
  private String name;
  private String email;
  private String phoneNumber;
  private String imageUrl;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
