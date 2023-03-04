package com.iss.model

data class IssInfo(
    val message: String?="",
    val timestamp: Long?=0,
    val iss_position: IssPosition?=null
)

data class IssPosition(
    val latitude: Double?=0.0,
    val longitude: Double?=0.0
)

data class IssLocationTimes(
    val viewType: Int?=2,
    val locationName: String?="",
    val dateTimes: String?=""
)