package com.kasko.victor.testapp.toddoapp.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.d("SingleLiveEvent","Multiple observers registered but only one will be notified of changes.")
        }
        super.observe(owner, { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }
    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }
}
@MainThread
fun SingleLiveEvent<Nothing>.emit() {
    this.value = null
}
@MainThread
fun<T> SingleLiveEvent<T>.emit(value: T) {
    this.value = value
}
@SinceKotlin("1.1")
fun <T> emptySingleLiveEvent(): SingleLiveEvent<T> = SingleLiveEvent()