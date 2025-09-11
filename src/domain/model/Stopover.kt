package domain.model

import java.time.LocalDateTime

data class Stopover(
    val city: String,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime
)