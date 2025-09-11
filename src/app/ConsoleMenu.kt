import domain.model.*
import java.time.LocalDateTime
import java.util.*

class ConsoleApp(private val reservationService: ReservationService) {

    private val validation = ValidationService()
    private val scanner = Scanner(System.`in`)

    fun run() {
        while (true) {
            println("=== Меню системы бронирования ===")
            println("1. Добавить бронь на отель")
            println("2. Добавить бронь на самолет")
            println("3. Добавить бронь на поезд")
            println("4. Показать все брони отелей")
            println("5. Показать все брони самолетов")
            println("6. Показать все брони поездов")
            println("0. Выход")
            print("Выберите действие: ")

            when (scanner.nextLine()) {
                "1" -> addHotelReservation()
                "2" -> addPlaneReservation()
                "3" -> addTrainReservation()
                "4" -> showAllHotelReservations()
                "5" -> showAllPlaneReservations()
                "6" -> showAllTrainReservations()
                "0" -> {
                    println("Выход из программы")
                    return
                }
                else -> println("Некорректный выбор, попробуйте снова")
            }
            println()
        }
    }

    private fun addHotelReservation() {
        println("Добавление брони на отель")
        val id = reservationService.getLastIdHotel()
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
        val hotelReservation = HotelReservation(id, checkIn, eviction, user)
        reservationService.addHotelReservation(hotelReservation)
        println("Бронь отеля добавлена с ID $id")
    }

    private fun addPlaneReservation() {
        println("Добавление брони на самолет")
        val id = reservationService.getLastIdPlane()
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
        val planeReservation = PlaneReservation(id, arrival, departure, arrivalCity, seat, user)
        reservationService.addPlaneReservation(planeReservation)
        println("Бронь самолета добавлена с ID $id")
    }

    private fun addTrainReservation() {
        println("Добавление брони на поезд")
        val id = reservationService.getLastIdTrain()
        println("Введите ID поезда (числовой):")
        val trainId = scanner.nextLine().toLongOrNull()
        if (trainId == null) {
            println("Некорректный ID поезда")
            return
        }

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
        val trainReservation = TrainReservation(
            id,
            arrivalCity,
            emptyMap(),
            departureCity,
            arrivalDateTime,
            departureDateTime,
            seat,
            user
        )
        reservationService.addTrainReservation(trainReservation)
        println("Бронь поезда добавлена с ID $id")
    }

    private fun showAllHotelReservations() {
        val list = reservationService.getAllHotelReservations()
        if (list.isEmpty()) println("Бронирований на отели нет") else list.forEach { println(it) }
    }

    private fun showAllPlaneReservations() {
        val list = reservationService.getAllPlaneReservations()
        if (list.isEmpty()) println("Бронирований на самолеты нет") else list.forEach { println(it) }
    }

    private fun showAllTrainReservations() {
        val list = reservationService.getAllTrainReservations()
        if (list.isEmpty()) println("Бронирований на поезда нет") else list.forEach { println(it) }
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
