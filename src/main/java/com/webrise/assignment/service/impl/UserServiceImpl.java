package com.webrise.assignment.service.impl;

import com.webrise.assignment.domain.dto.UserDTO;
import com.webrise.assignment.domain.dto.request.UserCreationDTO;
import com.webrise.assignment.domain.dto.request.UserUpdateDTO;
import com.webrise.assignment.domain.exseption.EmailAlreadyExistException;
import com.webrise.assignment.domain.exseption.UserNotFoundException;
import com.webrise.assignment.domain.mapper.UserMapper;
import com.webrise.assignment.domain.model.User;
import com.webrise.assignment.repository.UserRepository;
import com.webrise.assignment.service.UserService;
import com.webrise.assignment.service.validator.EntityPersistValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.webrise.assignment.constants.ExceptionMessages.EMAIL_ALREADY_EXIST;
import static com.webrise.assignment.constants.ExceptionMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final EntityPersistValidator validator;

    @Override
    public void create(UserCreationDTO userDTO) {
        validator.checkIsEmailForUserFree(userDTO.email());

        User user = mapper.createUser(userDTO);
        user.setEncryptedPassword(encoder.encode(userDTO.password()));

        userRepository.save(user);
    }

    @Override
    public UserDTO getUser(String id) {
        return userRepository
                .findById(UUID.fromString(id))
                .map(mapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    public void updateUser(String id, UserUpdateDTO userUpdateDTO) {

        User user = userRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, id)));

        validator.updateUserValidator(user, userUpdateDTO);

        user.setEmail(userUpdateDTO.email());
        user.setEncryptedPassword(encoder.encode(userUpdateDTO.password()));

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, id)));

        userRepository.delete(user);
    }
}
