package com.primefuel.fulltank.platform.fulfillment.interfaces.rest.resources;

public record DriverResource(Long id, Long providerId, String firstName, String lastName,
                             String licenseNumber, String phoneNumber, String email, String status) {
}
