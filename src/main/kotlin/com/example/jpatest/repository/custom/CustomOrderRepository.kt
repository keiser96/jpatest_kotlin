package com.example.jpatest.repository.custom

import com.example.jpatest.domain.Order
import com.example.jpatest.domain.OrderSearch

interface CustomOrderRepository {
    fun search(orderSearch: OrderSearch): List<Order>
}
