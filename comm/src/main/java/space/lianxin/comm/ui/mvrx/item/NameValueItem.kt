package space.lianxin.comm.ui.mvrx.item

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import kotlinx.android.synthetic.main.comm_mvrx_item_namevalue.view.*
import space.lianxin.base.extention.click
import space.lianxin.comm.R
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyHolder
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyModel

/**
 * ===========================================
 * 名称和值(左右结构)的item
 *
 * @author: lianxin
 * @date: 2021/3/7 12:25
 * ===========================================
 */
@EpoxyModelClass
abstract class NameValueItem : BaseEpoxyModel<BaseEpoxyHolder>() {

    override fun getDefaultLayout() = R.layout.comm_mvrx_item_namevalue

    /** 名称 */
    @EpoxyAttribute
    var name: CharSequence? = null

    /** 值 */
    @EpoxyAttribute
    var value: CharSequence? = null

    /** 设置背景颜色值。 */
    @EpoxyAttribute
    var backgroundTint: Int? = null

    /** 名称DSL */
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var nameStyle: (TextView.() -> Unit)? = null

    /** 值DSL */
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var valueStyle: (TextView.() -> Unit)? = null

    /** 点击事件 */
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClick: (() -> Unit)? = null

    override fun onBind(itemView: View) {
        super.onBind(itemView)
        name?.let {
            itemView.mvrxName.text = it
        }
        value?.let {
            itemView.mvrxValue.text = it
        }
        nameStyle?.let {
            itemView.mvrxName.apply(it)
        }
        valueStyle?.let {
            itemView.mvrxValue.apply(it)
        }
        backgroundTint?.let {
            itemView.background.colorFilter = PorterDuffColorFilter(it, PorterDuff.Mode.DARKEN)
        }
        itemView.click {
            onClick?.invoke()
        }
    }

}