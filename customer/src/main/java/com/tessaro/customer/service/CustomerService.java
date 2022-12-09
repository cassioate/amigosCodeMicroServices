package com.tessaro.customer.service;

import com.tessaro.clients.fraud.FraudClient;
import com.tessaro.customer.model.Customer;
import com.tessaro.customer.types.request.CustomerRegistrationRequest;
import com.tessaro.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient) {
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
        fraudClient.isFraudster(customer.getId());
        // todo: send notification

    }
}
