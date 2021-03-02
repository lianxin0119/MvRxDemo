package space.lianxin.comm.ui.mvrx.item

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import kotlinx.android.synthetic.main.comm_mvrx_item_nomore.view.*
import space.lianxin.comm.R
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyHolder
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyModel

/**
 * ===========================================
 * 没有更多item
 *
 * @author: lianxin
 * @date: 2021/2/28 11:21
 * ===========================================
 */
@EpoxyModelClass
abstract class NoMoreItem : BaseEpoxyModel<BaseEpoxyHolder>() {

    override fun getDefaultLayout() = R.layout.comm_mvrx_item_nomore

    /** 提示的文字，没有设置时。默认提示为："没有更多了~" */
    @EpoxyAttribute
    var tipsText: CharSequence? = null

    /** 提示TextView的样式 */
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var textStyle: (TextView.() -> Unit)? = null

    override fun onBind(itemView: View) {
        tipsText?.let {
            itemView.noMoreTv.text = it
        }
        textStyle?.let {
            itemView.noMoreTv.apply(it)
        }
    }

}