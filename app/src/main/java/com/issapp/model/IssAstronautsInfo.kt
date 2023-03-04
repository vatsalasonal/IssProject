package com.issapp.model

data class IssAstronautsInfo(
    val message: String?="",
    val number: Int?=0,
    val people: ArrayList<People>?=null
)

data class People(
    val name: String?="",
    val craft: String?=""
)

data class IssPeopleDate(
    val viewType: Int = 1,
    val name: String?="",
    val craft: String?=""
)