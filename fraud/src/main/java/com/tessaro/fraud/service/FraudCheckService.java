package com.tessaro.fraud.service;

import com.tessaro.fraud.model.FraudCheckHistory;
import com.tessaro.fraud.repository.FraudCheckHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository){
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId) {
        log.info("Fraud - Service - validate fraud with follow customer id: {}.", customerId);
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                .build());
        Boolean valueResult = false;
        log.info("Fraud - Service - fraud was validated as: {}.", valueResult);
        return valueResult;
    }
}
