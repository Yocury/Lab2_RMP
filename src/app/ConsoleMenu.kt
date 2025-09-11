
import app.*
import java.util.*

class ConsoleApp(private val reservationService: ReservationService) {

    private val validation = ValidationService()
    private val scanner = Scanner(System.`in`)
    private val hotelFactory = HotelReservationFactory(reservationService, validation, scanner)
    private val planeFactory = PlaneReservationFactory(reservationService, validation, scanner)
    private val trainFactory = TrainReservationFactory(reservationService, validation, scanner)

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
        val reservation = hotelFactory.createReservation()
        reservationService.addHotelReservation(reservation)
        println("Бронь отеля добавлена с ID ${reservation.id}")
    }

    private fun addPlaneReservation() {
        println("Добавление брони на самолет")
        val reservation = planeFactory.createReservation()
        reservationService.addPlaneReservation(reservation)
        println("Бронь самолета добавлена с ID ${reservation.id}")
    }

    private fun addTrainReservation() {
        println("Добавление брони на поезд")
        val reservation = trainFactory.createReservation()
        reservationService.addTrainReservation(reservation)
        println("Бронь поезда добавлена с ID ${reservation.id}")
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
}
