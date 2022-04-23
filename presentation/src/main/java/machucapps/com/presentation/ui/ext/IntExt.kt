package machucapps.com.presentation.ui.ext

import android.content.Context
import machucapps.com.isstracker.R
import java.text.SimpleDateFormat
import java.util.*


fun Int.formatDate(): String {
    val simpleDateFormat = SimpleDateFormat("EEEE dd 'de' MMMM 'a las' HH:mm", Locale.getDefault())
    return simpleDateFormat.format(this * 1000L)
}

fun Int.formatDuration(context: Context): String {
    val minutes = this.div(60)
    val seconds = this.rem(60)
    return String.format(
        context.getString(R.string.iss_duration_time),
        minutes,
        seconds
    )
}