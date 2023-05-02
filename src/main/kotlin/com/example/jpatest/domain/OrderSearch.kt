package com.example.jpatest.domain

import com.example.jpatest.domain.OrderSpec.Companion.memberNameLike
import com.example.jpatest.domain.OrderSpec.Companion.orderStatusEq
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.domain.Specification.where

class OrderSearch {
    var memberName: String? = null // 회원 이름
    var orderStatus: OrderStatus? = null // 주문 상태

    fun toSpecification(): Specification<Order> =
        where(memberNameLike(memberName))
            .and(orderStatusEq(orderStatus))
}
