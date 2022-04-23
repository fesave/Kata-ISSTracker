package machucapps.com.framework.network.response

data class ISSTrackerDTO(
    var message: String = "",
    var request: RequestDTO = RequestDTO(),
    var response: List<ResponseDTO> = listOf()
)