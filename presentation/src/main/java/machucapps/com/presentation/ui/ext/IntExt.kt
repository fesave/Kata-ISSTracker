package machucapps.com.presentation.ui.ext

import java.text.SimpleDateFormat
import java.util.*


fun Int.formatDate(): String {
    val simpleDateFormat = SimpleDateFormat("EEEE dd 'de' MMMM 'a las' HH:mm", Locale.getDefault())
    return simpleDateFormat.format(this * 1000L)
}

fun Int.formatDuration(text: String): String {
    val minutes = this.div(60)
    val seconds = this.rem(60)
    return String.format(
        text,
        minutes,
        seconds
    )
}