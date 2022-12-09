package com.tessaro.customer.service;

import com.tessaro.customer.config.CustomerConfig;
import com.tessaro.customer.model.Customer;
import com.tessaro.customer.types.request.CustomerRegistrationRequest;
import com.tessaro.customer.repository.CustomerRepository;
import com.tessaro.customer.types.request.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email no taken
        // todo: check if fraudster
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        // todo: send notification

    }
}
