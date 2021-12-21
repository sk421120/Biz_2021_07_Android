package com.callor.threedayday.data

import com.callor.threedayday.data.model.LoggedInUser
import com.callor.threedayday.data.model.User
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    val userList = arrayListOf<User>(
        User("corgi", "hello", "123456", "0", "sdklfs"),
        User("hello", "hello", "asdfgg", "1", "sdksdfdslfs")
    )

//    val login = mapOf(
//        "username" to "corgi",
//        "password" to "12345",
//        "nickname" to "Hong",
//        "isLogin" to false
//    )

    fun login(username: String, password: String): Result<LoggedInUser> {
//        val findUser = userList.filter { it.userId == username && it.password == password }

        val findUserId = userList.filter { it.userId == username }
        try {
            // TODO: handle loggedInUser authentication
//            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
//            return Result.Success(fakeUser)
            val findUser = findUserId?.filter { it.password == password }
            val user = findUser.get(0)
//            if(user.password == password)
            val loggedInUser =
                LoggedInUser(
                    user?.userId, user?.nikname, user?.password,
                    user?.role, user?.email, true
                )
            return Result.Success(loggedInUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication

    }

    fun join(username: String, password: String): Result<LoggedInUser> {
        val email = String.format("%s@naver.com", username)
        val user = User(username, username, password, "0", email)
//        userService.insert(user,"user")
//        userViewModel?.insert(user, "user")
        val loggedInUser =
            LoggedInUser(user.userId, user.nikname, user.password, user.role, user.email, true)
        return Result.Success(loggedInUser)
    }
}