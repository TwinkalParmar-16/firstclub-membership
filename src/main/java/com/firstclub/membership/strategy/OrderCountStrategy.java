package com.firstclub.membership.strategy;

import com.firstclub.membership.model.UserMetrics;
import org.springframework.stereotype.Component;

@Component
public class OrderCountStrategy
        implements TierEvaluationStrategy {

    @Override
    public boolean isEligible(UserMetrics metrics) {

        return metrics.getOrderCount() >= 10;
    }
}