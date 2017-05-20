package com.lucidware.fm_rt.domain.use_causes

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class ImmediateUseCaseSchedule : UseCaseSchedule {
    override fun getScheduler(): Scheduler {
        return TestScheduler()
    }
}