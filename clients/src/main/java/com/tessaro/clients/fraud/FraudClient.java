package com.tessaro.clients.fraud;

import com.tessaro.clients.fraud.type.response.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {

    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerId);
}
