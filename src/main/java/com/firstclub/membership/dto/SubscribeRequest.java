package com.firstclub.membership.dto;

import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.TierType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscribeRequest {

    @NotBlank(
            message = "User id is required"
    )
    private String userId;

    @NotNull(
            message = "Plan type is required"
    )
    private PlanType planType;

    @NotNull(
            message = "Tier type is required"
    )
    private TierType tierType;
}
