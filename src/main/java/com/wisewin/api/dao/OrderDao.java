package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.OrderItemBO;

import java.util.Map;

public interface OrderDao {
    Integer addOrderDao(OrderItemBO orderBO);

    Integer updOrderDao(OrderItemBO orderBO);

    OrderItemBO getOrderByOrderNumber(String orderNumber);




}
