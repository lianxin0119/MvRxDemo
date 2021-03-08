package space.lianxin.comm.utils.api.handle

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import okhttp3.*
import okio.Buffer
import space.lianxin.base.net.http.GlobeHttpHandler
import space.lianxin.comm.config.Constans
import space.lianxin.comm.repository.OauthRepository
import space.lianxin.comm.utils.DeviceUtil
import space.lianxin.comm.utils.encypt.RsaEncypt

/**
 * ===========================================
 * 网络请求监听
 *
 * @author: lianxin
 * @date: 2021/3/6 11:29
 * ===========================================
 */
class GlobalHttpHandlerImpl(private var context: Context) : GlobeHttpHandler {

    /** 请求体转字符串 */
    private fun requestBodyToString(requestBody: RequestBody): String {
        val buffer = Buffer()
        requestBody.writeTo(buffer)
        return buffer.readUtf8()
    }

    override fun onHttpRequestBefore(chain: Interceptor.Chain, request: Request): Request {
        val url = chain.request().url() // 请求地址
        val userId = OauthRepository.getUserIdToken().orEmpty() // 用户标识
        val devUUID = DeviceUtil.getDeviceUUID(context)
        val token =
            "userid=${userId}&platform=${Constans.PLATFORM}&dev=${devUUID}&token=3872e85d-0206-40f0-ac8d-35bbd6d59712"
        var requestBody: RequestBody? = null
        // 对需要加密的接口进行加密处理
        if (request.header(Constans.Encryption) == Constans.Rsa) {
            request.body()?.let {
                val bodyStr = requestBodyToString(it)
                // 加密
                val newBodyStr = RsaEncypt.encypt(bodyStr, RsaEncypt.PUBLIC_KEY_TOKEN)
                requestBody = RequestBody.create(it.contentType(), newBodyStr)
            }
        }
        val re = chain.request().newBuilder()
            .header("Token-Cninct", RsaEncypt.encypt(token, RsaEncypt.PUBLIC_KEY_TOKEN))
        requestBody?.let {
            re.post(it)
        }
        return re.url(url).build()
    }

    override fun onHttpResultResponse(
        httpResult: String,
        chain: Interceptor.Chain,
        response: Response
    ): Response {
        // 对加密接口进行解密处理
        if (response.isSuccessful && chain.request().header(Constans.Encryption) == Constans.Rsa) {
            response.body()?.let {
                return try {
                    val bodyStr = it.string() // 获取文本
                    val newBodyStr = RsaEncypt.decypt(bodyStr, RsaEncypt.PRIVATE_KEY) // 进行解密处理
                    response.newBuilder()
                        .body(ResponseBody.create(it.contentType(), newBodyStr))
                        .build()
                } catch (e: Exception) {
                    LogUtils.e("接口返回数据为空， error.message = ${e.message}")
                    response
                }
            }
        }
        return response
    }
}