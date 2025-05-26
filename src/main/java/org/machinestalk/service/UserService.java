package org.machinestalk.service;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.domain.User;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {

  /**
   * Register a new user.
   *
   * @param userRegistrationDto dto for user registration.
   * @return user entity.
   */
  User registerUser(@NotNull @Valid UserRegistrationDto userRegistrationDto);

  /**
   * Get user by its id.
   *
   * @param id user id.
   * @return user entity.
   */
  Mono<User> getById(long id);
}