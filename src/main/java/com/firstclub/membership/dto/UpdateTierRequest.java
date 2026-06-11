package com.firstclub.membership.dto;

import com.firstclub.membership.enums.TierType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTierRequest {

    @NotNull(message = "newTier is required (SILVER, GOLD, PLATINUM)")
    private TierType tierType;
}