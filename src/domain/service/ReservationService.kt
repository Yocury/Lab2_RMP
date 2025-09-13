import domain.model.*

class ReservationService {
    private val hotelReservations = mutableMapOf<Int, HotelReservation>()
    private var lastIdHotel: Int = 0

    private val planeReservations = mutableMapOf<Int, PlaneReservation>()
    private var lastIdPlane: Int = 0

    private val trainReservations = mutableMapOf<Int, TrainReservation>()
    private var lastIdTrain: Int = 0

    fun getNextHotelId(): Int = ++lastIdHotel
    fun addHotelReservation(reservation: HotelReservation) {
        hotelReservations[reservation.id] = reservation
    }
    fun getAllHotelReservations(): List<HotelReservation> = hotelReservations.values.toList()




    fun getNextPlaneId(): Int = ++lastIdPlane
    fun addPlaneReservation(reservation: PlaneReservation) {
        planeReservations[reservation.id] = reservation
    }
    fun getAllPlaneReservations(): List<PlaneReservation> = planeReservations.values.toList()

    fun getNextTrainId(): Int = ++lastIdTrain
    fun addTrainReservation(reservation: TrainReservation) {
        trainReservations[reservation.id] = reservation
    }
    fun getAllTrainReservations(): List<TrainReservation> = trainReservations.values.toList()

}
