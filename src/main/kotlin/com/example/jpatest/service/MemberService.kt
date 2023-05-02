package com.example.jpatest.service

import com.example.jpatest.domain.Member
import com.example.jpatest.repository.MemberRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository
) {
    // 회원가입
    fun join(member: Member): Long? {
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id
    }

    private fun validateDuplicateMember(member: Member) {
        val findMembers = memberRepository.findByName(member.name!!)
        if (findMembers.isNotEmpty()) throw IllegalStateException("이미 존재하는 회원입니다")
    }

    fun findMembers(): List<Member> = memberRepository.findAll()
}