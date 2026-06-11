package com.firstclub.membership.service;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.UpdateTierRequest;
import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.enums.TierType;
import com.firstclub.membership.exception.BadRequestException;
import com.firstclub.membership.exception.SubscriptionNotFoundException;
import com.firstclub.membership.factory.MembershipPlanFactory;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.model.TierBenefit;
import com.firstclub.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository repository;
    private final MembershipPlanFactory planFactory;
    private final Map<String, ReentrantLock> userLocks = new ConcurrentHashMap<>();
    private final Map<TierType, TierBenefit> tierBenefits;

    @Override
    public List<MembershipPlan> getPlans() {
        return List.of(
                planFactory.create(PlanType.MONTHLY),
                planFactory.create(PlanType.QUARTERLY),
                planFactory.create(PlanType.YEARLY)
        );
    }

    @Override
    public MembershipSubscription subscribe(SubscribeRequest request) {
        ReentrantLock lock = getLockForUser(request.getUserId());
        lock.lock();
        try {
            if(repository.exists(request.getUserId())) {
                throw new BadRequestException(
                        "User already subscribed");
            }
            MembershipPlan plan = planFactory.create(request.getPlanType());

            MembershipSubscription subscription =
                    MembershipSubscription.builder()
                            .subscriptionId(UUID.randomUUID().toString())
                            .userId(request.getUserId())
                            .planType(request.getPlanType())
                            .tierType(request.getTierType())
                            .status(SubscriptionStatus.ACTIVE)
                            .startDate(LocalDate.now())
                            .expiryDate(LocalDate.now().plusDays(plan.getDurationDays()))
                            .build();
            return repository.save(subscription);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public MembershipSubscription getSubscription(String userId) {
        ReentrantLock lock = getLockForUser(userId);
        lock.lock();
        try {
            MembershipSubscription subscription = repository.findByUserId(userId);
            if(subscription == null) {
                throw new SubscriptionNotFoundException("Subscription not found");
            }
            return subscription;

        } finally {
            lock.unlock();
        }
    }

    @Override
    public MembershipSubscription updateTier(String userId, UpdateTierRequest request) {
        ReentrantLock lock = getLockForUser(userId);
        lock.lock();
        try {
            MembershipSubscription subscription = getSubscription(userId);
            subscription.setTierType(request.getTierType());
            repository.save(subscription);
            return subscription;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public TierBenefit getBenefits(String userId) {
        MembershipSubscription subscription = getSubscription(userId);
        return tierBenefits.get(subscription.getTierType());
    }


    @Override
    public void cancelSubscription(String userId) {
        ReentrantLock lock = getLockForUser(userId);
        lock.lock();
        try {
            if(!repository.exists(userId)) {
                throw new SubscriptionNotFoundException("Subscription not found");
            }
            repository.delete(userId);
        } finally {
            lock.unlock();
        }
    }


    private ReentrantLock getLockForUser(String userId) {
        return userLocks.computeIfAbsent(userId, id -> new ReentrantLock());
    }
}
