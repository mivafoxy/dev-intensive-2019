package ru.skillbranch.devintensive.extensions

import java.util.*
import java.util.regex.Pattern

fun String.truncate(count: Int = 16) : String
{
    var truncated =
        Arrays.copyOfRange(
            this.toCharArray(),
            0,
            count).
        joinToString(
            "").
        trim()

    if (truncated.length < this.trim().length)
        truncated += "..."

    return truncated
}

fun String.stripHtml() : String
{
    val pattern = Pattern.compile("<.+?>")
    val matcher = pattern.matcher(this)

    val whiteSpacesPattern = Pattern.compile("\\s+")
    val spacesMatcher = whiteSpacesPattern.matcher(matcher.replaceAll(""))

    return spacesMatcher.replaceAll(" ")
}