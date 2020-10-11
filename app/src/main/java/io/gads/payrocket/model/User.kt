package io.gads.payrocket.model

data class User(
    var uid: String,
    var firstName: String,
    var lastName: String,
    var phoneNum: String,
    var email: String,
    var password: String,
    var confirmPassword: String,
)