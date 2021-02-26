package space.lianxin.comm.extention

import android.widget.TextView
import space.lianxin.base.extention.gone
import space.lianxin.base.extention.visible

/**
 * ===========================================
 * TextView相关扩展。
 *
 * @author: lianxin
 * @date: 2021/2/25 12:53
 * ===========================================
 */

/** 当text不为null时才设置值。否者就隐藏视图。 */
fun TextView.showTextNotNull(text: CharSequence?) {
    if (text.isNullOrBlank()) {
        gone()
    } else {
        visible()
        setText(text)
    }
}