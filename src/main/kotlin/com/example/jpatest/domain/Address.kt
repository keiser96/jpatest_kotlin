package com.example.jpatest.domain

import javax.persistence.Embeddable

@Embeddable
class Address {
    var city: String? = null
    var street: String? = null
    var zipCode: String? = null

    override fun toString(): String {
        return "Address(city=$city, street=$street, zipCode=$zipCode)"
    }
}
