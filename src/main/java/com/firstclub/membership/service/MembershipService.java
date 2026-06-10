package com.firstclub.membership.service;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.UpdateTierRequest;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipSubscription;

import java.util.List;

public interface MembershipService {

    List<MembershipPlan> getPlans();

    MembershipSubscription subscribe(
            SubscribeRequest request);

    MembershipSubscription getSubscription(
            String userId);

    MembershipSubscription updateTier(
            String userId,
            UpdateTierRequest request);

    void cancelSubscription(
            String userId);
}
