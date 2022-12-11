package com.tessaro.fraud.controller;

import com.tessaro.clients.fraud.type.response.FraudCheckResponse;
import com.tessaro.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

    public FraudController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        log.info("Fraud - FraudController - validate fraud with follow customer id: {}.", customerId);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("Fraud - FraudController - fraud was validated as: {}.", isFraudulentCustomer);
        return new FraudCheckResponse(isFraudulentCustomer);
    };

}
