package com.webrise.assignment.controller.impl;

import com.webrise.assignment.controller.UserController;
import com.webrise.assignment.domain.dto.UserDTO;
import com.webrise.assignment.domain.dto.request.UserCreationDTO;
import com.webrise.assignment.domain.dto.request.UserUpdateDTO;
import com.webrise.assignment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<Void> createUser(UserCreationDTO userDTO) {
        userService.create(userDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDTO> getUser(String id) {
        UserDTO user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> updateUser(String id, UserUpdateDTO userUpdateDTO) {
        userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
