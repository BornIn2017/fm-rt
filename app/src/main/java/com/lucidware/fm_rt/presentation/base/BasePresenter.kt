package com.lucidware.fm_rt.presentation.base

import android.support.annotation.CallSuper
import android.support.annotation.VisibleForTesting
import com.infullmobile.android.infullmvp.PresentedView
import com.infullmobile.android.infullmvp.Presenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<out PresentedViewType : PresentedView<*, *>>
(presentedView: PresentedViewType) : Presenter<PresentedViewType>(presentedView), DisposableRegistrants {

    val compositeDisposable = CompositeDisposable()
        @VisibleForTesting get() = field

    @CallSuper
    override fun unbind() {
        compositeDisposable.dispose()
    }

    override fun registerDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}