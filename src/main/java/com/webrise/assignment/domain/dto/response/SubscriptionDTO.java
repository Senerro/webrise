package com.webrise.assignment.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubscriptionDTO {
    private final String name;
    private final String description;
}
