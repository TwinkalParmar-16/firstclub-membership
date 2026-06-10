package com.firstclub.membership.strategy;

import com.firstclub.membership.model.UserMetrics;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderValueStrategy
        implements TierEvaluationStrategy {

    @Override
    public boolean isEligible(UserMetrics metrics) {

        return metrics.getMonthlySpend()
                .compareTo(BigDecimal.valueOf(5000))
                >= 0;
    }
}