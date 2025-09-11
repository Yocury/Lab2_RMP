import domain.model.HotelReservation
import domain.model.PlaneReservation
import domain.model.TrainReservation


class ReservationService {

    private val hotelReservations = mutableMapOf<Int, HotelReservation>()
    private val lastIdHotel : Int = 0
    private val planeReservations = mutableMapOf<Int, PlaneReservation>()
    private val lastIdPlane : Int = 0
    private val trainReservations = mutableMapOf<Int, TrainReservation>()
    private val lastIdTrain : Int = 0

    fun newId(model : Int) {
        when (model){
            1 -> (lastIdHotel + 1)
            2 -> (lastIdPlane + 1)
            3 -> (lastIdTrain + 1)
        }
    }




    fun addHotelReservation(reservation: HotelReservation) {
        hotelReservations[reservation.id] = reservation
    }

    fun removeHotelReservation(id: Int) {
        hotelReservations.remove(id)
    }

    fun getHotelReservation(id: Int): HotelReservation? {
        return hotelReservations[id]
    }

    fun addPlaneReservation(reservation: PlaneReservation) {
        planeReservations[reservation.id] = reservation
    }

    fun removePlaneReservation(id: Int) {
        planeReservations.remove(id)
    }

    fun getPlaneReservation(id: Int): PlaneReservation? {
        return planeReservations[id]
    }

    fun addTrainReservation(reservation: TrainReservation) {
        trainReservations[reservation.id] = reservation
    }

    fun removeTrainReservation(id: Int) {
        trainReservations.remove(id)
    }

    fun getTrainReservation(id: Int): TrainReservation? {
        return trainReservations[id]
    }

    fun getAllHotelReservations(): List<HotelReservation> = hotelReservations.values.toList()
    fun getLastIdHotel() : Int = lastIdHotel

    fun getAllPlaneReservations(): List<PlaneReservation> = planeReservations.values.toList()
    fun getLastIdPlane() : Int = lastIdPlane

    fun getAllTrainReservations(): List<TrainReservation> = trainReservations.values.toList()
    fun getLastIdTrain() : Int = lastIdTrain
}
