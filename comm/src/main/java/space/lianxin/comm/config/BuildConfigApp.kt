package space.lianxin.comm.config

import space.lianxin.comm.BuildConfig
import space.lianxin.comm.constants.ApiConstants

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/2 15:12
 * ===========================================
 */
object BuildConfigApp {

    val DEBUG = if (ApiConstants.Host.FORCE_RELEASE) {
        false
    } else {
        BuildConfig.DEBUG
    }


}