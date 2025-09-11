package app

import ReservationFactory
import ReservationService
import ValidationService
import domain.model.HotelReservation
import domain.model.User
import java.time.LocalDateTime
import java.util.Scanner

class HotelReservationFactory(
    private val reservationService: ReservationService,
    private val validation: ValidationService,
    private val scanner: Scanner
) : ReservationFactory<HotelReservation> {

    override fun createReservation(): HotelReservation {
        val id = reservationService.getNextHotelId()

        var checkIn: LocalDateTime? = null
        while (checkIn == null) {
            println("Введите дату заезда (ГГГГ-ММ-ДДTчч:мм):")
            val input = scanner.nextLine()
            checkIn = validation.parseDateTime(input)
            if (checkIn == null) println("Некорректный формат даты, попробуйте снова.")
        }

        var eviction: LocalDateTime? = null
        while (eviction == null) {
            println("Введите дату выезда (ГГГГ-ММ-ДДTчч:мм):")
            val input = scanner.nextLine()
            eviction = validation.parseDateTime(input)
            if (eviction == null) {
                println("Некорректный формат даты, попробуйте снова.")
                continue
            }
            if (!validation.validateCheckInEviction(checkIn, eviction)) {
                println("Дата выезда не может быть раньше даты заезда.")
                eviction = null
            }
        }

        val user = createDummyUser()
        return HotelReservation(id, checkIn, eviction, user)
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
