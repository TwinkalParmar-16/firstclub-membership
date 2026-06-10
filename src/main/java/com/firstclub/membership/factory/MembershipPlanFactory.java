package com.firstclub.membership.factory;

import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.model.MembershipPlan;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MembershipPlanFactory {

    public MembershipPlan create(PlanType planType) {

        return switch (planType) {

            case MONTHLY ->
                    new MembershipPlan(
                            PlanType.MONTHLY,
                            BigDecimal.valueOf(199),
                            30
                    );

            case QUARTERLY ->
                    new MembershipPlan(
                            PlanType.QUARTERLY,
                            BigDecimal.valueOf(499),
                            90
                    );

            case YEARLY ->
                    new MembershipPlan(
                            PlanType.YEARLY,
                            BigDecimal.valueOf(1499),
                            365
                    );
        };
    }
}