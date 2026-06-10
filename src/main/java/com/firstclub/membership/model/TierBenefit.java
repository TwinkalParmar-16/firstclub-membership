package com.firstclub.membership.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TierBenefit {

    private boolean freeDelivery;

    private int discountPercentage;

    private boolean prioritySupport;

    private boolean earlyAccess;
}