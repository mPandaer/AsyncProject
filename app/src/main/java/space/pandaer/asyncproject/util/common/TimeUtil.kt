package space.pandaer.asyncproject.util.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun dateFormat(date:LocalDateTime = LocalDateTime.now(), format:String = "yyyy-MM-dd HH:mm:ss") =
    date.format(DateTimeFormatter.ofPattern(format)).toString()
