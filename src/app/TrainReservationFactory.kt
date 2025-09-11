import domain.model.TrainReservation
import domain.model.User
import java.time.LocalDateTime
import java.util.Scanner

class TrainReservationFactory(
    private val reservationService: ReservationService,
    private val validation: ValidationService,
    private val scanner: Scanner
) : ReservationFactory<TrainReservation> {

    override fun createReservation(): TrainReservation {
        val id = reservationService.getNextTrainId()

        println("Введите город прибытия:")
        val arrivalCity = scanner.nextLine()

        println("Введите город отправления:")
        val departureCity = scanner.nextLine()

        var arrivalDateTime: LocalDateTime? = null
        while (arrivalDateTime == null) {
            println("Введите дату и время прибытия (ГГГГ-ММ-ДДTчч:мм):")
            val input = scanner.nextLine()
            arrivalDateTime = validation.parseDateTime(input)
            if (arrivalDateTime == null) println("Некорректный формат даты, попробуйте снова.")
        }

        var departureDateTime: LocalDateTime? = null
        while (departureDateTime == null) {
            println("Введите дату и время отправления (ГГГГ-ММ-ДДTчч:мм):")
            val input = scanner.nextLine()
            departureDateTime = validation.parseDateTime(input)
            if (departureDateTime == null) println("Некорректный формат даты, попробуйте снова.")
        }

        println("Введите место в поезде:")
        val seat = scanner.nextLine()

        val user = createDummyUser()
        return TrainReservation(
            id,
            arrivalCity,
            emptyMap(),
            departureCity,
            arrivalDateTime,
            departureDateTime,
            seat,
            user
        )
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
