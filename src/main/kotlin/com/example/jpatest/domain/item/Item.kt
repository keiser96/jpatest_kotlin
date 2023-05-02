package com.example.jpatest.domain.item

import com.example.jpatest.domain.Category
import com.example.jpatest.exception.NotEnoughStockException
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
abstract class Item(
    @Column(name = "name")
    val name: String, // 이름

    @Column(name = "price")
    val price: Int, // 가격

    @Column(name = "stock_quantity")
    var stockQuantity: Int // 재고수량
) {
    @Id
    @Column(name = "item_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToMany
    val categories = mutableListOf<Category>()

    fun addStock(orderQuantity: Int) {
        stockQuantity += orderQuantity
    }

    fun removeStock(orderQuantity: Int) {
        stockQuantity -= orderQuantity
        if (stockQuantity < 0) throw NotEnoughStockException("need more stock")
    }

    override fun toString(): String {
        return "Item(name='$name', price=$price, stockQuantity=$stockQuantity, id=$id, categories=$categories)"
    }
}
