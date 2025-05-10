package com.webrise.assignment.controller.impl;

import com.webrise.assignment.controller.SubscriptionController;
import com.webrise.assignment.domain.dto.request.SubscriptionToUserRequestDTO;
import com.webrise.assignment.domain.dto.response.UserSubscriptionsDTO;
import com.webrise.assignment.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscriptionControllerImpl implements SubscriptionController {
    private final SubscriptionService service;

    @PostMapping("/generate")
    public ResponseEntity<Void> testCreateEntity() {
        service.testGenerate();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> subscribeUser(SubscriptionToUserRequestDTO requestDTO) {
        service.subscribe(requestDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserSubscriptionsDTO> getUserSubscription(String userId) {
        UserSubscriptionsDTO subscriptions = service.getUserSubscription(userId);
        return ResponseEntity.ok(subscriptions);
    }

    @Override
    public ResponseEntity<Void> unsubscribeUser(SubscriptionToUserRequestDTO requestDTO) {
        service.unsubscribe(requestDTO);
        return ResponseEntity.ok().build();
    }
}
