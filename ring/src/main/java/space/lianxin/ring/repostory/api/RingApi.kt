package space.lianxin.ring.repostory.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import space.lianxin.comm.BuildConfig
import space.lianxin.comm.utils.api.BaseNetList
import space.lianxin.comm.utils.api.BaseResponse
import space.lianxin.ring.repostory.bean.request.RDeviceName
import space.lianxin.ring.repostory.bean.result.DeviceNameBean

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/8 16:21
 * ===========================================
 */
interface RingApi {

    @POST("${BuildConfig.ROUTE}?op=QueryApproachName")
    fun queryDeviceNameList(@Body body: RDeviceName): Observable<BaseResponse<BaseNetList<DeviceNameBean>>>

}