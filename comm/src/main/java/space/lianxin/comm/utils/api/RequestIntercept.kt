package space.lianxin.comm.utils.api

import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import space.lianxin.base.net.http.GlobeHttpHandler
import space.lianxin.comm.utils.HttpZipHelper
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ===========================================
 * description: 网络请求的拦截器.
 *
 * @author: JessYan
 * ===========================================
 */
@Singleton
class RequestIntercept @Inject constructor(private val mHandler: GlobeHttpHandler?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // 在请求服务器之前可以拿到request,做一些操作比如给request添加header
        // 如果不做操作则返回参数中的request
        mHandler?.let {
            request = it.onHttpRequestBefore(chain, request)
        }

        val requestBuffer = Buffer()
        request.body()?.writeTo(requestBuffer)

        val originalResponse = chain.proceed(request)

        // 读取服务器返回的结果
        val responseBody = originalResponse.body()
        val source = responseBody!!.source()
        source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
        val buffer = source.buffer

        // 获取content的压缩类型
        val encoding = originalResponse.headers()["Content-Encoding"]

        val clone = buffer.clone()
        val bodyString: String

        // 解析response content
        if (encoding != null && encoding.equals("gzip", ignoreCase = true)) { //c ontent使用gzip压缩
            bodyString = HttpZipHelper.decompressForGzip(clone.readByteArray()) // 解压
        } else if (encoding != null && encoding.equals(
                "zlib",
                ignoreCase = true
            )
        ) { //content使用zlib压缩
            bodyString = HttpZipHelper.decompressToStringForZlib(clone.readByteArray()) // 解压
        } else { // content没有被压缩
            var charset: Charset? = Charset.forName("UTF-8")
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(charset)
            }
            bodyString = clone.readString(charset!!)
        }

        return mHandler?.onHttpResultResponse(bodyString, chain, originalResponse)
            ?: originalResponse

    }

}