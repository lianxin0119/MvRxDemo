package space.lianxin.comm.utils.api.exception

/**
 * ===========================================
 * 网络请求异常的Exception.
 *
 * @author: lianxin
 * @date: 2021/3/3 19:57
 * ===========================================
 */
open class ApiException constructor(var code: Int = 0, var msg: String? = null) : Exception()