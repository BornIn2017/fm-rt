package com.lucidware.fm_rt.domain.use_causes;

import io.reactivex.Scheduler;

public interface UseCaseSchedule {
    Scheduler getScheduler();
}