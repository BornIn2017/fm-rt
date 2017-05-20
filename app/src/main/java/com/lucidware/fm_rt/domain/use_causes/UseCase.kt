package com.lucidware.fm_rt.domain.use_causes

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

abstract class UseCase<T>(protected val useCaseSchedule: UseCaseSchedule) {

    open fun perform(): Single<T> {
        return doWork()
                .subscribeOn(useCaseSchedule.scheduler)
                .observeOn(AndroidSchedulers.mainThread())
    }

    open fun performWithoutRedirection(): Single<T> {
        return doWork()
                .subscribeOn(useCaseSchedule.scheduler)
    }

    open fun performOnTheSameThread(): Single<T> {
        return doWork()
    }

    protected abstract fun doWork(): Single<T>
}
