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
    val state: Int,
    val message: String,
    val ext: T
)

data class BaseNetList<T>(
    val total_count: Int,
    val total_pages: Int,
    val page: Int,
    val page_size: Int,
    val result: List<T>
)