package machucapps.com.framework.network.response

data class RequestDTO(
    var altitude: Int = 0,
    var datetime: Int = 0,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var passes: Int = 0
)