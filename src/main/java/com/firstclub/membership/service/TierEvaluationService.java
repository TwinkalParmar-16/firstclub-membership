package com.firstclub.membership.service;

import com.firstclub.membership.model.UserMetrics;
import com.firstclub.membership.strategy.TierEvaluationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TierEvaluationService {

    private final List<TierEvaluationStrategy> strategies;

    public boolean qualifiesForUpgrade(String userId, UserMetrics metrics) {
        log.info("Evaluating upgrade eligibility for user {}", userId);
        return strategies.stream().anyMatch(strategy -> strategy.isEligible(metrics));
    }
}