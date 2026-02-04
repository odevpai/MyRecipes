package br.com.domain.services.password

import at.favre.lib.crypto.bcrypt.BCrypt

class BCryptPasswordService {

    fun verify(password: CharArray, hash: String): Boolean {
        return BCrypt.verifyer().verify(password, hash).verified
    }

    fun hash(password: CharArray): String {
        return BCrypt.withDefaults().hashToString(12, password)
    }

}