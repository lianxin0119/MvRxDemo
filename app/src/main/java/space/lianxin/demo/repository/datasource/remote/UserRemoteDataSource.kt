package space.lianxin.demo.repository.datasource.remote

import io.reactivex.Observable
import space.lianxin.base.repository.BaseRemoteDataSource
import space.lianxin.demo.repository.api.UserApi

/**
 * ===========================================
 * 用户相关远程仓库数据
 *
 * @author: lianxin
 * @date: 2021/3/3 13:05
 * ===========================================
 */
class UserRemoteDataSource : BaseRemoteDataSource() {

    private val userApi: UserApi = retrofitService(UserApi::class.java)

    fun login(): Observable<Boolean> {
        return userApi.login()
    }


}