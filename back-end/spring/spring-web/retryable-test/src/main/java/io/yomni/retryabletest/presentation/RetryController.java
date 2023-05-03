package io.yomni.retryabletest.presentation;

import io.yomni.retryabletest.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RetryController {
    private final RetryService retryService;

    @GetMapping("/retry")
    public ResponseEntity<String> getRetryable(
            @RequestParam(required = false) boolean retryFlag) {
        return ResponseEntity.ok(retryService.getRetryable(retryFlag));
    }
}
