package domain.model

import java.time.LocalDateTime


data class PlaneReservation(
    val id: Int,
    val arrivalDateTime: LocalDateTime,
    val departureDateTime: LocalDateTime,
    val arrivalCity: String,
    val seatOnPlane: String,
    val user: User
)