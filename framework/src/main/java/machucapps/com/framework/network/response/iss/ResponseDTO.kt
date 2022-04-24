package machucapps.com.framework.network.response.iss

import com.google.gson.annotations.SerializedName
import machucapps.com.domain.data.PassItem

data class ResponseDTO(
    val duration: Int = 0,
    @SerializedName("risetime")
    val riseTime: Int = 0
)

fun ResponseDTO.toPassItem() = PassItem(
    duration = this.duration,
    riseTime = this.riseTime
)