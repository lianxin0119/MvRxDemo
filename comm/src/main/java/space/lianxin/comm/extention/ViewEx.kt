package space.lianxin.comm.extention

import android.graphics.Color
import android.view.View
import space.lianxin.comm.utils.PressEffectHelper

/**
 * ===========================================
 * View相关扩展
 *
 * @author: lianxin
 * @date: 2021/3/7 19:02
 * ===========================================
 */

/**
 * 根据条件表达式设置View.VISIBLE 和 View.GONE，不包括View.INVISIBLE
 * @param expression true 为 View.VISIBLE false 为View.GONE
 */
fun View.visibilityWith(expression: () -> Boolean) {
    if (expression.invoke()) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

/**
 * 设置按下效果为改变背景色
 */
fun View.pressEffectBgColor(
    bgColor: Int = Color.parseColor("#F7F7F7"),
    topLeftRadiusDp: Float = 0f,
    topRightRadiusDp: Float = 0f,
    bottomRightRadiusDp: Float = 0f,
    bottomLeftRadiusDp: Float = 0f
) {
    PressEffectHelper.bgColorEffect(
        this,
        bgColor,
        topLeftRadiusDp,
        topRightRadiusDp,
        bottomRightRadiusDp,
        bottomLeftRadiusDp
    )
}