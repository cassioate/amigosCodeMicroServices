package com.tessaro.customer.types.request;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
