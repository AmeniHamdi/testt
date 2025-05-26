package org.machinestalk.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.machinestalk.api.dto.AddressDto;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.domain.Address;
import org.machinestalk.domain.Department;
import org.machinestalk.domain.User;
import org.machinestalk.repository.UserRepository;
import org.machinestalk.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserServiceImplTest {

  @Mock private UserRepository userRepository;
  @Mock private ModelMapper modelMapper;

  @InjectMocks private UserServiceImpl userService;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void Should_RegisterNewUser_When_RegisterUser() {
    // Given
    AddressDto addressDto = new AddressDto();
    addressDto.setStreetNumber("20");
    addressDto.setStreetName("Rue de Voltaire");
    addressDto.setPostalCode("75015");
    addressDto.setCity("Paris");
    addressDto.setCountry("France");

    UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
    userRegistrationDto.setFirstName("Jack");
    userRegistrationDto.setLastName("Sparrow");
    userRegistrationDto.setDepartment("RH");
    userRegistrationDto.setPrincipalAddress(addressDto);

    Department department = new Department();
    department.setName("RH");

    Address address = new Address();
    address.setStreetNumber("20");
    address.setStreetName("Rue de Voltaire");
    address.setPostalCode("75015");
    address.setCity("Paris");
    address.setCountry("France");

    User mappedUser = new User();
    mappedUser.setFirstName("Jack");
    mappedUser.setLastName("Sparrow");
    mappedUser.setDepartment(department);
    mappedUser.setAddresses(Collections.singleton(address));

    User savedUser = new User();
    savedUser.setId(1L); // simulate DB assigning ID
    savedUser.setFirstName("Jack");
    savedUser.setLastName("Sparrow");
    savedUser.setDepartment(department);
    savedUser.setAddresses(Collections.singleton(address));

    when(modelMapper.map(userRegistrationDto, User.class)).thenReturn(mappedUser);

    when(userRepository.save(any(User.class))).thenReturn(savedUser);

    User result = userService.registerUser(userRegistrationDto);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertEquals(1L, result.getId());
    assertEquals("Jack", result.getFirstName());
    assertEquals("Sparrow", result.getLastName());
    assertEquals("RH", result.getDepartment().getName());
    assertEquals("Rue de Voltaire", result.getAddresses().iterator().next().getStreetName());

    verify(userRepository, times(1)).save(any(User.class));
  }


  @Test
  void Should_RetrieveUserByTheGivenId_When_GetById() {
    // Given
    final Department department = new Department();
    department.setName("RH");
    final Address address = new Address();
    address.setStreetNumber("20");
    address.setStreetName("Rue Jean Jacques Rousseau");
    address.setPostalCode("75002");
    address.setCity("Paris");
    address.setCountry("France");
    final User user = new User();
    user.setId(12345L);
    user.setFirstName("Dupont");
    user.setLastName("Emilie");
    user.setDepartment(department);
    user.setAddresses(singleton(address));

    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    // When && Then
    StepVerifier.create(userService.getById(user.getId()))
            .assertNext(
                    usr -> {
                      assertNotNull(usr);
                      assertEquals(user, usr);
                    })
            .verifyComplete();
  }
}