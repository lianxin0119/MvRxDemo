package space.lianxin.comm.repository

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import io.reactivex.Observable
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import space.lianxin.base.repository.BaseRepositoryRemote
import space.lianxin.comm.repository.bean.LoginResultBean
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
    private val dataSource: OauthRemoteDataSource,
    private val userRepo: UserRepository
) : BaseRepositoryRemote<OauthRemoteDataSource>(dataSource) {

    companion object {

        private const val KEY_CACHE_OAUTHBEAN = "key_cache_oauthbean"
        private var userId: Long = 0L
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
            userId = bean.uid
        }

        /** 清除缓存数据 */
        fun clearCache() {
            userId = 0L
            CacheRepository.getCommMMKV().removeValueForKey(KEY_CACHE_OAUTHBEAN)
        }

        /** 缓存登录数据 */
        fun cacheLoginResultBean() {
            tempLoginResultBean?.let {
                val json = GsonUtils.toJson(it)
                CacheRepository.getCommMMKV().encode(KEY_CACHE_OAUTHBEAN, json)
            }
        }

        fun getUserId(): Long = userId

        /** 是否已经登录 */
        fun isLogin(): Boolean {
            return userId != 0L
        }
    }

    fun phoneLogin(): Observable<LoginResultBean> {
        clearCache()
        return dataSource.phoneLogin()
            .map {
                LogUtils.d("login success result = ${GsonUtils.toJson(it)}")
                initData(it)
                cacheLoginResultBean()
                it
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