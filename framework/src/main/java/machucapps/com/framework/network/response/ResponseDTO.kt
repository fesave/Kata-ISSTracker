package machucapps.com.framework.network.response

import com.google.gson.annotations.SerializedName
import machucapps.com.domain.data.PassItem

data class ResponseDTO(
    var duration: Int = 0,
    @SerializedName("risetime")
    var riseTime: Int = 0
)

fun ResponseDTO.toPassItem() = PassItem(
    duration = this.duration,
    riseTime = this.riseTime
)