import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDto(

    @SerialName("restaurantId")
    val restaurantId: Int,

    @SerialName("restaurantName")
    val restaurantName: String,

    @SerialName("address")
    val address: String,

    @SerialName("time")
    val time: String,

    @SerialName("rating")
    val rating: String,

    @SerialName("distance")
    val distance: String,

    )