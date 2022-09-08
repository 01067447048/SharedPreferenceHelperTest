package com.jaehyeon.sharedpreferencehelpertest.util

import androidx.lifecycle.Observer

/**
 * Created by Jaehyeon on 2022/09/08.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>?> {

    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }

}