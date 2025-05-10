package com.webrise.assignment.controller;

import com.webrise.assignment.domain.dto.UserDTO;
import com.webrise.assignment.domain.dto.request.UserCreationDTO;
import com.webrise.assignment.domain.dto.request.UserUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/webrise/users")
public interface UserController {
    @PostMapping
    ResponseEntity<Void> createUser(@RequestBody UserCreationDTO userDTO);

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<Void> updateUser(@PathVariable String id,
                                    @RequestBody UserUpdateDTO userUpdateDTO);
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable String id);
}
