package com.firstclub.membership.strategy;

import com.firstclub.membership.model.UserMetrics;
import org.springframework.stereotype.Component;

@Component
public class CohortStrategy
        implements TierEvaluationStrategy {

    @Override
    public boolean isEligible(UserMetrics metrics) {

        return "VIP".equalsIgnoreCase(
                metrics.getCohort()
        );
    }
}
