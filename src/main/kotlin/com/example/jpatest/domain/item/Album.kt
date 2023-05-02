package com.example.jpatest.domain.item

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Album : Item() {
    @Column(name = "artist")
    val artist: String? = null

    @Column(name = "etc")
    val etc: String? = null

    override fun toString(): String {
        return "Album(artist=$artist, etc=$etc)"
    }
}
