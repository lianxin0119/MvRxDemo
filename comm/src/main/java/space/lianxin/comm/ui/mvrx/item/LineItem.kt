package space.lianxin.comm.ui.mvrx.item

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import space.lianxin.comm.R
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyHolder
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyModel

/**
 * ===========================================
 * 通用的分割线。
 *
 * @author: lianxin
 * @date: 2021/3/2 9:31
 * ===========================================
 */
@EpoxyModelClass
abstract class LineItem : BaseEpoxyModel<BaseEpoxyHolder>() {

    override fun getDefaultLayout() = R.layout.comm_mvrx_item_line

    @EpoxyAttribute
    var heightPx: Int? = null

    init {
        heightPx = 1
        bgColorRes = R.color.itemLineBgColor
    }

    override fun bind(holder: BaseEpoxyHolder) {
        super.bind(holder)
        if (heightDp == null) {
            heightPx?.let {
                val layoutParams = holder.itemView.layoutParams
                layoutParams.height = it
            }
        }
    }

}