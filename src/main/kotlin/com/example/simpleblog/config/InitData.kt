package com.example.simpleblog.config

import com.example.simpleblog.domain.member.*
import com.example.simpleblog.domain.post.Post
import com.example.simpleblog.domain.post.PostRepository
import com.example.simpleblog.domain.post.PostSaveReq
import com.example.simpleblog.domain.post.toEntity
import io.github.serpro69.kfaker.faker
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class InitData(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository

) {

    val faker = faker { }
    private val log = KotlinLogging.logger {}

    @EventListener(ApplicationReadyEvent::class)
    private fun init() {
        generateMember(100)
        generatePost(100)
    }

    private fun generateMember(cnt: Int) {
        val members = mutableListOf<Member>()
        for (i in 1..cnt) {
            val member = memberSaveReq()
            log.info { "insert $member" }
            members.add(member)
        }
        memberRepository.saveAll(members)
    }

    private fun generatePost(cnt: Int) {
        val posts = mutableListOf<Post>()
        for (i in 1..cnt) {
            val post = postSaveReq()
            log.info { "insert $post" }
            posts.add(post)
        }
        postRepository.saveAll(posts)
    }


    private fun memberSaveReq(): Member = MemberSaveReq(
        email = faker.internet.safeEmail(),
        password = "1234",
        role = Role.USER
    ).toEntity()

    private fun postSaveReq(): Post = PostSaveReq(
        title = faker.theExpanse.ships(),
        content = faker.quote.matz(),
        memberId = 1
    ).toEntity()
}