package com.soulmate.validation

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ValidationGlobalError {
    var code: String? = null

    constructor(code: String?) {
        this.code = code
    }

    constructor()
}