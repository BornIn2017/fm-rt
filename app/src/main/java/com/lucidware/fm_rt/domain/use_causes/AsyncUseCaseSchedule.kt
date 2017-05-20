package com.lucidware.fm_rt.domain.use_causes

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class AsyncUseCaseSchedule : UseCaseSchedule {
    override fun getScheduler(): Scheduler {
        return Schedulers.io()
    }
}
