import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class ValidationService {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    // Проверка и парсинг даты с исправлением возможных ошибок
    fun parseDateTime(input: String): LocalDateTime? {
        val cleanedInput = input.replace('Т', 'T') // кириллицу Т меняем на латинскую T
        return try {
            LocalDateTime.parse(cleanedInput, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    // Проверка, что строка не пуста
    fun validateNonEmpty(input: String): Boolean {
        return input.trim().isNotEmpty()
    }

    // Пример валидации даты заезда и выезда
    fun validateCheckInEviction(checkIn: LocalDateTime, eviction: LocalDateTime): Boolean {
        return !eviction.isBefore(checkIn)
    }
}
