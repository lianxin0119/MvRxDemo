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