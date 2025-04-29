package core.ui.components.platform

import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertController
import platform.UIKit.UIApplication

actual fun showToast(message: String, isLongDuration: Boolean, context: Any?) {
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController

    if (rootViewController != null) {
        val alertController = UIAlertController.alertControllerWithTitle(
            title = "Aviso",
            message = message,
            preferredStyle = 1L
        )

        val okAction = UIAlertAction.actionWithTitle(
            title = "Aceptar",
            style = 0L,
            handler = { _ -> Unit }
        )

        alertController.addAction(okAction)

        rootViewController.presentViewController(
            viewControllerToPresent = alertController,
            animated = true,
            completion = null
        )
    } else {
        println("Alert: $message")
    }
}