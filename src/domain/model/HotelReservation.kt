package domain.model

import java.time.LocalDateTime


data class HotelReservation(
    val id: Int,
    val checkInDateTime: LocalDateTime,
    val evictionDateTime: LocalDateTime,
    val user: User
)