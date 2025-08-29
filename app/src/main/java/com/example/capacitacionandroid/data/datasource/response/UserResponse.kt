package com.example.capacitacionandroid.data.datasource.response

import kotlinx.serialization.Serializable

data class UserResponse(
    val results: List<User>,
    val info: Info
)

data class Info (
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)

@Serializable
data class User (
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: LoginData,
    val dob: DataAge,
    val registered: DataAge,
    val phone: String,
    val cell: String,
    val id: ID,
    val picture: Picture,
    val nat: String
)

@Serializable
data class DataAge (
    val date: String,
    val age: Long
)

@Serializable
data class ID (
    val name: String,
    val value: String? = null
)

@Serializable
data class Location (
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates,
    val timezone: Timezone
)

@Serializable
data class Coordinates (
    val latitude: String,
    val longitude: String
)

@Serializable
data class Street (
    val number: Long,
    val name: String
)

@Serializable
data class Timezone (
    val offset: String,
    val description: String
)

@Serializable
data class LoginData (
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

@Serializable
data class Name (
    val title: String,
    val first: String,
    val last: String
)

@Serializable
data class Picture (
    val large: String,
    val medium: String,
    val thumbnail: String
)
