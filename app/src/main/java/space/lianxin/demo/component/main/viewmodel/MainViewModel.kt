package space.lianxin.demo.component.main.viewmodel

import com.airbnb.mvrx.MvRxState
import io.reactivex.Single
import space.lianxin.base.mvvm.MvRxViewModel
import java.util.concurrent.TimeUnit


data class MainState(
    val data: String? = null
) : MvRxState

/**
 * ===========================================
 * 首页的ViewModel。
 *
 * @author: lianxin
 * @date: 2021/3/7 17:46
 * ===========================================
 */
class MainViewModel(instate: MainState = MainState()) : MvRxViewModel<MainState>(instate) {

    fun loadData() {
        withState { state ->
            val s = Single.just(state.data.orEmpty() + "01_")
                .delay(3, TimeUnit.SECONDS)
                .subscribe { it ->
                    setState {
                        copy(data = it)
                    }
                }
        }
    }

}