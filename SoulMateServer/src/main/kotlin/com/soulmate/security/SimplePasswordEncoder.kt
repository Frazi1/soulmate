package com.soulmate.security

import org.springframework.security.crypto.password.PasswordEncoder

class SimplePasswordEncoder : PasswordEncoder {
    override fun encode(seq: CharSequence): String = seq.toString()

    override fun matches(seq: CharSequence, pass: String): Boolean = encode(seq) == pass
}