package com.DairyWise.backend.Manager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Manager")
public class ManagerModel {

  @Id
  private String id;

  private String name;
  private String phone;
  private String phoneNumber;
  private String location;
  private String email;
  private String urlImage;
}
