package com.firstclub.membership.service;

import com.firstclub.membership.model.UserMetrics;
import com.firstclub.membership.strategy.TierEvaluationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TierEvaluationService {

    private final List<TierEvaluationStrategy> strategies;

    public boolean qualifiesForUpgrade(
            UserMetrics metrics) {

        return strategies.stream()
                .anyMatch(strategy ->
                        strategy.isEligible(metrics));
    }
}