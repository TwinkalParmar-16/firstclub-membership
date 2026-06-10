package com.firstclub.membership.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserMetrics {

    private int orderCount;

    private BigDecimal monthlySpend;

    private String cohort;
}