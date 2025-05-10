package com.webrise.assignment.service;

import com.webrise.assignment.domain.dto.request.SubscriptionToUserRequestDTO;
import com.webrise.assignment.domain.dto.response.UserSubscriptionsDTO;

import java.util.List;

public interface SubscriptionService {
    void testGenerate();
    void subscribe(SubscriptionToUserRequestDTO requestDTO);
    void unsubscribe(SubscriptionToUserRequestDTO requestDTO);
    UserSubscriptionsDTO getUserSubscription(String userId);
}
