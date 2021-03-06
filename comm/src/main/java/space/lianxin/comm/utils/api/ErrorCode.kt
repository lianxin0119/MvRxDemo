package space.lianxin.comm.utils.api

/**
 * ===========================================
 * api错误码
 *
 * @author: lianxin
 * @date: 2021/3/3 19:25
 * ===========================================
 */
object ErrorCode {

    // state 错误
    const val OK = 0 // 正常
    const val ApiHasNot = -2 // 接口不存在
    const val UnKnown = -1 // 未知错误
    const val Error = 1 // 错误
    const val ErrorParamar = 2 // 参数错误
    const val NoServer = 4 // 无数据服务
    const val UnLogin = 5 // 未登录
    const val UnRegister = 7 // 未注册

    // code码错误
    const val Success = 200 // 成功返回数据
    const val ServiceError = 500 // 服务器发生错误
    const val PageNotFound = 404 // 页面不存在
    const val RequestRefuse = 405 // 请求被拒绝
    const val RequestSerRefuse = 403 // 请求被服务器拒绝
    const val Redirect = 307 // 请求被重定向到其他页面
    const val ServiceExc = 502 // 服务器异常

}

/** 服务器返回异常的类型相关判断工具类 */
object ErrorCodeUtils {

    /** 不需要吐司服务器message的code码集合。 */
    private val dontShowMsgList = listOf(
        ErrorCode.UnRegister,
        ErrorCode.UnLogin
    )

    /** 是否需要展示服务端提示的msg */
    fun isNeedShowMsg(errorCode: Int): Boolean {
        return !dontShowMsgList.contains(errorCode)
    }

}