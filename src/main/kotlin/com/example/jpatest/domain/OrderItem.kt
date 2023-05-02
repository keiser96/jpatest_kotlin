package com.example.jpatest.domain

import com.example.jpatest.domain.item.Item
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "ORDER_ITEM")
class OrderItem(
    @ManyToOne
    @JoinColumn(name = "item_cd")
    var item: Item,

    @Column(name = "order_price")
    var orderPrice: Int,

    @Column(name = "order_count")
    var count: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    val id: Long? = null

    fun createOrderItem(item: Item, price: Int, quantity: Int): OrderItem {
        item.removeStock(quantity)
        return OrderItem(item, orderPrice, count)
    }

    fun cancel() {
        item.addStock(count)
    }

    fun getTotalPrice() = orderPrice * count

    override fun toString(): String {
        return "OrderItem(item=$item, orderPrice=$orderPrice, count=$count, id=$id)"
    }
}
