package com.example.simpleblog.domain.member

/**
 * dto <=> entity 맵핑 때
 * 1. 각 dto , entity에 책임 할당
 * 2. entity mapper 클래스를 통해 담당하게 하는 것
 */
data class MemberSaveReq(
    val email: String,
    val password: String,
    val role: Role
)
//
fun MemberSaveReq.toEntity() : Member{
    return Member(
        email =this.email,
        password =this.password,
        role = this.role,
    )
}

data class MemberRes(
    val id:Long,
    val email: String,
    val password: String,
    val role: Role
)
