package com.lucasprioste.searchrepositorygithub.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale



fun LocalDateTime.toReadableDate(locale: Locale): String{
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale)
    return this.format(formatter)
}