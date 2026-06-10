package com.firstclub.membership.strategy;

import com.firstclub.membership.model.UserMetrics;

public interface TierEvaluationStrategy {

    boolean isEligible(UserMetrics metrics);
}