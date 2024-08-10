package domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

interface IDispatcherProvider {
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}

class DispatcherProvider : IDispatcherProvider {
    override fun ui(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun io(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}