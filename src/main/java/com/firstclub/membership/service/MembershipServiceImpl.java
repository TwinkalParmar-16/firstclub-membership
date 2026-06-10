package com.firstclub.membership.service;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.UpdateTierRequest;
import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.exception.BadRequestException;
import com.firstclub.membership.exception.NotFoundException;
import com.firstclub.membership.factory.MembershipPlanFactory;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl
        implements MembershipService {

    private final MembershipRepository repository;

    private final MembershipPlanFactory planFactory;

    @Override
    public List<MembershipPlan> getPlans() {
        return List.of(
                planFactory.create(PlanType.MONTHLY),
                planFactory.create(PlanType.QUARTERLY),
                planFactory.create(PlanType.YEARLY)
        );
    }

    @Override
    public MembershipSubscription subscribe(
            SubscribeRequest request) {

        if(repository.exists(
                request.getUserId())) {

            throw new BadRequestException(
                    "User already subscribed");
        }

        MembershipPlan plan =
                planFactory.create(
                        request.getPlanType());

        MembershipSubscription subscription =
                MembershipSubscription.builder()
                        .subscriptionId(
                                UUID.randomUUID().toString())
                        .userId(
                                request.getUserId())
                        .planType(
                                request.getPlanType())
                        .tierType(
                                request.getTierType())
                        .status(
                                SubscriptionStatus.ACTIVE)
                        .startDate(
                                LocalDate.now())
                        .expiryDate(
                                LocalDate.now()
                                        .plusDays(
                                                plan.getDurationDays()))
                        .build();

        return repository.save(subscription);
    }

    @Override
    public MembershipSubscription getSubscription(String userId) {

        MembershipSubscription subscription = repository.findByUserId(userId);

        if(subscription == null) {

            throw new NotFoundException(
                    "Subscription not found");
        }
        return subscription;

    }

    @Override
    public MembershipSubscription updateTier(String userId, UpdateTierRequest request) {
        MembershipSubscription subscription =
                getSubscription(userId);

        subscription.setTierType(
                request.getTierType());

        repository.save(subscription);

        return subscription;
    }

    @Override
    public void cancelSubscription(String userId) {

        if(!repository.exists(userId)) {

            throw new NotFoundException(
                    "Subscription not found");
        }

        repository.delete(userId);


    }
}
