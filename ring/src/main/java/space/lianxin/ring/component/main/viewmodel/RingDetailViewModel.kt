package space.lianxin.ring.component.main.viewmodel

import com.airbnb.mvrx.MvRxState
import space.lianxin.base.mvvm.MvRxViewModel
import space.lianxin.ring.repostory.bean.result.RingDetailBean


data class RingDetailState(
    val data: RingDetailBean? = null
) : MvRxState

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/8 19:18
 * ===========================================
 */
class RingDetailViewModel(
    instate: RingDetailState = RingDetailState()
) : MvRxViewModel<RingDetailState>(instate) {

    fun loadData() {
        setState {
            copy(data = RingDetailBean("数据1", "数据2", "数据4", "数据5", "数据6", "数据7"))
        }
    }

}