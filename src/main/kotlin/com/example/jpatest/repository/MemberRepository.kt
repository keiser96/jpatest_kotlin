package com.example.jpatest.repository

import com.example.jpatest.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByName(name: String): List<Member>
}
