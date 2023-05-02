package com.example.jpatest.repository

import com.example.jpatest.domain.Order
import com.example.jpatest.repository.custom.CustomOrderRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface OrderRepository : JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>, CustomOrderRepository
