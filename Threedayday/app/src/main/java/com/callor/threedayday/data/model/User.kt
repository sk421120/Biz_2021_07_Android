package com.callor.threedayday.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    val userId: String,
    val nikname: String,
    val password:String,
    val role:String,
    val email:String,
)