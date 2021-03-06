package space.lianxin.comm.utils.api

import com.blankj.utilcode.util.ToastUtils
import io.reactivex.Observable
import space.lianxin.base.utils.SchedulersUtil

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/3 19:51
 * ===========================================
 */
object ApiErrorMessageHelper {

    /** 根据errorCode，显示相应的信息 */
    fun showToastMessage(errorCode: Int, serviceMessage: String?) {
        if (ErrorCodeUtils.isNeedShowMsg(errorCode)) {
            serviceMessage?.let {
                val disposable = Observable.just(it)
                    .compose(SchedulersUtil.applySchedulers())
                    .subscribe({ msg ->
                        ToastUtils.showShort(msg)
                    }, {})
            }
        }
    }

}