package domain.model

import java.time.LocalDateTime


data class TrainReservation(
    val id: Int,
    val arrivalCity: String,
    val stopovers: Map<String, Stopover>,  // ключ – название/город остановки
    val departureCity: String,
    val arrivalDateTime: LocalDateTime,
    val departureDateTime: LocalDateTime,
    val seatOnTrain: String,
    val user: User
)