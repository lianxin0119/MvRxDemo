package space.lianxin.ring.repostory.datasource.remote

import io.reactivex.Observable
import space.lianxin.base.repository.BaseRemoteDataSource
import space.lianxin.comm.utils.api.BaseNetList
import space.lianxin.comm.utils.api.handle.RxGlobalHandleUtil
import space.lianxin.ring.repostory.api.RingApi
import space.lianxin.ring.repostory.bean.request.RDeviceName
import space.lianxin.ring.repostory.bean.result.DeviceNameBean

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/8 16:20
 * ===========================================
 */
class RingRemoteDataSource : BaseRemoteDataSource() {

    private val ringApi: RingApi = retrofitService(RingApi::class.java)

    fun queryDeviceNameList(body: RDeviceName): Observable<BaseNetList<DeviceNameBean>> {
        return ringApi.queryDeviceNameList(body)
            .compose(RxGlobalHandleUtil.globalHandle())
    }

}