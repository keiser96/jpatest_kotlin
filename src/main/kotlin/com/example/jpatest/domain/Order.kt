package com.example.jpatest.domain

import com.example.jpatest.domain.DeliveryStatus.COMPLETE
import com.example.jpatest.domain.OrderStatus.CANCEL
import com.example.jpatest.domain.OrderStatus.ORDER
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "ORDERS")
class Order(

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "member_id")
    var member: Member? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    var orderItems: List<OrderItem> = mutableListOf()

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery? = null

    @Column(name = "order_date")
    var orderDate: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus? = null

    fun createOrder(member: Member, delivery: Delivery, orderItems: List<OrderItem>) = Order(
    ).also {
        it.member = member
        it.orderItems = orderItems
        it.delivery = delivery
        orderDate = LocalDateTime.now()
        orderStatus = ORDER
    }

    fun cancel() {
        if (delivery?.deliveryStatus == COMPLETE) {
            throw RuntimeException("배송완료된 상품은 취소불가")
        } else {
            this.orderStatus = CANCEL
            orderItems.forEach { it.cancel() }
        }
    }

    fun getTotalPrice() = orderItems.sumOf { it.getTotalPrice() }

    override fun toString(): String {
        return "Order(id=$id, member=$member, orderItems=$orderItems, delivery=$delivery, orderDate=$orderDate, orderStatus=$orderStatus)"
    }
}
