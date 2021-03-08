package space.lianxin.comm.repository.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import space.lianxin.comm.BuildConfig
import space.lianxin.comm.repository.bean.request.LoginBean
import space.lianxin.comm.repository.bean.result.LoginResultBean
import space.lianxin.comm.utils.api.BaseNetList
import space.lianxin.comm.utils.api.BaseResponse

/**
 * ===========================================
 * 登录相关的API.
 *
 * @author: lianxin
 * @date: 2021/3/3 20:23
 * ===========================================
 */
internal interface OauthApi {

    /** 使用手机号登录 */
    @POST("${BuildConfig.ROUTE}?op=Login")
    fun phoneLogin(@Body body: LoginBean): Observable<BaseResponse<BaseNetList<LoginResultBean>>>

}