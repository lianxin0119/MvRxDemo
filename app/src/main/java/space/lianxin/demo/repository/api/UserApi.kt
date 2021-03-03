package space.lianxin.demo.repository.api

import io.reactivex.Observable
import retrofit2.http.POST
import space.lianxin.comm.BuildConfig

/**
 * ===========================================
 * 用户相关Api
 *
 * @author: lianxin
 * @date: 2021/3/3 12:45
 * ===========================================
 */
interface UserApi {

    /** 登录 */
    @POST("${BuildConfig.ROUTE}?op=aslhd")
    fun login(): Observable<Boolean>

}