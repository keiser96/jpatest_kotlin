package com.example.jpatest.domain

import com.example.jpatest.domain.item.Item
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Long? = null

    val name: String? = null

    @ManyToMany
    @JoinTable(
        name = "CATEGORY_ITEM",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items = mutableListOf<Item>()

    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parent: Category? = null

    @OneToMany(mappedBy = "parent")
    val child = mutableListOf<Category>()

    fun addItem(item: Item) {
        items.add(item)
    }

    fun addChildCategory(child: Category) {
        this.child.add(child)
        child.parent = this
    }

    override fun toString(): String {
        return "Category(id=$id, name=$name, items=$items, parent=$parent, child=$child)"
    }
}
