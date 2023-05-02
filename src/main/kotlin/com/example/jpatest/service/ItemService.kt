package com.example.jpatest.service

import com.example.jpatest.domain.item.Item
import com.example.jpatest.repository.ItemRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ItemService(
    private val itemRepository: ItemRepository
) {
    fun saveItem(item: Item) = itemRepository.save(item)

    fun findItems() = itemRepository.findAll()

    fun findOne(itemId: Long) = itemRepository.findById(itemId)
}