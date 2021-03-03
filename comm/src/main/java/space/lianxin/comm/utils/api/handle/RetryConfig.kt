package space.lianxin.comm.utils.api.handle

import io.reactivex.Single

/** 重试次数 */
private const val DEFAULT_RETRY_TIMES = 1

/** 重试间隔时间 */
private const val DEFAULT_DELAY_DURATION = 500

/**
 * ===========================================
 * 请求重试的配置.
 *
 * @author: lianxin
 * @date: 2021/3/3 19:28
 * ===========================================
 */
data class RetryConfig(
    val maxRetries: Int = DEFAULT_RETRY_TIMES,
    val delay: Int = DEFAULT_DELAY_DURATION,
    val condition: () -> Single<Boolean> = { Single.just(false) }
)