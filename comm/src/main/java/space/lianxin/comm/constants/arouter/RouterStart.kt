package space.lianxin.comm.constants.arouter

import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import space.lianxin.comm.extention.putExtra

/**
 * ===========================================
 * 如果跳转到的一个页面需要的参数比较多。为了防止写错，应当在
 * 当前类中声明start方法。如不需要参数或者参数较少，可以使用
 * [NavigateUtil]类来直接跳转，而不需要再当前类中声明。
 *
 *
 * @author: lianxin
 * @date: 2021/3/7 17:20
 * ===========================================
 */
object RouterStart {

    object App {

        /**
         * 示例
         * @param title 标题
         */
        fun startMainActivity(title: String) {
            // 关闭欢迎和登录等页面。主页打开时保持只有一个activity。
//            ActivityUtils.getActivityList().forEach {
//                it.finish()
//            }
//            BaseApplication.INSTANCE.appManager.killAll()
            // 在Activity内部实现。解决屏幕闪烁问题。
            ARouter.getInstance().build(RouterConstants.App.MainActivity)
                .putExtra(RouterParam.App.Title, title)
                .navigation()
        }


    }


}