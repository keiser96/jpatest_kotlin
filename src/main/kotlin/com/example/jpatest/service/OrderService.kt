package com.example.jpatest.service

import com.example.jpatest.domain.Delivery
import com.example.jpatest.domain.Order
import com.example.jpatest.domain.OrderItem
import com.example.jpatest.domain.OrderSearch
import com.example.jpatest.repository.MemberRepository
import com.example.jpatest.repository.OrderRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class OrderService(
    private val memberRepository: MemberRepository,
    private val orderRepository: OrderRepository,
    private val itemService: ItemService
) {
    // 주문
    fun order(memberId: Long, itemId: Long, count: Int): Long {
        // entity 조회
        val member = memberRepository.findById(memberId).let {
            if (it.isPresent) it.get()
            else throw IllegalArgumentException("존재하지 않는 회원")
        }
        val item = itemService.findOne(itemId).let {
            if (it.isPresent) it.get()
            else throw IllegalArgumentException("존재하지 않는 물품")
        }

        // 배송정보 생성
        val delivery = Delivery(member.address)
        // 주문상품 생성
        val orderItem = OrderItem(item, item.price, count)
        // 주문생성
        val order = Order().createOrder(member, delivery, listOf(orderItem))
        // 주문저장
        orderRepository.save(order)
        return order.id!!
    }

    // 주문취소
    fun cancelOrder(orderId: Long) {
        val order = orderRepository.findById(orderId)
        order.ifPresent { it.cancel() }
    }

    // 주문검색
    fun findOrders(orderSearch: OrderSearch): List<Order> {
//        return orderRepository.findAll(orderSearch.toSpecification()) // Specification사용
        return orderRepository.search(orderSearch) // QueryDsl사용
    }
}