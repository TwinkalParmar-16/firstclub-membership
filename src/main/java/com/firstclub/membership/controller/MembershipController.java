package com.firstclub.membership.controller;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.UpdateTierRequest;
import com.firstclub.membership.dto.UserMetricsRequest;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.model.UserMetrics;
import com.firstclub.membership.service.MembershipService;
import com.firstclub.membership.service.TierEvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService service;
    private final TierEvaluationService tierEvaluationService;

    @GetMapping("/plans")
    public List<MembershipPlan> getPlans() {

        return service.getPlans();
    }

    @PostMapping("/subscriptions")
    public MembershipSubscription subscribe(
            @Valid
            @RequestBody
            SubscribeRequest request) {

        return service.subscribe(request);
    }

    @GetMapping("/subscriptions/{userId}")
    public MembershipSubscription getSubscription(
            @PathVariable String userId) {

        return service.getSubscription(userId);
    }

    @PutMapping("/subscriptions/{userId}/tier")
    public MembershipSubscription updateTier(
            @PathVariable String userId,
            @RequestBody UpdateTierRequest request) {

        return service.updateTier(
                userId,
                request);
    }

    @DeleteMapping("/subscriptions/{userId}")
    public String cancelSubscription(
            @PathVariable String userId) {

        service.cancelSubscription(userId);
        return "Subscription cancelled successfully";
    }

    @PostMapping("/evaluate-tier")
    public boolean evaluateTier(
            @RequestBody
            UserMetricsRequest request) {

        UserMetrics metrics =
                new UserMetrics(
                        request.getOrderCount(),
                        request.getMonthlySpend(),
                        request.getCohort()
                );

        return tierEvaluationService
                .qualifiesForUpgrade(metrics);
    }
}