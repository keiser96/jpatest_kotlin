package com.example.jpatest.domain

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "MEMBER")
class Member(
    @Column(name = "name")
    val name: String,

    @Embedded
    val address: Address
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @OneToMany(mappedBy = "member")
    val orders = mutableListOf<Order>()

    override fun toString(): String {
        return "Member(id=$id, name=$name, address=$address, orders=$orders)"
    }
}
