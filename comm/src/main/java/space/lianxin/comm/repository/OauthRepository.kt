package space.lianxin.comm.repository

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import io.reactivex.Observable
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import space.lianxin.base.repository.BaseRepositoryRemote
import space.lianxin.comm.repository.bean.request.LoginBean
import space.lianxin.comm.repository.bean.result.LoginResultBean
import space.lianxin.comm.repository.datasource.remote.OauthRemoteDataSource
import space.lianxin.comm.utils.api.exception.ApiException

/**
 * ===========================================
 * 登录相关的数据仓库。
 *
 * @author: lianxin
 * @date: 2021/3/3 20:21
 * ===========================================
 */
class OauthRepository(
    remote: OauthRemoteDataSource,
    private val userRepo: UserRepository
) : BaseRepositoryRemote<OauthRemoteDataSource>(remote) {

    companion object {

        private const val KEY_CACHE_OAUTHBEAN = "key_cache_oauthbean"
        private var userId: Int? = null
        private var userIdToken: String? = null
        private var tempLoginResultBean: LoginResultBean? = null

        /** 初始化缓存数据 */
        fun init() {
            val json = CacheRepository.getCommMMKV().decodeString(KEY_CACHE_OAUTHBEAN, "")
            if (json.isNotBlank()) {
                try {
                    val bean =
                        GsonUtils.fromJson<LoginResultBean>(json, LoginResultBean::class.java)
                    initData(bean)
                } catch (e: Exception) {
                }
            }
        }

        private fun initData(bean: LoginResultBean) {
            userId = bean.account_id
            userIdToken = bean.userid
        }

        /** 清除缓存数据 */
        fun clearCache() {
            userId = null
            CacheRepository.getCommMMKV().removeValueForKey(KEY_CACHE_OAUTHBEAN)
        }

        /** 缓存登录数据 */
        fun cacheLoginResultBean() {
            tempLoginResultBean?.let {
                val json = GsonUtils.toJson(it)
                CacheRepository.getCommMMKV().encode(KEY_CACHE_OAUTHBEAN, json)
            }
        }

        fun getUserId(): Int? = userId
        internal fun getUserIdToken(): String? = userIdToken

        /** 是否已经登录 */
        fun isLogin(): Boolean {
            return userId != null
        }
    }

    fun phoneLogin(bean: LoginBean): Observable<LoginResultBean> {
        clearCache()
        return remoteDataSource.phoneLogin(bean)
            .map {
                val data = it.result.first()
                tempLoginResultBean = data
                LogUtils.d("login success result = ${GsonUtils.toJson(it)}")
                initData(data)
                cacheLoginResultBean()
                data
            }
            .doOnError {
                if (it is ApiException) {
                    LogUtils.e("login failed. error message = ${it.msg}")
                }
            }
    }

}

//授权相关
const val TAG_KODEIN_MODULE_REPOSITORY_OAUTH = "oauthRepositoryModel"
val oauthRepositoryModel = Kodein.Module(TAG_KODEIN_MODULE_REPOSITORY_OAUTH) {
    bind<OauthRemoteDataSource>() with singleton { OauthRemoteDataSource() }
    bind<OauthRepository>() with singleton { OauthRepository(instance(), instance()) }
}