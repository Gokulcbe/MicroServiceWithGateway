package com.quinbay.reporting.service.OrderServiceInterface;

import com.quinbay.reporting.dto.OrderDTO;

public interface OrderServiceInterface {

    public void handleOrderEvent(OrderDTO message);

}
