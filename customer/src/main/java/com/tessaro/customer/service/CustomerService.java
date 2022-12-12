package com.tessaro.customer.service;

import com.tessaro.amqp.producer.RabbitMQMessageProducer;
import com.tessaro.clients.fraud.FraudClient;
import com.tessaro.clients.notification.type.request.NotificationRequest;
import com.tessaro.customer.model.Customer;
import com.tessaro.customer.types.request.CustomerRegistrationRequest;
import com.tessaro.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerService(
        CustomerRepository customerRepository,
        FraudClient fraudClient,
        RabbitMQMessageProducer producer) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        log.info("Customer - Service - make a new register with the follow data: {}.", request);
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        // todo: check if email valid
        // todo: check if email no taken
        // todo: check if fraudster

        log.info("Customer - Service - make the validation of the customer id: {}", customer.getId());
        fraudClient.isFraudster(customer.getId());

        NotificationRequest notificationData = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome...!", customer.getFirstName())
        );
        log.info("Customer - Service - send a notification to the queue with the follow data: {}", notificationData);
        producer.publish(
                notificationData,
                "internal.exchange",
                "internal.notification.routing-key"
        );
        log.info("Customer - Service - message was sent to the queue");
    }
}
