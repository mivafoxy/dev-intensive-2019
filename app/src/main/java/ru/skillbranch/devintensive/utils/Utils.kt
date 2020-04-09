package ru.skillbranch.devintensive.utils

object Utils
{
    fun parseFullName(fullName: String?): Pair<String?, String?>
    {
        if (fullName.isNullOrEmpty() || fullName.trim().isNullOrEmpty())
            return Pair(null, null)

        val parts = fullName.split(" ")

        if (parts.size == 1)
            return Pair(parts[0], null)

        return Pair(parts[0], parts[1])
    }

    fun toInitials(firstName: String?, lastName: String?): String?
    {
        val hasFirstName =
            !firstName.isNullOrBlank()

        val hasLastName =
            !lastName.isNullOrBlank()

        if (!hasFirstName && !hasLastName)
            return null

        if (hasFirstName && !hasLastName)
            return "${firstName!![0].toUpperCase()}"

        if (!hasFirstName && hasLastName)
            return "${lastName!![0].toUpperCase()}"

        return "${firstName!![0].toUpperCase()}${lastName!![0].toUpperCase()}"
    }

    fun transliteration(fullName: String, divider: String = " "): String
    {
        val transliterationKeys =
            mapOf(
                "а" to "a", "A" to "A",
                "б" to "b", "Б" to "B",
                "в" to "v", "В" to "V",
                "г" to "g", "Г" to "G",
                "д" to "d", "Д" to "D",
                "е" to "e", "Е" to "E",
                "ё" to "e", "Ё" to "E",
                "ж" to "zh", "Ж" to "Zh",
                "з" to "z", "З" to "Z",
                "и" to "i", "И" to "I",
                "й" to "i", "Й" to "I",
                "к" to "k", "К" to "K",
                "л" to "l", "Л" to "L",
                "м" to "m", "М" to "M",
                "н" to "n", "Н" to "N",
                "о" to "o", "О" to "O",
                "п" to "p", "П" to "P",
                "р" to "r", "Р" to "R",
                "с" to "s", "С" to "S",
                "т" to "t", "Т" to "T",
                "у" to "u", "У" to "U",
                "ф" to "f", "Ф" to "F",
                "х" to "h", "Х" to "H",
                "ц" to "c", "Ц" to "C",
                "ч" to "ch", "Ч" to "Ch",
                "ш" to "sh", "Ш" to "Sh",
                "щ" to "sh'", "Щ" to "Sh'",
                "ъ" to "", "Ъ" to "",
                "ы" to "i", "Ы" to "I",
                "ь" to "", "Ь" to "",
                "э" to "e", "Э" to "E",
                "ю" to "yu", "Ю" to "Yu",
                "я" to "ya", "Я" to "Ya"
            )

        var nickname = ""

        fullName.forEach {
            nickname += when {
                it == ' ' -> divider
                transliterationKeys.containsKey(it.toString()) -> transliterationKeys[it.toString()]
                else -> it
            }
        }
        return nickname
    }
}