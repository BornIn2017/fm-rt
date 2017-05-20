package com.lucidware.fm_rt.presentation.base

import io.reactivex.disposables.Disposable

interface DisposableRegistrants {
    fun registerDisposable(disposable: Disposable)
}

fun Disposable.register(registrants: DisposableRegistrants) {
    registrants.registerDisposable(this)
}
