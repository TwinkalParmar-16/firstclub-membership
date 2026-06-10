package com.firstclub.membership.model;

import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.enums.TierType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MembershipSubscription {

    private String subscriptionId;

    private String userId;

    private PlanType planType;

    private TierType tierType;

    private SubscriptionStatus status;

    private LocalDate startDate;

    private LocalDate expiryDate;
}
