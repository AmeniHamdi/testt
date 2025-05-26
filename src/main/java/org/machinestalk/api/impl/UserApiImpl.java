package org.machinestalk.api.impl;

import org.machinestalk.api.UserApi;
import org.machinestalk.api.dto.UserDto;
import org.machinestalk.api.dto.UserDto.UserInfos;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.domain.Address;
import org.machinestalk.domain.User;
import org.machinestalk.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserApiImpl implements UserApi {

    private final UserService userService;

    public UserApiImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto register(final UserRegistrationDto userRegistrationDto) {
        User user = userService.registerUser(userRegistrationDto);
        return toUserDto(user);
    }

    @Override
    public Mono<UserDto> findUserById(long id) {
        return userService.getById(id)
                .map(this::toUserDto);
    }

    private UserDto toUserDto(User user) {
        List<String> addresses = user.getAddresses()
                                     .stream()
                                     .map(Address::toString)
                                     .collect(Collectors.toList());

        UserInfos infos = new UserInfos(
                user.getFirstName(),
                user.getLastName(),
                user.getDepartment().getName(),
                addresses
        );

        return new UserDto(String.valueOf(user.getId()), infos);
    }
}
