package com.example.jpatest.domain

import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Join
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class OrderSpec {
    companion object {
        fun memberNameLike(memberName: String?): Specification<Order> {
            return object : Specification<Order> {
                override fun toPredicate(root: Root<Order>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
                    if (memberName.isNullOrEmpty()) return null
                    val m: Join<Order, Member> = root.join("member", JoinType.INNER) // 회원과 조인
                    return criteriaBuilder.like(m.get("name"), "%$memberName%")
                }
            }
        }

        fun orderStatusEq(orderStatus: OrderStatus?): Specification<Order> {
            return object : Specification<Order> {
                override fun toPredicate(
                    root: Root<Order>,
                    query: CriteriaQuery<*>,
                    criteriaBuilder: CriteriaBuilder
                ): Predicate? {
                    if (orderStatus == null) return null
                    return criteriaBuilder.equal(root.get<String>("orderStatus"), orderStatus)
                }
            }
        }
    }
//    fun memberNameLike(memberName: String?): Specification<Order> {
//        return object : Specification<Order> {
//            override fun toPredicate(root: Root<Order>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
//                if (memberName.isNullOrEmpty()) return null
//                val m: Join<Order, Member> = root.join("member", JoinType.INNER) //회원과 조인
//                return criteriaBuilder.like(m.get("name"), "%$memberName%")
//            }
//        }
//    }
//
//    fun orderStatusEq(orderStatus: OrderStatus?): Specification<Order> {
//        return object : Specification<Order> {
//            override fun toPredicate(
//                root: Root<Order>,
//                query: CriteriaQuery<*>,
//                criteriaBuilder: CriteriaBuilder
//            ): Predicate? {
//                if (orderStatus == null) return null
//                return criteriaBuilder.equal(root.get<String>("orderStatus"), orderStatus)
//            }
//        }
//    }
}
