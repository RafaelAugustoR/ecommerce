package com.rafaelaugusto.ecommerce.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    WAITING_PAYMENT("Waiting Payment"),
    PAYED("Payed"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELED("Canceled");

    public final String value;



}
