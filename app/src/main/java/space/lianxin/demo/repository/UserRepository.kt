package space.lianxin.demo.repository

import io.reactivex.Observable
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import space.lianxin.base.repository.BaseRepositoryRemote
import space.lianxin.demo.repository.datasource.local.UserLocalDataSource
import space.lianxin.demo.repository.datasource.remote.UserRemoteDataSource

/**
 * ===========================================
 * 用户数据获取仓库。
 *
 * @author: lianxin
 * @date: 2021/3/3 12:42
 * ===========================================
 */
class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : BaseRepositoryRemote<UserRemoteDataSource>(userRemoteDataSource) {

    /** 登录 */
    fun login(): Observable<Boolean> {
        return userRemoteDataSource
            .login()
            .map {
                userLocalDataSource.saveUserData()
                it
            }
    }


}

// 用户相关的Repository
const val TAG_KODEIN_MODULE_REPOSITORY_CIRCLE = "userRepositoryModel"
val userRepositoryModel = Kodein.Module(TAG_KODEIN_MODULE_REPOSITORY_CIRCLE) {
    bind<UserRemoteDataSource>() with singleton { UserRemoteDataSource() }
    bind<UserLocalDataSource>() with singleton { UserLocalDataSource() }
    bind<UserRepository>() with singleton { UserRepository(instance(), instance()) }
}