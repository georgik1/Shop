package com.georgik1.orderservice.service;

import com.georgik1.orderservice.model.dto.OrderRequestDto;

public interface OrderService {

     void placeOrder(OrderRequestDto orderRequestDto);

}
