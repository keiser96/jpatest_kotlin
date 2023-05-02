package com.example.jpatest.repository.custom

import com.example.jpatest.domain.Order
import com.example.jpatest.domain.OrderSearch
import com.example.jpatest.domain.QMember
import com.example.jpatest.domain.QOrder
import com.querydsl.jpa.JPQLQuery
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport


class OrderRepositoryImpl: QuerydslRepositorySupport(Order::class.java), CustomOrderRepository {

    override fun search(orderSearch: OrderSearch): List<Order> {
        val order = QOrder.order
        val member = QMember.member
        val query: JPQLQuery<*> = from(order)
        if (!orderSearch.memberName.isNullOrEmpty()) {
            query.leftJoin(order.member, member)
                .where(member.name.contains(orderSearch.memberName))
        }
        if (orderSearch.orderStatus != null) {
            query.where(order.orderStatus.eq(orderSearch.orderStatus))
        }
//        return query.list(order)
        return query.select(order).fetch()
    }
}