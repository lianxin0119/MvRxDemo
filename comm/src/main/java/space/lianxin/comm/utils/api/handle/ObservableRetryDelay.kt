package space.lianxin.comm.utils.api.handle

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit

/**
 * ===========================================
 * Observable的接口请求重连.
 * @author: lianxin
 * @date: 2021/3/3 19:41
 * ===========================================
 */
class ObservableRetryDelay(
    val retryConfigProvider: (Throwable) -> RetryConfig
) : Function<Observable<Throwable>, ObservableSource<*>> {

    private var retryCount: Int = 0

    override fun apply(throwableObs: Observable<Throwable>): ObservableSource<*> {
        return throwableObs
            .flatMap { error ->
                val (maxRetries, delay, retryCondition) = retryConfigProvider(error)
                if (++retryCount <= maxRetries) {
                    retryCondition()
                        .flatMapObservable { retry ->
                            if (retry)
                                Observable.timer(delay.toLong(), TimeUnit.MILLISECONDS)
                            else
                                Observable.error<Any>(error)
                        }
                } else Observable.error<Any>(error)
            }
    }
}