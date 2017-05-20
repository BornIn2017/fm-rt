package com.lucidware.fm_rt.domain.use_causes

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.mockito.Mockito.`when` as whenDo

@RunWith(RobolectricTestRunner::class)
class UseCaseTest {

    private lateinit var useCase: UseCase<Unit>
    private var scheduler = Schedulers.computation()
    @Mock lateinit var mockedSingle: Single<Unit>
    @Mock lateinit var mockedUseCaseSchedule: UseCaseSchedule

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = TestUseCase(mockedSingle, mockedUseCaseSchedule)
        whenDo(mockedUseCaseSchedule.scheduler).thenReturn(scheduler)
    }

    @Test
    fun shouldDoPerformCorrectly() {
        // when
        val single = useCase.perform()

        // then
        assertThat(single).isNotNull()
    }

    @Test
    fun shouldDoPerformWithoutRedirectionCorrectly() {
        // when
        val single = useCase.performWithoutRedirection()

        // then
        assertThat(single).isNotNull()
    }

    @Test
    fun shouldDoPerformOnTheSameThreadCorrectly() {
        // when
        val single = useCase.performOnTheSameThread()

        // then
        assertThat(single).isEqualTo(mockedSingle)
        verifyZeroInteractions(mockedSingle)
        verifyZeroInteractions(mockedUseCaseSchedule)
    }

    private class TestUseCase(val singleUnit: Single<Unit>,
                              useCaseSchedule: UseCaseSchedule) : UseCase<Unit>(useCaseSchedule) {
        override fun doWork(): Single<Unit> {
            return singleUnit
        }
    }
}