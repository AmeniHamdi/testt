package org.machinestalk.service.impl;

import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.domain.Address;
import org.machinestalk.domain.Department;
import org.machinestalk.domain.User;
import org.machinestalk.repository.UserRepository;
import org.machinestalk.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public User registerUser(final UserRegistrationDto dto) {
    User user = new User();
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());

    Department dept = new Department(dto.getDepartment());
    user.setDepartment(dept);

    Set<Address> addresses = new HashSet<>();
    addresses.add(modelMapper.map(dto.getPrincipalAddress(), Address.class));
    if (dto.getSecondaryAddress() != null) {
      addresses.add(modelMapper.map(dto.getSecondaryAddress(), Address.class));
    }
    user.setAddresses(addresses);

    return userRepository.save(user);
  }

  @Override
  public Mono<User> getById(final long id) {
    return Mono.justOrEmpty(userRepository.findById(id));
  }
}
