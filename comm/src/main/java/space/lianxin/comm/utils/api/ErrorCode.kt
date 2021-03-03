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

    const val Success = 200 // 成功返回数据
    const val PageNotFound = 404 // 页面不存在
    const val TokenExpired = 317 // token过期

}

/** 服务器返回异常的类型相关判断工具类 */
object ErrorCodeUtils {

    private val ggCodeList = listOf(
        504, //获取缓存锁异常
        503, //服务端HTTP请求错误
        502, //json反序列化失败
        501, //json序列化失败
        500, //服务器异常
        335, //分布式锁处理中
        321, //数据异常
        319, //内容解析失败
        301 //请求冲突或数据库唯一冲突
    )

    /** 不需要吐司服务器message的code码集合。 */
    private val dontShowMsgList = listOf(
        ErrorCode.TokenExpired
    )

    /** 异常是否属于提示“啊哦，服务器抽搐了，请稍后重试~” */
    fun isShowSeverGG(errorCode: Int): Boolean {
        return ggCodeList.contains(errorCode)
    }

    /** 是否需要展示服务端提示的msg */
    fun isNeedShowMsg(errorCode: Int): Boolean {
        return !dontShowMsgList.contains(errorCode)
    }

}