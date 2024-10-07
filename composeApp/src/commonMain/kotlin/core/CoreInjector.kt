package core

import domain.DispatcherProvider
import domain.IDispatcherProvider

object CoreInjector {

    val dispatcherProvider: IDispatcherProvider by lazy { DispatcherProvider() }
}