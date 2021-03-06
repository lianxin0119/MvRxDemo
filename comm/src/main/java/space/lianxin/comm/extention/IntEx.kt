package space.lianxin.comm.extention

/**
 * ===========================================
 * Int相关扩展。
 *
 * @author: lianxin
 * @date: 2021/2/25 12:35
 * ===========================================
 */
/** 转换成dp值，返回float类型 */
fun Int.dp() = toFloat().dp()

/** 转换成dp值，返回int类型 */
fun Int.dpInt() = dp().toInt()

/** 如果为null就返回0。否则返回自身。 */
fun Int?.orZero(): Int {
    return this ?: 0
}

/** (中缀表达式) 如果为null就返回指定的数。否则返回自身 */
infix fun Int?.ifNullTo(i: Int): Int {
    return this ?: i
}

infix fun Int?.ifNullOrZeroTo(i: Int): Int {
    return if (this == null || this == 0) i else this
}