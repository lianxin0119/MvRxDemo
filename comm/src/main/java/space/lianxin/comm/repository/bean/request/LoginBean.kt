package space.lianxin.comm.repository.bean.request

/**
 * ===========================================
 * 登录实体类。
 *
 * @author: lianxin
 * @date: 2021/3/6 11:48
 * ===========================================
 */
data class LoginBean(
    /** 账号:电话号码 */
    val account: String? = null,
    /** 验证码 */
    val sms_code: String? = null,
    /** umeng token */
    val device_token: String? = null
)
