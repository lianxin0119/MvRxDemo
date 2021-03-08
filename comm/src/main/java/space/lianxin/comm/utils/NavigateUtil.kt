package space.lianxin.comm.utils

import android.app.Activity
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

/**
 *  author : hmily
 *  e-mail : 297277239@qq.com
 *  time   : 2019/10/14
 *  desc   : ARouter页面跳转工具
 */
object NavigateUtil {

    /**
     * 跳转到下一个界面。如果不需要带回调的方式启动。就不需要传[activity]。
     * 只传[path]就行。如果需要带回调启动就传递[activity]并且从写onActivityResult方法。
     *
     * @param path 路由地址
     * @param activity 需要和[requestCode]一起传递。
     * @param requestCode 请求code。
     */
    fun navigation(path: String, activity: Activity? = null, requestCode: Int = 0) {
        ARouter.getInstance().build(path).run {
            if (activity != null) {
                navigation(activity, requestCode)
            } else {
                navigation()
            }

        }
    }

    fun newIntent(path: String): Postcard {
        return ARouter.getInstance().build(path)
    }

}