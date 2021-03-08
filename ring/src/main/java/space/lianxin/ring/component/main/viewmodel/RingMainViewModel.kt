package space.lianxin.ring.component.main.viewmodel

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import org.kodein.di.generic.instance
import space.lianxin.base.app.BaseApplication
import space.lianxin.base.mvvm.MvRxViewModel
import space.lianxin.comm.extention.orZero
import space.lianxin.ring.repostory.RingRepostory
import space.lianxin.ring.repostory.bean.request.RDeviceName
import space.lianxin.ring.repostory.bean.result.DeviceNameBean

data class RingMainState(
    val listData: List<DeviceNameBean> = emptyList(),
    val request: Async<Any> = Uninitialized,
    val body: RDeviceName = RDeviceName(),
    val hasMore: Boolean = false
) : MvRxState

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/8 15:56
 * ===========================================
 */
class RingMainViewModel(
    instate: RingMainState = RingMainState()
) : MvRxViewModel<RingMainState>(instate) {

    private val ringRepo by BaseApplication.INSTANCE.kodein.instance<RingRepostory>()

    fun refresh() {
        withState { state ->
            if (state.request is Loading) {
                return@withState
            }
            ringRepo.queryDeviceNameList(state.body).execute {
                if (it is Loading) {
                    copy(request = it)
                } else {
                    val hasMore = it()?.total_pages.orZero() > state.body.page.orZero()
                    copy(request = it, listData = it()?.result.orEmpty(), hasMore = hasMore)
                }
            }
        }
    }

    fun loadMore() {
        withState { state ->
            if (state.request is Loading) {
                return@withState
            }
            ringRepo.queryDeviceNameList(state.body).execute {
                if (it is Loading) {
                    copy(request = it)
                } else {
                    val hasMore = it()?.total_pages.orZero() > state.body.page.orZero()
                    copy(
                        request = it,
                        listData = state.listData + it()?.result.orEmpty(),
                        hasMore = hasMore
                    )
                }
            }
        }
    }

}