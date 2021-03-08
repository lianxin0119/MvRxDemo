package space.lianxin.ring.repostory

import io.reactivex.Observable
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import space.lianxin.base.repository.BaseRepositoryRemote
import space.lianxin.comm.utils.api.BaseNetList
import space.lianxin.ring.repostory.bean.request.RDeviceName
import space.lianxin.ring.repostory.bean.result.DeviceNameBean
import space.lianxin.ring.repostory.datasource.remote.RingRemoteDataSource

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/8 16:19
 * ===========================================
 */
class RingRepostory(
    remote: RingRemoteDataSource
) : BaseRepositoryRemote<RingRemoteDataSource>(remote) {

    fun queryDeviceNameList(body: RDeviceName): Observable<BaseNetList<DeviceNameBean>> {
        return remoteDataSource.queryDeviceNameList(body)
    }

}

// ring
const val TAG_KODEIN_MODULE_REPOSITORY_RING = "ringRepositoryModel"
val ringRepositoryModel = Kodein.Module(TAG_KODEIN_MODULE_REPOSITORY_RING) {
    bind<RingRemoteDataSource>() with singleton { RingRemoteDataSource() }
    bind<RingRepostory>() with singleton { RingRepostory(instance()) }
}

