package com.tarun.myapplication.room

data class UserBuilder(
    var id: Int = 0,
    var login: String = "",
    var html_url: String = "",
    var avatar_url:String=""
) {
    fun build(): User {
        return User(
            id = id,
            login = login,
            html_url = html_url,
            avatar_url=avatar_url
        )
    }
}