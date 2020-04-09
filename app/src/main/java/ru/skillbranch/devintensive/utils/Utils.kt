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

        return "${firstName!![0].toUpperCase()}${lastName!![0].toUpperCase()}"
    }

    fun transliteration(fullName: String, divider: String = " "): String
    {
        val transliterationKeys =
            mapOf(
                'а' to "a",
                'б' to "b",
                'в' to "v",
                'г' to "g",
                'д' to "d",
                'е' to "e",
                'ё' to "e",
                'ж' to "zh",
                'з' to "z",
                'и' to "i",
                'й' to "i",
                'к' to "k",
                'л' to "l",
                'м' to "m",
                'н' to "n",
                'о' to "o",
                'п' to "p",
                'р' to "r",
                'с' to "s",
                'т' to "t",
                'у' to "u",
                'ф' to "f",
                'х' to "h",
                'ц' to "c",
                'ч' to "ch",
                'ш' to "sh",
                'щ' to "sh",
                'ы' to "i",
                'ь' to "",
                'ъ' to "",
                'э' to "e",
                'ю' to "yu",
                'я' to "ya"
            )

        var (firstName, lastName) = Utils.parseFullName(fullName)

        var transliteratedFirstName = ""

        firstName?.forEach { it ->
            transliteratedFirstName +=
                if (transliterationKeys.containsKey(it.toLowerCase()))
                {
                    transliterationKeys[it.toLowerCase()]
                }
                else
                {
                    it
                }
        }.toString()

        var transliteratedSecondName = ""

        lastName?.forEach {
            it ->
            transliteratedSecondName +=
                if (transliterationKeys.containsKey(it.toLowerCase()))
                {
                    transliterationKeys[it.toLowerCase()]
                }
                else
                {
                    it
                }
        }

        return transliteratedFirstName.capitalize() +
            divider +
            transliteratedSecondName.capitalize()
    }
}