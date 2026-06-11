package com.firstclub.membership.config;

import com.firstclub.membership.enums.TierType;
import com.firstclub.membership.model.TierBenefit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class BenefitConfig {

    @Bean
    public Map<TierType, TierBenefit> tierBenefits() {
        return Map.of(
                TierType.SILVER, TierBenefit.builder()
                        .tierType(TierType.SILVER)
                        .discountPercentage(5.0)
                        .freeDeliveryThreshold(499.0)
                        .earlyAccessToSales(false)
                        .exclusiveDeals(false)
                        .prioritySupport(false)
                        .description("1% discount, free delivery on orders above ₹499")
                        .build(),

                TierType.GOLD, TierBenefit.builder()
                        .tierType(TierType.GOLD)
                        .discountPercentage(10.0)
                        .freeDeliveryThreshold(0.0)
                        .earlyAccessToSales(true)
                        .exclusiveDeals(true)
                        .prioritySupport(false)
                        .description("2% discount, free delivery on all orders, early access to sales")
                        .build(),

                TierType.PLATINUM, TierBenefit.builder()
                        .tierType(TierType.PLATINUM)
                        .discountPercentage(15.0)
                        .freeDeliveryThreshold(0.0)
                        .earlyAccessToSales(true)
                        .exclusiveDeals(true)
                        .prioritySupport(true)
                        .description("3% discount, free delivery, early access, exclusive deals, priority support")
                        .build()
        );
    }
}

