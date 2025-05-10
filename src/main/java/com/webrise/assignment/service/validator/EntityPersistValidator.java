package com.webrise.assignment.service.validator;

import com.webrise.assignment.domain.dto.request.UserUpdateDTO;
import com.webrise.assignment.domain.exseption.EmailAlreadyExistException;
import com.webrise.assignment.domain.exseption.SamePasswordException;
import com.webrise.assignment.domain.exseption.SubscriptionNotFoundException;
import com.webrise.assignment.domain.exseption.UserNotFoundException;
import com.webrise.assignment.domain.model.User;
import com.webrise.assignment.repository.SubscriptionRepository;
import com.webrise.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.webrise.assignment.constants.ExceptionMessages.*;

@Component
@RequiredArgsConstructor
public class EntityPersistValidator {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PasswordEncoder encoder;

    public void checkIsEmailForUserFree(String email, UUID id) {
        if (userRepository.existsByEmailAndIdNot(email, id))
            throw new EmailAlreadyExistException(String.format(EMAIL_ALREADY_EXIST, email));
    }

    public void checkIsSubscribeAvailableForUser(String subscribeId, String userId) {
        validateSubscriptionExist(UUID.fromString(subscribeId));
        validateUserExist(UUID.fromString(userId));
    }

    public void updateUserValidator(User user, UserUpdateDTO userUpdateDTO) {
        if (!user.getEmail().equals(userUpdateDTO.email()))
            checkIsEmailForUserFree(userUpdateDTO.email(), user.getId());
        checkPasswordMatches(userUpdateDTO.password(), user.getEncryptedPassword());
    }

    public void checkIsEmailForUserFree(String email) {
        if (userRepository.existsByEmail(email))
            throw new EmailAlreadyExistException(String.format(EMAIL_ALREADY_EXIST, email));
    }

    private void checkPasswordMatches(String stringPass, String hashPass) {
        if (encoder.matches(stringPass, hashPass))
            throw new SamePasswordException(SAME_PASSWORD);
    }

    private void validateUserExist(UUID id) {
        if (userRepository.existsById(id))
            throw new UserNotFoundException(String.format(USER_NOT_FOUND, id));

    }

    private void validateSubscriptionExist(UUID id) {
        if (subscriptionRepository.existsById(id))
            throw new SubscriptionNotFoundException(String.format(SUBSCRIPTION_NOT_FOUND, id));
    }
}
