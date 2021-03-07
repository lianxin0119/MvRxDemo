package space.lianxin.comm.utils

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

/**
 *  author : hmily
 *  e-mail : 297277239@qq.com
 *  time   : 2019/10/14
 *  desc   : ARouter页面跳转工具
 */
object NavigateUtil {

    /** @param context:请传入Activity */
    fun navigation(context: Context, path: String) {
        ARouter.getInstance().build(path).navigation(context)
    }

    fun newIntent(path: String): Postcard {
        return ARouter.getInstance().build(path)
    }
    
}