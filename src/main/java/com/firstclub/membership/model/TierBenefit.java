package com.firstclub.membership.model;

import com.firstclub.membership.enums.TierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Data
public class TierBenefit {

    private final TierType tierType;
    private final double discountPercentage;
    private final double freeDeliveryThreshold;
    private final boolean earlyAccessToSales;
    private final boolean exclusiveDeals;
    private final boolean prioritySupport;
    private final String description;
}