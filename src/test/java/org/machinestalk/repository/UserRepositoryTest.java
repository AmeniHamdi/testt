package org.machinestalk.repository;

import org.machinestalk.domain.Address;
import org.machinestalk.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.util.Optionals;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
  Address address;
  User user;

  @Autowired private UserRepository userRepository;

  @BeforeEach
  void init(){
    // Given
    address = new Address();
    address.setStreetNumber("22");
    address.setStreetName("Rue Voltaire");
    address.setPostalCode("75012");
    address.setCity("Paris");
    address.setCountry("France");

    user = new User();
    user.setId(1L);
    user.setFirstName("Jack");
    user.setLastName("Sparrow");
    user.setAddresses(singleton(address));
  }

  @Test
  void Should_PersistNewUser_When_Save() {
    // when
    StepVerifier.create(Mono.just(userRepository.save(user)))
            .assertNext(result -> {
              // Then
              assertNotNull(result);
              user.setId(result.getId());
              assertEquals(user, result);
            }).verifyComplete();

  }

  @Test
  void Should_GetUserByItsId_When_FindById() {
    // Given
    final Optional<User> savedUser = Optional.of(userRepository.save(user));

    // When
    savedUser.ifPresent(su -> {
      Optional<User> result = userRepository.findById(su.getId());
      // Then
      Optionals.ifPresentOrElse(result, res -> assertEquals(user, res), Assertions::fail);
    });
  }

  @Test
  void Should_returnEmptyOptional_When_FindById_ForUserThatNotExistsByTheGivenId() {
    // When
    StepVerifier.create(Mono.just(userRepository.findById(5678L)))
            .assertNext(usr -> {
              // Then
              assertNotNull(usr);
              assertFalse(usr.isPresent());
            })
            .verifyComplete();
  }
}
