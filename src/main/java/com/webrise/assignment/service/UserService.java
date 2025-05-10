package com.webrise.assignment.service;

import com.webrise.assignment.domain.dto.UserDTO;
import com.webrise.assignment.domain.dto.request.UserCreationDTO;
import com.webrise.assignment.domain.dto.request.UserUpdateDTO;

public interface UserService {
    void create(UserCreationDTO userDTO);

    UserDTO getUser(String id);

    void updateUser(String id, UserUpdateDTO userUpdateDTO);

    void deleteUser(String id);
}
