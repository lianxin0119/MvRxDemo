package space.lianxin.comm.config

import android.app.Application
import android.content.Context
import space.lianxin.base.app.AppLifecycle
import space.lianxin.base.di.GlobeConfigModule
import space.lianxin.base.integration.ConfigModule
import space.lianxin.base.integration.IRepositoryManager
import space.lianxin.comm.constants.ApiConstants
import space.lianxin.comm.repository.api.OauthApi
import space.lianxin.comm.repository.api.UserApi
import space.lianxin.comm.utils.api.RequestIntercept
import space.lianxin.comm.utils.api.handle.GlobalHttpHandlerImpl

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/2 15:20
 * ===========================================
 */
class ModuleConfig : ConfigModule {

    override fun applyOptions(context: Context, builder: GlobeConfigModule.Builder) {
        builder.baseUrl(ApiConstants.Host.BASE_URL)
        builder.addInterceptor(RequestIntercept(GlobalHttpHandlerImpl(context)))
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
            OauthApi::class.java,
            UserApi::class.java
        )
    }
}