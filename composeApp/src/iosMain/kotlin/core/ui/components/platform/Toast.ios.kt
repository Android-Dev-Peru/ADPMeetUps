package core.ui.components.platform

import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyle
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyle
import platform.UIKit.UIApplication

actual fun showToast(message: String, isLongDuration: Boolean, context: Any?) {
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController

    if (rootViewController != null) {
        // Crear el alert controller
        val alertController = UIAlertController.alertControllerWithTitle(
            title = "Aviso",
            message = message,
            preferredStyle = 1L
            //preferredStyle = UIAlertControllerStyle.UIAlertControllerStyleAlert
        )

        // Añadir un botón de Aceptar para cerrar el alert
        val okAction = UIAlertAction.actionWithTitle(
            title = "Aceptar",
            style = 0L,
            //style = UIAlertActionStyle.UIAlertActionStyleDefault,
            handler = { _ -> Unit }
        )

        alertController.addAction(okAction)

        // Presentar el alert
        rootViewController.presentViewController(
            viewControllerToPresent = alertController,
            animated = true,
            completion = null
        )
    } else {
        // Fallback a la salida de consola si no podemos mostrar el alert
        println("Alert: $message")
    }
}