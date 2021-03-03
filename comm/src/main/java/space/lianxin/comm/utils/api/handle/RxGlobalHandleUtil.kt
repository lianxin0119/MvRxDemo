package space.lianxin.comm.utils.api.handle

import com.blankj.utilcode.util.NetworkUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import org.kodein.di.generic.instance
import space.lianxin.base.app.BaseApplication
import space.lianxin.comm.repository.OauthRepository
import space.lianxin.comm.utils.api.ApiErrorMessageHelper
import space.lianxin.comm.utils.api.ErrorCode
import space.lianxin.comm.utils.api.exception.ApiException
import space.lianxin.comm.utils.api.exception.LoginException
import space.lianxin.comm.utils.api.exception.TokenExpiredException
import java.net.ConnectException
import java.util.concurrent.atomic.AtomicBoolean

/**
 * ===========================================
 * 网络请求全局拦截及异常处理工具类.
 *
 * @author: lianxin
 * @date: 2021/3/3 19:24
 * ===========================================
 */
object RxGlobalHandleUtil {

    private val tokenExpiredRetryConfig = RetryConfig {
        refreshToken()
    }

    private var refreshTokenPublishSubject: PublishSubject<Boolean> =
        PublishSubject.create<Boolean>()
    private var isRefreshingToken: AtomicBoolean = AtomicBoolean(false)

    private fun refreshToken(): Single<Boolean> {
        if (isRefreshingToken.compareAndSet(false, true)) {
            val oauthRepository by BaseApplication.INSTANCE.kodein.instance<OauthRepository>()
            oauthRepository.phoneLogin()
                .map {
                    true
                }
                .doOnNext {
                    isRefreshingToken.set(false)
                }
                .doOnError {
                    isRefreshingToken.set(false)
                }
                .subscribe(refreshTokenPublishSubject)
        }
        return refreshTokenPublishSubject.single(false)
    }

    fun <T> globalHandle(): GlobalHandleTransformer<T> {
        return GlobalHandleTransformer(
            globalOnNextInterceptor = {
                // 全局成功预处理
                val codeTemp = when {
                    it.code > 0 -> it.code
                    it.status > 0 -> it.status
                    else -> 0
                }
                when (codeTemp) {
                    ErrorCode.Success -> { // 请求成功返回正常
                        Observable.just(it.data)
                    }
                    ErrorCode.TokenExpired -> { // Token失效
                        Observable.error(TokenExpiredException())
                    }
                    else -> { // 其他请求成功，返回异常
                        ApiErrorMessageHelper.showToastMessage(it.code, it.message)
                        Observable.error(ApiException(it.code, it.message))
                    }
                }
            },
            globalOnErrorResume = {
                // 全局异常预处理
                when (it) {
                    is LoginException -> { // 登录异常，跳转到登录页面

                    }
                }
                Observable.error<T>(it)
            },
            retryConfigProvider = {
                // 异常重试的配置
                when (it) {
                    is ConnectException -> { // 请求连接异常的配置
                        if (NetworkUtils.isConnected()) { // 有网络连接重试三次
                            RetryConfig(maxRetries = 3, delay = 500)
                        } else { // 无网络连接不重试
                            RetryConfig()
                        }
                    }
                    is TokenExpiredException -> { // token失效，刷新token。
                        tokenExpiredRetryConfig
                    }
                    else -> RetryConfig()
                }
            },
            upStreamSchedulerProvider = { AndroidSchedulers.mainThread() },
            downStreamSchedulerProvider = { AndroidSchedulers.mainThread() }
        )
    }

}