package space.lianxin.demo.application

import android.app.Application
import android.content.Context
import space.lianxin.base.app.AppLifecycle
import space.lianxin.base.di.GlobeConfigModule
import space.lianxin.base.integration.ConfigModule
import space.lianxin.base.integration.IRepositoryManager

/**
 * ===========================================
 * 模块配置
 *
 * @author: lianxin
 * @date: 2021/3/2 14:17
 * ===========================================
 */
class AppConfig : ConfigModule {

    override fun applyOptions(context: Context, builder: GlobeConfigModule.Builder) {

    }

    override fun injectActivityLifecycle(
        context: Context,
        lifecycles: ArrayList<Application.ActivityLifecycleCallbacks>
    ) {

    }

    override fun injectAppLifecycle(context: Context, lifecycles: ArrayList<AppLifecycle>) {

    }

    override fun registerComponents(context: Context, repositoryManager: IRepositoryManager) {
        repositoryManager.injectRetrofitService(
        )
    }
}