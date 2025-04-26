package com.DairyWise.backend.User.Repository;

import java.util.Optional;

import com.DairyWise.backend.User.Model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

  Optional<UserModel> findByUserId(String username);

  boolean existsByUserId(String userId);

  void deleteByUserId(String userId);

}
