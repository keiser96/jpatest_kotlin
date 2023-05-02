package com.example.jpatest.service

import org.springframework.stereotype.Service

@Service
class MockCreateService(
    private val memberService: MemberService,
    private val itemService: ItemService,

) {
}