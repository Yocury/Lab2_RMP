import domain.model.HotelReservation
import domain.model.PlaneReservation
import domain.model.TrainReservation


data class UserReservations(
    val hotelReservations: Map<Int, HotelReservation> = emptyMap(),
    val planeReservations: Map<Int, PlaneReservation> = emptyMap(),
    val trainReservations: Map<Int, TrainReservation> = emptyMap()
)
