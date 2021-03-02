package space.lianxin.comm.constants

import space.lianxin.comm.BuildConfig
import space.lianxin.comm.config.BuildConfigApp

/**
 * ===========================================
 * Api常量
 *
 * @author: lianxin
 * @date: 2021/3/2 14:57
 * ===========================================
 */
object ApiConstants {

    object Host {

        internal const val FORCE_RELEASE = true // 是否强制使用正式服
        private const val HTTP = "http://"
        private const val HTTPS = "https://"

        // APP的请求地址前缀
        internal val BASE_URL = if (BuildConfigApp.DEBUG) {
            "${HTTPS}${BuildConfig.SERVER_HOST}"
        } else {
            "${HTTPS}${BuildConfig.SERVER_HOST}"
        }
    }


}