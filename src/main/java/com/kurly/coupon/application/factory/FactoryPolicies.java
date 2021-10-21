package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.dto.CouponPolicyRegisterData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FactoryPolicies {
    public final List<PolicyFactory> policyFactories;

    public FactoryPolicies() {
        this.policyFactories = List.of(
                new FlatRatePolicyFactory(),
                new FixedAmountPolicyFactory()
        );
    }

    public CouponPolicy create(CouponPolicyRegisterData dto) {
        return policyFactories.stream()
                .filter(v -> v.matchPolicy(dto.getPolicy()))
                .map(v -> v.createPolicy(dto))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
