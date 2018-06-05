@file:Suppress("unused")

package com.soulmate.shared.dtos

class UserDialogDto() {
    lateinit var user: UserAccountDto
    var lastMessage: UserMessageDto? = null

    constructor(user: UserAccountDto, lastMessage: UserMessageDto?) : this() {
        this.user = user
        this.lastMessage = lastMessage
    }
}