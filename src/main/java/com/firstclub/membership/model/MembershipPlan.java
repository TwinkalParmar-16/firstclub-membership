package com.firstclub.membership.model;

import com.firstclub.membership.enums.PlanType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MembershipPlan {

    private PlanType type;

    private BigDecimal price;

    private int durationDays;
}
