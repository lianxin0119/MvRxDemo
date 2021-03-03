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
        when (errorCode) {
            ErrorCode.PageNotFound -> "页面不存在"
            else -> {
                if (ErrorCodeUtils.isShowSeverGG(errorCode)) {
                    "服务器GG了，稍后重试。"
                } else {
                    serviceMessage
                }
            }
        }?.let {
            if (ErrorCodeUtils.isNeedShowMsg(errorCode)) {
                val disposable = Observable.just(it)
                    .compose(SchedulersUtil.applySchedulers())
                    .subscribe({ msg ->
                        ToastUtils.showShort(msg)
                    }, {})
            }
        }
    }

}