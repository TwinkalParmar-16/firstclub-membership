package com.firstclub.membership.repository;

import com.firstclub.membership.model.MembershipSubscription;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MembershipRepository {

    private final Map<String, MembershipSubscription> subscriptions =
            new ConcurrentHashMap<>();

    public MembershipSubscription save(
            MembershipSubscription subscription) {

        subscriptions.put(
                subscription.getUserId(),
                subscription
        );

        return subscription;
    }

    public MembershipSubscription findByUserId(
            String userId) {

        return subscriptions.get(userId);
    }

    public void delete(String userId) {

        subscriptions.remove(userId);
    }

    public boolean exists(String userId) {

        return subscriptions.containsKey(userId);
    }

    public MembershipSubscription saveIfAbsent(
            MembershipSubscription subscription) {

        return subscriptions.putIfAbsent(
                subscription.getUserId(),
                subscription
        );
    }
}