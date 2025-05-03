package com.DairyWise.backend.User;

import com.DairyWise.backend.User.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  /*
  @Test
  public void userRepository_findByUserId_returnsUserWithId() {

    // Arrange
    UserModel user = UserModel
        .builder()
        .userId("user1")
        .password(encoder.encode("user1"))
        .isAccountNonLocked(true)
        .isEnabled(true)
        .role(UserRoles.ADMIN)
        .build();

    // Act
    Optional<UserModel> result = userRepository.findByUserId("manager_01");

    // Assert
    assertFalse(result.isEmpty());
    assertEquals("Arun", result.get().getUserId());
  }
  */
}
