package com.example.simpleblog.domain.member

import com.example.simpleblog.domain.AuditingEntity
import javax.persistence.*

@Entity
@Table(name = "Member")
class Member(email: String, password: String, role: Role) : AuditingEntity() {

    @Column
    var email: String = email
        protected set

    @Column
    var password: String = password
        protected set

    @Enumerated(EnumType.STRING)
    var role: Role = role
        protected set

    override fun toString(): String {
        return "Member(email='$email', password='$password', role=$role)"
    }

    /**
     *  JPA와 Kotlin의 궁합이 약간 좋지 않다
     */

}

enum class Role {
    USER, ADMIN
}