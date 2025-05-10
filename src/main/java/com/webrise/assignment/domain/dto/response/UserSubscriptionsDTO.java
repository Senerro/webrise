package com.webrise.assignment.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserSubscriptionsDTO {
    private final String userId;
    private final List<SubscriptionDTO> subscriptions;
}
