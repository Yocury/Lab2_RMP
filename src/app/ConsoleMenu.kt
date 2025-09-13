
import app.*
import java.util.*

class ConsoleApp(private val reservationService: ReservationService) {

    private val validation = ValidationService()
    private val scanner = Scanner(System.`in`)
    private val hotelFactory = HotelReservationFactory(reservationService, validation, scanner)
    private val planeFactory = PlaneReservationFactory(reservationService, validation, scanner)
    private val trainFactory = TrainReservationFactory(reservationService, validation, scanner)

    fun menuPlane(){
        while (true) {
            println("=== Меню системы бронирования билетами на самолет ===")
            println("1. Добавить бронь на самолет")
            println("2. Показать все брони самолетов")
            println("3. Удалить бронь самолета")
            println("0. Выход")
            print("Выберите действие: ")
            when (scanner.nextLine()){
                "1" -> addPlaneReservation()
                "2" -> showAllPlaneReservations()
                "3" -> println("Функция не работает")
                "0" -> {
                    println("Выход из программы")
                    return
                }
                else -> println("Некорректный выбор, попробуйте снова")
            }
        }
    }

    fun menuTrain(){
        while (true) {
            println("=== Меню системы бронирования поездами ===")
            println("1. Добавить бронь на поезд")
            println("2. Показать все брони поездов")
            println("3. Удалить бронь поезда")
            println("0. Выход")
            print("Выберите действие: ")
            when (scanner.nextLine()){
                "1" -> addTrainReservation()
                "2" -> showAllTrainReservations()
                "3" -> println("Функция не работает")
                "0" -> {
                    println("Выход из программы")
                    return
                }
                else -> println("Некорректный выбор, попробуйте снова")
            }
        }
    }


    fun menuHotel() {
        while (true) {
            println("=== Меню системы бронирования отелями ===")
            println("1. Добавить бронь на отель")
            println("2. Показать все брони отелей")
            println("3. Удалить бронь отеля")
            println("0. Выход")
            print("Выберите действие: ")
            when (scanner.nextLine()){
                "1" -> addHotelReservation()
                "2" -> showAllHotelReservations()
                "3" -> println("Функция не работает")
                "0" -> {
                    println("Выход из программы")
                    return
                }
                else -> println("Некорректный выбор, попробуйте снова")
            }
        }
    }
    fun run() {
        while (true) {
            println("=== Меню системы бронирования ===")
            println("1. Перейти в меню бронирования отелями")
            println("2. Перейти в меню бронирования самолетами")
            println("3. Перейти в меню бронирования поездами")
            println("0. Выход")
            print("Выберите действие: ")

            when (scanner.nextLine()) {
                "1" -> menuHotel()
                "2" -> menuPlane()
                "3" -> menuTrain()
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
        val reservations = reservationService.getAllHotelReservations()
        if (reservations.isEmpty()) println("Бронирований на отели нет")
        else {
        for (reservation in reservations) {
            println("id = ${reservation.id}")
            println("дата въезда = ${reservation.checkInDateTime}")
            println("дата выезда = ${reservation.evictionDateTime}")
            println("Гость = \"${reservation.user.fullName}\"")
            println() // пустая строка для разделения записей
        } }
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
