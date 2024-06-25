package com.example.godeliveryapp.utils

import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun convertUTCtoIST(utcTime: String): String {

    val utcTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")

    val utcDateTime = ZonedDateTime.parse(utcTime, utcTimeFormatter.withZone(ZoneOffset.UTC))

    val istZoneId = ZoneId.of("Asia/Kolkata")
    val istDateTime = utcDateTime.withZoneSameInstant(istZoneId)

    val isFormatter = DateTimeFormatter.ofPattern("d/M/yyyy, h:mm a")

    return istDateTime.format(isFormatter)

}