package space.lianxin.comm.utils.encypt

import android.util.Base64
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher


/**
 *  author : hmily
 *  desc   : RSA加密解密
 */
object RsaEncypt {
    /** 公钥 */
    const val PUBLIC_KEY_TOKEN =
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxFb+e8CF0sie4aVYq0dnjpef1vghU5+yR/G1QTF8Px3NGmHrQyu6/7anRPzqQ4QGilJZfT+gLXE0HJbqihfaCQi5u/bO5LnuEj6DYfG7mhRS73Yspp4msieJfkLKRMSSz4OxyDTael/x4Q9Cy8Qa8Dr/yeIvwALtiYRF+Nh1IJwIDAQAB"

    /** 私钥 */
    const val PRIVATE_KEY =
        "MIICXQIBAAKBgQDa3Hs2FDkmyiEMzvzPmdyHFciRYZ4zVxzj5ngYUGMXUH5M63dnB/0CZ2IH6ncgz1QkGQxbcFNf7ov6+xa5xiAtbxRe2MH2o5ZE6YCOxcvgwRX681WqUz/RNdN+YBezLd+8zIHwbrCuRLdIFR2GZLfkWFIKRrPoyGFVOXBKEYpZQQIDAQABAoGAYALFEm5AvCGD6iiLg08ODTyjJhPa4M7gX6HlIVVygvHO1lOdp9c7SOiteqeC5yPM16EdaKeYC8eMJOgtigW+ayA7sADk5GSe1WM6AxW5pB2FcSIaOx3aEdCxq2PnZqcABPLPP9vMETMWYVt5VocUxW6+9vUh95cYpRG3rwFFcpkCQQDlIAXw2I1629CzcM6Dlmqqf6hy3lppbTcD17DxoPfknZUDl+GVTEP1lnjCgexKnnllfDWb879E486kH7wSgWrLAkEA9IhDoxvZ8ibNTrte4g2f6RcFF2sRFWPVz9y+TEOZDeVj72o4owpv1lpz4FdOO7FW1DyEr/FKN/9HRSaphRzOowJBAMI8wYyJqHbMvkJAkXHQJCDiE+U9Rpw1b/wb7+8HLN9sD5V6PkyY2MvrgfgyuCQR8IvuHmxU2IMINHfCR2fQur0CQCgshugyODFHXK2VrPX4hRAXXxfE2E6qU0NOIdmHtYqJ6Ew6KEno+gy+WZE+5rR54ykFNEtvmeDvSLc8rdcXr38CQQDcm4QDJZSP4mZyFPReaVST6y9XVhsYnuOKPn4+bZGWHVklaGdTUUChDcg02Pc57w3hvcw9Na6ie3eGxzicgB9D"

    /** RSA最大加密明文大小 */
    private const val MAX_ENCRYPT_BLOCK = 117

    /** RSA最大解密密文大小 */
    private const val MAX_DECRYPT_BLOCK = 128

    /** 获取私钥 */
    private fun getPrivateKey(privateKey: String): PrivateKey {
        val keyFactory = KeyFactory.getInstance("RSA")
        val decodedKey = Base64.decode(privateKey, Base64.NO_WRAP)
        val keySpec = PKCS8EncodedKeySpec(decodedKey)
        return keyFactory.generatePrivate(keySpec)
    }

    /** 获取公钥 */
    private fun getPublicKey(publicKey: String): PublicKey {
        val keyFactory = KeyFactory.getInstance("RSA")
        val decodedKey = Base64.decode(publicKey, Base64.NO_WRAP)
        val keySpec = X509EncodedKeySpec(decodedKey)
        return keyFactory.generatePublic(keySpec)
    }

    /** RSA加密 */
    fun encypt(data: String, publicKey: String): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey))
        val dataBytes = data.toByteArray()
        val inputLen = dataBytes.size
        val out = ByteArrayOutputStream()
        var offset = 0
        var cache: ByteArray
        var i = 0
        // 对数据分段加密
        while (inputLen - offset > 0) {
            cache = if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cipher.doFinal(dataBytes, offset, MAX_ENCRYPT_BLOCK)
            } else {
                cipher.doFinal(dataBytes, offset, inputLen - offset)
            }
            out.write(cache, 0, cache.size)
            i++
            offset = i * MAX_ENCRYPT_BLOCK
        }
        val encryptedData = out.toByteArray()
        out.close()
        return Base64.encodeToString(encryptedData, Base64.NO_WRAP)
    }

    /** 解密 */
    fun decypt(data: String, privateKey: String): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey))
        val dataBytes = Base64.decode(data, Base64.NO_WRAP)
        val inputLen = dataBytes.size
        val out = ByteArrayOutputStream()
        var offset = 0
        var cache: ByteArray
        var i = 0
        // 对数据分段解密
        while (inputLen - offset > 0) {
            cache = if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK)
            } else {
                cipher.doFinal(dataBytes, offset, inputLen - offset)
            }
            out.write(cache, 0, cache.size)
            i++
            offset = i * MAX_DECRYPT_BLOCK
        }
        val decryptedData = out.toByteArray()
        out.close()
        return decryptedData.toString(Charset.defaultCharset())
    }
}