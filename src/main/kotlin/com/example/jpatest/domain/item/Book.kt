package com.example.jpatest.domain.item

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Book : Item() {
    @Column(name = "author")
    val author: String? = null

    @Column(name = "isbn")
    val isbn: String? = null
}
