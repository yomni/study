package io.yomni.retryabletest.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(2000),
            retryFor = IllegalStateException.class,
            noRetryFor = {NullPointerException.class, IllegalArgumentException.class}
    )
    public String getRetryable(boolean retryFlag) {
        if (retryFlag) {
            throw new IllegalStateException();
        } else {
            return "retry";
        }
    }

    // 만약 외부에서 getRetryableWrapper --> getRetryable 순으로 호출되었고,
    // getRetryable 내부에서 exception 발생 시 @Retryable 은 동작하지 않는다.
    public String getRetryableWrapper(boolean retryFlag) {
        return getRetryable(retryFlag);
    }
}
