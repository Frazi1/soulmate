package com.soulmate.models

import java.time.Clock
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name= "user_message")
class UserMessage: IEntityWithId {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0

    @ManyToOne(/*cascade = [CascadeType.ALL]*/)
    @JoinColumn(name = "source_user_id")
    var sourceUserAccount: UserAccount? = null

    @ManyToOne(/*cascade = [CascadeType.ALL]*/)
    @JoinColumn(name = "destination_user_id")
    var destinationUserAccount: UserAccount? = null

    @Column(name = "content")
    var content: String = ""

    @Column(name = "sentTime")
    var sentTime: LocalDateTime = LocalDateTime.now(Clock.systemUTC())
}