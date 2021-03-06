package space.lianxin.comm.utils.encypt

import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec

object DesEncypt {

    /**
     * DES加密
     * @param input 待加密字符
     * @param password 加密密码，长度不能够小于8位
     */
    fun encrypt(input: String, password: String = "FDKASFJI"): String {
        //simple encrypt
        val c = Cipher.getInstance("DES")//Cipher.getInstance("DES/CBC/PKCS5Padding")
        val keyfact = SecretKeyFactory.getInstance("DES")
        val keyn = DESKeySpec(password.toByteArray())
        val key = keyfact.generateSecret(keyn)
        c.init(Cipher.ENCRYPT_MODE, key)
        val encrypt = c.doFinal(input.toByteArray())
        return Base64.encode(encrypt)
    }


    fun decrypt(input: String, password: String = "FDKASFJI"): String {
        //simple encrypt
        val c = Cipher.getInstance("DES")//Cipher.getInstance("DES/CBC/PKCS5Padding")
        val keyfact = SecretKeyFactory.getInstance("DES")
        val keyn = DESKeySpec(password.toByteArray())
        val key = keyfact.generateSecret(keyn)
        c.init(Cipher.DECRYPT_MODE, key)
        val encrypt = c.doFinal(Base64.decode(input))
        return String(encrypt)
    }

}