package space.lianxin.comm.repository.datasource.remote

import io.reactivex.Observable
import space.lianxin.base.repository.BaseRemoteDataSource
import space.lianxin.comm.repository.api.OauthApi
import space.lianxin.comm.repository.bean.request.LoginBean
import space.lianxin.comm.repository.bean.result.LoginResultBean
import space.lianxin.comm.utils.api.BaseNetList
import space.lianxin.comm.utils.api.handle.RxGlobalHandleUtil

/**
 * ===========================================
 * 登录相关的接口请求.
 *
 * @author: lianxin
 * @date: 2021/3/3 20:22
 * ===========================================
 */
class OauthRemoteDataSource : BaseRemoteDataSource() {

    private val oauthApi: OauthApi = retrofitService(OauthApi::class.java)

    fun phoneLogin(bean: LoginBean): Observable<BaseNetList<LoginResultBean>> {
        return oauthApi.phoneLogin(bean)
            .compose(RxGlobalHandleUtil.globalHandle())
    }

}