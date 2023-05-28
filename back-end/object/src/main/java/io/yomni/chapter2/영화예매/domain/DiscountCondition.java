package io.yomni.chapter2.영화예매.domain;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
