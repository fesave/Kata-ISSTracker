package machucapps.com.framework.network.response.number

import machucapps.com.domain.data.NumberText

data class NumberDTO(
    val found: Boolean = false,
    val number: Int = 0,
    val text: String = "",
    val type: String = ""
)

fun NumberDTO.toNumber() = NumberText(
    text = this.text
)