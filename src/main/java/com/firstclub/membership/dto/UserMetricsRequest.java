package com.firstclub.membership.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserMetricsRequest {

    private int orderCount;

    private BigDecimal monthlySpend;

    private String cohort;
}