package space.lianxin.comm.constants.arouter

import com.alibaba.android.arouter.launcher.ARouter
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
            ARouter.getInstance().build(RouterConstants.App.MainActivity)
                .putExtra(RouterParam.App.Title, title)
                .navigation()
        }


    }


}