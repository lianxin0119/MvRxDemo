package space.lianxin.comm.utils.api

/**
 * ===========================================
 * 网络请求返回的基类.
 *
 * @author: lianxin
 * @date: 2021/3/3 19:37
 * ===========================================
 */
data class BaseResponse<out T>(
    /** 正常接口使用的状态码 */
    val code: Int,
    /** 异常接口使用的状态码 */
    val status: Int,
    /** code异常对应的信息提示 */
    val message: String,
    /** 正常返回的数据信息 */
    val data: T
)