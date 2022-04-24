package machucapps.com.framework.network.response.iss

data class ISSTrackerDTO(
    val message: String = "",
    val request: RequestDTO = RequestDTO(),
    val response: List<ResponseDTO> = listOf()
)