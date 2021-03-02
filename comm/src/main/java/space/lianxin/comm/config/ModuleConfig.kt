package space.lianxin.comm.config

import android.app.Application
import android.content.Context
import space.lianxin.base.app.AppLifecycle
import space.lianxin.base.di.GlobeConfigModule
import space.lianxin.base.integration.ConfigModule
import space.lianxin.base.integration.IRepositoryManager
import space.lianxin.comm.constants.ApiConstants

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/2 15:20
 * ===========================================
 */
class ModuleConfig : ConfigModule {

    override fun applyOptions(context: Context, builder: GlobeConfigModule.Builder) {
        builder.baseUrl(ApiConstants.Host.BASE_URL)
    }

    override fun injectActivityLifecycle(
        context: Context,
        lifecycles: ArrayList<Application.ActivityLifecycleCallbacks>
    ) {

    }

    override fun injectAppLifecycle(context: Context, lifecycles: ArrayList<AppLifecycle>) {

    }

    override fun registerComponents(context: Context, repositoryManager: IRepositoryManager) {

    }
}