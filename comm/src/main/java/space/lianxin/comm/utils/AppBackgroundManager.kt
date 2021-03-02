package space.lianxin.comm.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * ===========================================
 * App前后台切换的管理类.
 *
 * @author: lianxin
 * @date: 2021/3/2 10:42
 * ===========================================
 */
object AppBackgroundManager {

    /** 一些状态基用于判断用户是否在前台  */
    private var isAppForeground = false

    private var mListener: ((isAppForeground: Boolean) -> Unit)? = null

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onForeground() {
                isAppForeground = true
                mListener?.invoke(isAppForeground)
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onBackground() {
                isAppForeground = false
                mListener?.invoke(isAppForeground)
            }

        })
    }

    fun isAppOnForeground(): Boolean {
        return isAppForeground
    }

    fun setAppStateListener(listener: ((isAppForeground: Boolean) -> Unit)?) {
        mListener = listener
    }

}