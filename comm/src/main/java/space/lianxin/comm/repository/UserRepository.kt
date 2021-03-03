package space.lianxin.comm.repository

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import space.lianxin.base.repository.BaseRepositoryRemote
import space.lianxin.comm.repository.datasource.remote.UserRemoteDataSource

/**
 * ===========================================
 * 用户数据获取仓库。
 *
 * @author: lianxin
 * @date: 2021/3/3 12:42
 * ===========================================
 */
class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource
) : BaseRepositoryRemote<UserRemoteDataSource>(userRemoteDataSource) {

}

// 用户相关的Repository
const val TAG_KODEIN_MODULE_REPOSITORY_CIRCLE = "userRepositoryModel"
val userRepositoryModel = Kodein.Module(TAG_KODEIN_MODULE_REPOSITORY_CIRCLE) {
    bind<UserRemoteDataSource>() with singleton { UserRemoteDataSource() }
    bind<UserRepository>() with singleton { UserRepository(instance()) }
}