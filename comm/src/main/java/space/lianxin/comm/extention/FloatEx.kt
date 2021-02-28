package space.lianxin.comm.extention

import android.content.res.Resources
import android.util.TypedValue

/**
 * ===========================================
 * Float相关扩展。
 *
 * @author: lianxin
 * @date: 2021/2/25 12:32
 * ===========================================
 */

/** 转换成dp值，返回float类型 */
fun Float.dp() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    Resources.getSystem().displayMetrics
)

/** 转换成dp值，返回int类型 */
fun Float.dpInt() = dp().toInt()

/** 如果为null就返回0。否则返回自身。 */
fun Float?.orZero(): Float {
    return this ?: 0f
}

/** (中缀表达式) 如果为null就返回指定的数。否则返回自身 */
infix fun Float?.ifNullTo(i: Float): Float {
    return this ?: i
}
