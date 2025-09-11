package app

import ConsoleApp
import ReservationService

fun main() {
    val reservationService = ReservationService()
    val app = ConsoleApp(reservationService)
    app.run()
}
