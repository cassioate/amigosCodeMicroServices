package com.tessaro.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
