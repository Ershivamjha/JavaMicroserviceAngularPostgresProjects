package com.acme.orders.dto; import jakarta.validation.constraints.*; public record CreateOrderRequest(@Email @NotBlank String customerEmail,@NotBlank String sku,@Min(1) int quantity){}
