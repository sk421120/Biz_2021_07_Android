package com.callor.threedayday.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val nikname: String,
    val password: String,
    val role: String,
    val email: String,
    val isLogin: Boolean,
    //    로그인 겸 회원가입
    // 로그인 된 정보를 파일이나 데이터베이스에 저장
// 파일에 저장하면 유저 정보로 확인
// 어떻게 저장할건가 (xml파일, 텍스트)
// 로그인 시간으로 로그아웃 구현 가능
)