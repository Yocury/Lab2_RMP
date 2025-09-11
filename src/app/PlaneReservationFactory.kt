package app

import ReservationFactory
import ReservationService
import ValidationService
import domain.model.PlaneReservation
import domain.model.User
import java.time.LocalDateTime
import java.util.Scanner

class PlaneReservationFactory(
    private val reservationService: ReservationService,
    private val validation: ValidationService,
    private val scanner: Scanner
) : ReservationFactory<PlaneReservation> {

    override fun createReservation(): PlaneReservation {
        val id = reservationService.getNextPlaneId()

        var departure: LocalDateTime? = null
        while (departure == null) {
            println("Введите дату и время вылета (ГГГГ-ММ-ДДTчч:мм):")
            val input = scanner.nextLine()
            departure = validation.parseDateTime(input)
            if (departure == null) println("Некорректный формат даты, попробуйте снова.")
        }

        var arrival: LocalDateTime? = null
        while (arrival == null) {
            println("Введите дату и время прилета (ГГГГ-ММ-ДДTчч:мм):")
            val input = scanner.nextLine()
            arrival = validation.parseDateTime(input)
            if (arrival == null) println("Некорректный формат даты, попробуйте снова.")
        }

        println("Введите город прибытия:")
        val arrivalCity = scanner.nextLine()

        println("Введите место в самолете:")
        val seat = scanner.nextLine()

        val user = createDummyUser()
        return PlaneReservation(id, arrival, departure, arrivalCity, seat, user)
    }

    private fun createDummyUser(): User {
        return User(
            id = 1,
            fullName = "Иван Иванов",
            passportData = "1234 567890",
            bornDate = LocalDateTime.of(1990, 1, 1, 0, 0)
        )
    }
}
