package com.webrise.assignment.domain.dto.request;

import lombok.Builder;

@Builder
public record SubscriptionToUserRequestDTO (String subscriptionId,
                                            String userId){
}
