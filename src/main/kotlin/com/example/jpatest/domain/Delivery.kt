package com.example.jpatest.domain

import com.example.jpatest.domain.DeliveryStatus.READY
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Delivery(address: Address) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    val id: Long? = null

    @OneToOne(mappedBy = "delivery")
    val order: Order? = null

    @Embedded
    var address: Address? = address

    @Enumerated(EnumType.STRING)
    var deliveryStatus: DeliveryStatus? = null

    init {
        this.deliveryStatus = READY
    }

    override fun toString(): String {
        return "Delivery(id=$id, order=$order, address=$address, deliveryStatus=$deliveryStatus)"
    }
}
