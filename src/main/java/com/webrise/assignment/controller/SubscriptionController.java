package com.webrise.assignment.controller;

import com.webrise.assignment.domain.dto.request.SubscriptionToUserRequestDTO;
import com.webrise.assignment.domain.dto.response.UserSubscriptionsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/webrise/subscriptions")
public interface SubscriptionController {
    @PostMapping("/generate")
    ResponseEntity<Void> testCreateEntity();
    @PostMapping("/subscribe")
    ResponseEntity<Void> subscribeUser(@RequestBody SubscriptionToUserRequestDTO requestDTO);

    @GetMapping("/users/{userId}")
    ResponseEntity<UserSubscriptionsDTO> getUserSubscription(@PathVariable String userId);

    @DeleteMapping("/unsubscribe")
    ResponseEntity<Void> unsubscribeUser(@RequestBody SubscriptionToUserRequestDTO requestDTO);
}
