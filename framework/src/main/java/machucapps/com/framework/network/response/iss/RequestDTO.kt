package machucapps.com.framework.network.response.iss

data class RequestDTO(
    val altitude: Int = 0,
    val datetime: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val passes: Int = 0
)