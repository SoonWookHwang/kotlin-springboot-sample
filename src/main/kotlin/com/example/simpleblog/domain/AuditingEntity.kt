package com.example.simpleblog.domain

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntity(

) : AuditingEntityId(){

    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    lateinit var createAt: LocalDateTime
        protected set  //상속받는 곳에서만 사용이 가능하게 끔 설정

    @LastModifiedDate
    @Column(name = "update_at")
    lateinit var updateAt: LocalDateTime
        protected set
}

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntityId : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set
}