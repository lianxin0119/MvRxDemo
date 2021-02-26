package space.lianxin.comm.extention

import android.app.Activity
import android.content.Context

/**
 * ===========================================
 * Context相关扩展。
 *
 * @author: lianxin
 * @date: 2021/2/25 12:21
 * ===========================================
 */

/** 设置透明度。黑暗 0.0F ~ 1.0F 透明 */
fun Context.setBackgroundAlpha(alpha: Float) {
    val act = this as? Activity ?: return
    val attributes = act.window.attributes
    attributes.alpha = alpha
    act.window.attributes = attributes
}

private var lastClickTime: Long = 0
private const val SPACE_TIME = 600

/** 快速双击 */
fun isDoubleClick(): Boolean {
    val currentTime = System.currentTimeMillis()
    val isDoubleClick: Boolean // in range
    isDoubleClick = currentTime - lastClickTime <= SPACE_TIME
    if (!isDoubleClick) {
        lastClickTime = currentTime
    }
    return isDoubleClick
}