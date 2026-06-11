package com.firstclub.membership.controller;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.TierEvaluationResponse;
import com.firstclub.membership.dto.UpdateTierRequest;
import com.firstclub.membership.dto.UserMetricsRequest;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.model.TierBenefit;
import com.firstclub.membership.model.UserMetrics;
import com.firstclub.membership.service.MembershipService;
import com.firstclub.membership.service.TierEvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService service;
    private final TierEvaluationService tierEvaluationService;

    //GET http://localhost:8080/api/plans
    @GetMapping("/plans")
    public List<MembershipPlan> getPlans() {
        log.info("Fetching available plans and tiers");
        return service.getPlans();
    }

    /* POST http://localhost:8080/api/subscriptions
     * Body (JSON):
     * {
     *   "userId": "Twinkal",
     *   "plan": "MONTHLY",
     *   "tierType": "SILVER"
     * }
     */
    @PostMapping("/subscriptions")
    public MembershipSubscription subscribe(
            @Valid
            @RequestBody
            SubscribeRequest request) {
        log.info("Subscribe request for user: {}", request.getUserId());
        return service.subscribe(request);
    }

    /**
     * GET /api/subscriptions/{userId}
     * GET http://localhost:8080/api/subscriptions/userId
     */
    @GetMapping("/subscriptions/{userId}")
    public MembershipSubscription getSubscription(@PathVariable String userId) {
        log.info("Fetching subscription for user: {}", userId);
        return service.getSubscription(userId);
    }

    /**
     * Upgrade/Downgrade to a higher tier (SILVER → GOLD -> SILVER).
     *
     * PUT http://localhost:8080/api/subscriptions/user123/tier
     * Body (JSON):
     * {
     *   "tierType": "GOLD"
     * }
     */
    @PutMapping("/subscriptions/{userId}/tier")
    public MembershipSubscription updateTier(@PathVariable String userId,
            @RequestBody UpdateTierRequest request) {
        log.info("Upgrade tier request for user: {} to {}", userId, request.getTierType());
        return service.updateTier(userId, request);
    }

    /**
     * DELETE http://localhost:8080/api/subscriptions/user123
     */
    @DeleteMapping("/subscriptions/{userId}")
    public String cancelSubscription(@PathVariable String userId) {
        log.info("Cancel subscription request for user: {}", userId);
        service.cancelSubscription(userId);
        return "Subscription cancelled successfully";
    }

    /**
     * Auto-evaluate and update tier based on user's order history.
     * Uses the Strategy Pattern — all TierEvaluationStrategy implementations run.
     *
     * POSTMAN: http://localhost:8080/api/memberships/evaluate-tier
     * Body (JSON):
     *   "userId": "Bhawna",
     *   "orderCount": 0,
     *   "monthlySpend": 500,
     *   "cohort": "REGULAR"
     * }
     */
    @PostMapping("/evaluate-tier")
    public TierEvaluationResponse evaluateTier(@RequestBody UserMetricsRequest request) {

        UserMetrics metrics = new UserMetrics(
                        request.getOrderCount(),
                        request.getMonthlySpend(),
                        request.getCohort()
                );

        boolean eligible = tierEvaluationService.qualifiesForUpgrade(request.getUserId(), metrics);

        return new TierEvaluationResponse(request.getUserId(), eligible,
                eligible ? "Eligible for upgrade" : "Not eligible for upgrade"
        );
    }

    /**
     *
     * @param userId
     * @return
     * POST /api/memberships/subscriptions/{userId}/benefits
     * {
     *   "userId":"USER_1",
     *   "planType":"YEARLY",
     *   "tierType":"GOLD"
     * }
     */
    @GetMapping("/subscriptions/{userId}/benefits")
    public TierBenefit getBenefits(@PathVariable String userId) {
        log.info("Benefits for user {}", userId);
        return service.getBenefits(userId);
    }
}