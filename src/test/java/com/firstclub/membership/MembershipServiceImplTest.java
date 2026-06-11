package com.firstclub.membership;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.UpdateTierRequest;
import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.enums.TierType;
import com.firstclub.membership.exception.SubscriptionNotFoundException;
import com.firstclub.membership.factory.MembershipPlanFactory;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.model.TierBenefit;
import com.firstclub.membership.repository.MembershipRepository;
import com.firstclub.membership.service.MembershipServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MembershipServiceImplTest {

    private MembershipServiceImpl service;

    @BeforeEach
    void setup() {

        MembershipRepository repository = new MembershipRepository();
        MembershipPlanFactory planFactory = new MembershipPlanFactory();
        Map<TierType, TierBenefit> tierBenefits = Map.of();

        service = new MembershipServiceImpl(repository, planFactory, tierBenefits);
    }

    @Test
    void shouldCreateSubscription() {

        SubscribeRequest request =
                new SubscribeRequest();

        request.setUserId("USER_1");
        request.setPlanType(
                PlanType.YEARLY);
        request.setTierType(
                TierType.GOLD);

        MembershipSubscription result =
                service.subscribe(request);

        assertNotNull(
                result.getSubscriptionId());

        assertEquals(
                SubscriptionStatus.ACTIVE,
                result.getStatus());

        assertEquals(
                "USER_1",
                result.getUserId());
    }

    @Test
    void shouldUpdateTier() {

        SubscribeRequest request =
                new SubscribeRequest();

        request.setUserId("USER_2");
        request.setPlanType(
                PlanType.YEARLY);
        request.setTierType(
                TierType.GOLD);

        service.subscribe(request);

        UpdateTierRequest update =
                new UpdateTierRequest();

        update.setTierType(
                TierType.PLATINUM);

        MembershipSubscription result =
                service.updateTier(
                        "USER_2",
                        update);

        assertEquals(
                TierType.PLATINUM,
                result.getTierType());
    }

    @Test
    void shouldCancelSubscription() {

        SubscribeRequest request =
                new SubscribeRequest();

        request.setUserId("Twinkal");
        request.setPlanType(
                PlanType.MONTHLY);
        request.setTierType(
                TierType.SILVER);

        service.subscribe(request);

        service.cancelSubscription(
                "Twinkal");

        assertThrows(
                SubscriptionNotFoundException.class,
                () -> service.getSubscription("USER_3")
        );
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadySubscribed() {

        SubscribeRequest request =
                new SubscribeRequest();

        request.setUserId("USER_4");
        request.setPlanType(
                PlanType.YEARLY);
        request.setTierType(
                TierType.GOLD);

        service.subscribe(request);

        assertThrows(
                RuntimeException.class,
                () -> service.subscribe(request)
        );
    }
}