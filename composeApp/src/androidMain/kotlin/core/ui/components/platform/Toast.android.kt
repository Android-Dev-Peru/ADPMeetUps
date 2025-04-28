package core.ui.components.platform

import android.content.Context
import android.widget.Toast

actual fun showToast(message: String, isLongDuration: Boolean, context: Any?) {
    val platformContext = context as? Context
    val duration = if (isLongDuration) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(platformContext, message, duration).show()
}