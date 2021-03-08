package space.lianxin.ring.config

import android.app.Application
import android.content.Context
import space.lianxin.base.app.AppLifecycle
import space.lianxin.base.di.GlobeConfigModule
import space.lianxin.base.integration.ConfigModule
import space.lianxin.base.integration.IRepositoryManager
import space.lianxin.ring.repostory.api.RingApi

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/8 15:54
 * ===========================================
 */
class ModuleConfig : ConfigModule {

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
        repositoryManager.injectRetrofitService(RingApi::class.java)
    }

}