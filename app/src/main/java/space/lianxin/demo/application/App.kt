package space.lianxin.demo.application

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.RomUtils
import com.didichuxing.doraemonkit.DoraemonKit
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import space.lianxin.base.app.BaseApplication
import space.lianxin.base.di.ClientModule
import space.lianxin.comm.repository.OauthRepository
import space.lianxin.comm.repository.oauthRepositoryModel
import space.lianxin.comm.repository.userRepositoryModel
import space.lianxin.comm.utils.AppBackgroundManager
import space.lianxin.comm.widget.refresh.BlackManHeader
import space.lianxin.demo.BuildConfig
import space.lianxin.demo.R
import space.lianxin.ring.repostory.ringRepositoryModel
import java.io.IOException
import java.net.SocketException

/**
 * ===========================================
 * Appliction
 *
 * @author: lianxin
 * @date: 2021/3/2 9:45
 * ===========================================
 */
class App : BaseApplication() {

    val client: OkHttpClient by kodein.instance()

    override fun onCreate() {
        if (isMainProcess()) {
            // 初始化抓包和okhttp的log,由于放到initInMainProcess中会比初始化慢，所以需要提前
            initCharles()
        }
        super.onCreate()
        INSTANCE = this
        if (RomUtils.isXiaomi()) {
            // 小米推送只在主进程
            if (isMainProcess()) {
                initPush()
            }
        } else {
            // 推送需要在所有进程中初始化
            initPush()
        }
    }

    override fun initInMainProcess() {
        super.initInMainProcess()
        initCacheData() // 初始化缓存数据
        initARouter() // 初始化Router
        initDoraemon() // 初始化调试工具
        initThirdPlatform() // 初始化三方平台
        initAppBackgroundManager() // 初始化前后台切换的监听
        initRxJavaPluginsErrorHandler() // 初始化errorHandler
    }

    private fun isMainProcess(): Boolean {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        if (am.runningAppProcesses == null) {
            return false
        }
        val processInfo = am.runningAppProcesses
        val mainProcessName = packageName
        val myPid = Process.myPid()
        for (info in processInfo) {
            if (info.pid == myPid && mainProcessName == info.processName) {
                return true
            }
        }
        return false
    }

    /**
     * ================================================================
     * 抓包条件：
     * 1.公司渠道的包
     * 2.需要在sd卡下面保存charles的pem文件，文件名称为charles.pem
     * 3.需要SD卡读取权限
     * ================================================================
     * 初始化抓包和okhttp的log
     */
    private fun initCharles() {
        ClientModule.httpDebug = BuildConfig.DEBUG
    }

    /**
     * 初始化ARouter
     */
    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog() // 打印日志
            ARouter.openDebug() // 开启调试模式
            ARouter.printStackTrace() // 打印日志的时候打印线程堆栈
        }
        ARouter.init(this) // 初始化
    }

    /** 初始化推送 */
    private fun initPush() {}

    /** 初始化三方 */
    private fun initThirdPlatform() {}


    override fun initKodein(builder: Kodein.MainBuilder) {
        super.initKodein(builder)
        // 注入数据库

        // 在这里添加一些全局的注入
        builder.import(userRepositoryModel) // 用户相关的
        builder.import(oauthRepositoryModel) // 登录授权相关
        builder.import(ringRepositoryModel) // ring相关
    }

    /** 初始化调试工具哆啦A梦 */
    private fun initDoraemon() {
        if (BuildConfig.DEBUG) {
            DoraemonKit.install(this)
        }
    }

    /** 初始化缓存数据 */
    private fun initCacheData() {
        OauthRepository.init()
    }

    /**
     * App前后台切换的管理
     */
    private fun initAppBackgroundManager() {
        AppBackgroundManager.setAppStateListener { isAppForeground ->
            if (isAppForeground) {
                // 回到应用, 添加相应监听。
                Log.d("qingyi", "App:: app 进入前台")
            } else {
                // 应用在后台。
                Log.d("qingyi", "App:: app 退到后台")
            }
        }
    }

    /** 弱网请求等错误时处理rx */
    private fun initRxJavaPluginsErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                LogUtils.e(e.cause)
                return@setErrorHandler
            }
            if ((e is IOException) || (e is SocketException)) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (e is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if ((e is NullPointerException) || (e is IllegalArgumentException)) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(
                    Thread.currentThread(),
                    e
                )
                return@setErrorHandler
            }
            if (e is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(
                    Thread.currentThread(),
                    e
                )
                return@setErrorHandler
            }
        }
    }

    companion object {

        /** App的静态实例 */
        lateinit var INSTANCE: App

        init {
            // 设置全局的下拉刷新样式
            // 设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.white, R.color.mainThemeColor)//全局设置主题颜色
                BlackManHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        }
    }
}