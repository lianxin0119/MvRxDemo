package space.lianxin.comm.ui.mvrx.item

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import kotlinx.android.synthetic.main.comm_mvrx_item_loadmore.view.*
import space.lianxin.comm.R
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyHolder
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyModel

/**
 * ===========================================
 * 加载更多的Item
 *
 * @author: lianxin
 * @date: 2021/3/1 16:54
 * ===========================================
 */
@EpoxyModelClass
abstract class LoadMoreItem : BaseEpoxyModel<BaseEpoxyHolder>() {

    override fun getDefaultLayout() = R.layout.comm_mvrx_item_loadmore

    /** 提示的文字，默认提示为："数据加载中..." */
    @EpoxyAttribute
    var tipsText: CharSequence? = null

    /** 提示TextView的样式 */
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var textStyle: (TextView.() -> Unit)? = null

    /** 提示ProgressBar的样式 */
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var progressBarStyle: (ProgressBar.() -> Unit)? = null

    /** 加载更多的回调 */
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onLoadMore: (() -> Unit)? = null

    override fun onBind(itemView: View) {
        tipsText?.let {
            itemView.loadMoreTv.text = it
        }
        textStyle?.let {
            itemView.loadMoreTv.apply(it)
        }
        progressBarStyle?.let {
            itemView.loadMorePb.apply(it)
        }
    }

    override fun onVisibilityChanged(
        percentVisibleHeight: Float,
        percentVisibleWidth: Float,
        visibleHeight: Int,
        visibleWidth: Int,
        view: BaseEpoxyHolder
    ) {
        super.onVisibilityChanged(
            percentVisibleHeight,
            percentVisibleWidth,
            visibleHeight,
            visibleWidth,
            view
        )
        if (percentVisibleHeight == 100F) {
            // 加载更多完全显示出来
            onLoadMore?.invoke()
        }
    }

}