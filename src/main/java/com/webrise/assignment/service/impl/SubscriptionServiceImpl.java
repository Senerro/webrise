package com.webrise.assignment.service.impl;

import static com.webrise.assignment.constants.ExceptionMessages.SUBSCRIPTION_NOT_FOUND;
import static com.webrise.assignment.constants.ExceptionMessages.USER_NOT_FOUND;

import com.webrise.assignment.domain.dto.request.SubscriptionToUserRequestDTO;
import com.webrise.assignment.domain.dto.response.UserSubscriptionsDTO;
import com.webrise.assignment.domain.exseption.SubscriptionNotFoundException;
import com.webrise.assignment.domain.exseption.UserNotFoundException;
import com.webrise.assignment.domain.mapper.UserMapper;
import com.webrise.assignment.domain.model.Subscription;
import com.webrise.assignment.domain.model.User;
import com.webrise.assignment.repository.SubscriptionRepository;
import com.webrise.assignment.repository.UserRepository;
import com.webrise.assignment.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    public void testGenerate(){

        List<Subscription> list = List.of(
                new Subscription().setName("VNC").setDescription("VNC descr"),
                new Subscription().setName("MTC").setDescription("MTC descr"),
                new Subscription().setName("APPLE muscic").setDescription("APPLE descr")
        );

        subscriptionRepository.saveAll(list);
    }

    @Override
    public void subscribe(SubscriptionToUserRequestDTO requestDTO) {
        User user = userRepository
                .findUserWithSubscriptions(UUID.fromString(requestDTO.userId()))
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, requestDTO.userId())));
        Subscription subscription = subscriptionRepository
                .findById(UUID.fromString(requestDTO.subscriptionId()))
                .orElseThrow(() -> new SubscriptionNotFoundException(String.format(SUBSCRIPTION_NOT_FOUND, requestDTO.subscriptionId())));

        user.getSubscriptions().add(subscription);
        userRepository.save(user);
    }

    @Override
    public void unsubscribe(SubscriptionToUserRequestDTO requestDTO) {
        User user = userRepository
                .findUserWithSubscriptions(UUID.fromString(requestDTO.userId()))
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, requestDTO.userId())));
        Subscription subscription = subscriptionRepository
                .findById(UUID.fromString(requestDTO.subscriptionId()))
                .orElseThrow(() -> new SubscriptionNotFoundException(String.format(SUBSCRIPTION_NOT_FOUND, requestDTO.subscriptionId())));

        user.getSubscriptions().remove(subscription);
        userRepository.save(user);
    }

    @Override
    public UserSubscriptionsDTO getUserSubscription(String userId) {
        User user = userRepository
                .findUserWithSubscriptions(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, userId)));
        return mapper.toUserSubscriptionsDTO(user);
    }
}
