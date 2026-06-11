package com.firstclub.membership.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserMetricsRequest {

    @NotBlank(message = "User id is required")
    private String userId;

    @Min(value = 0, message = "Order count cannot be negative")
    private int orderCount;

    @Min(value = 0, message = "Order value cannot be negative")
    private BigDecimal monthlySpend;

    // Optional
    private String cohort;
}