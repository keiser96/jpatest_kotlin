package com.example.jpatest.domain.item

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Movie : Item() {
    @Column(name = "director")
    val director: String? = null

    @Column(name = "actor")
    val actor: String? = null

    override fun toString(): String {
        return "Movie(director=$director, actor=$actor)"
    }
}
