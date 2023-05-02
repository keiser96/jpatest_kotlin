package com.example.jpatest.controller

import com.example.jpatest.service.JpaTestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class JpaTestController(
    private val jpaTestService: JpaTestService
) {
    @GetMapping("/test")
    fun getSvc() = jpaTestService.test()
}
