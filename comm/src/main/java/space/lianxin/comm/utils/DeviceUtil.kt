package space.lianxin.comm.utils

import android.content.Context
import android.os.Build
import android.provider.Settings

/**
 * ===========================================
 * 设备相关工具类。
 *
 * @author: lianxin
 * @date: 2021/3/6 11:34
 * ===========================================
 */
object DeviceUtil {

    /** 获取设备唯一标识，不是那么准确，绝大部分是可靠的 */
    fun getDeviceUUID(context: Context): String {
        // 设备ID
        val androidId =
            Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val board = Build.BOARD // 主板名
        return "android_id_${androidId}_board_${board}"
    }
}